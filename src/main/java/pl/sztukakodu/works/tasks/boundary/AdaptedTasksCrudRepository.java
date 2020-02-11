
package pl.sztukakodu.works.tasks.boundary;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.sztukakodu.works.exceptions.NotFoundException;
import pl.sztukakodu.works.tasks.entity.Task;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Primary
@Repository
@RequiredArgsConstructor
public class AdaptedTasksCrudRepository implements TaskRepository {
    private final TasksCrudRepository taskCrudRepository;

    @Override
    public void add(Task task) {
        taskCrudRepository.save(task);
    }

    @Override
    public void addAll(Iterable<Task> tasks) {
        taskCrudRepository.saveAll(tasks);
    }


    @Override
    public List<Task> fetchAll() {
        return StreamSupport.stream(taskCrudRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Task fetchById(Long id) {
        return taskCrudRepository.findById(id).orElseThrow(()->new NotFoundException("Cannot find task with id: " + id));
    }

    @Override
    public void deleteById(Long aLong) {
        taskCrudRepository.deleteById(aLong);
    }

    @Override
    public void update(Long id, String title, String author, String description) {
        taskCrudRepository.updateTitleDescription(id, title, description, author);
    }

    @Override
    public void addAttachment(Long id, String fileName) {

    }

    @Override
    public void save(Task task) {
        taskCrudRepository.save(task);
    }

    @Override
    public List<Task> findByTitle(String title) {
        return taskCrudRepository.findAllByTitleLike(title);
    }

    @Override
    public List<Task> findWithAttachments() {
        return Collections.emptyList();
    }

}
