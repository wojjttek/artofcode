package pl.sztukakodu.works.tasks.boundary;

import pl.sztukakodu.works.tasks.entity.Task;

import java.util.List;

public interface TaskRepository {

    void add(Task task);

    List<Task> fetchAll();

    Task fetchById(Long id);

    void deleteById(Long id);

    void update(Long id, String title, String author, String description);

    void addAttachment(Long id, String fileName);
}
