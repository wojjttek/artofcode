package pl.sztukakodu.works.tasks.boundary;

import pl.sztukakodu.works.tasks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    void add(Task task);

    void addAll(Iterable<Task> tasks);

    List<Task> fetchAll();

    Task fetchById(Long id);

    void deleteById(Long id);

    void update(Long id, String title, String author, String description);

    void addAttachment(Long id, String fileName);

    void save(Task task);

    List<Task> findByTitle(String title);

    List<Task> findWithAttachments();

    Optional<Task> findById(Long id);
}