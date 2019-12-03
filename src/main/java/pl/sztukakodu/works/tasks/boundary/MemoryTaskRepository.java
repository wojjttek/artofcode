package pl.sztukakodu.works.tasks.boundary;

import org.springframework.stereotype.Repository;
import pl.sztukakodu.works.exceptions.NotFoundException;
import pl.sztukakodu.works.tasks.entity.Task;

import java.util.*;

@Repository
public class MemoryTaskRepository implements TaskRepository {
    private final Set<Task> tasks = new HashSet<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public List<Task> fetchAll() {
        return new ArrayList<>(tasks);
    }

    public Task fetchById(Long id) {
//        throw new UnsupportedOperationException("not implemented");
        return findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found "));
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(tasks::remove);
    }

    private Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst();
    }

    @Override
    public void update(Long id, String title, String author, String description) {
        Task task = findById(id).orElseThrow(() -> new NotFoundException("Task with id not found: " + id));
        task.setTitle(title);
        task.setDescription(description);
        task.setAuthor(author);
    }

    @Override
    public void addAttachment(Long id, String fileName) {
        Task task = findById(id).orElseThrow(() -> new NotFoundException("Task with id not found: " + id));
        task.getFiles().add(fileName);
    }
}
