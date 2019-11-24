package pl.sztukakodu.works.tasks;

import java.util.List;

public interface TaskRepository {

    void add(Task task);

    List<Task> fetchAll();

}
