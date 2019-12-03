package pl.sztukakodu.works.tasks.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sztukakodu.works.Clock;
import pl.sztukakodu.works.tasks.boundary.TaskRepository;
import pl.sztukakodu.works.tasks.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final Clock clock;
    private final AtomicLong nextTaskId = new AtomicLong(0L);
    public TaskService(TaskRepository taskRepository, Clock clock) {
        this.taskRepository = taskRepository;
        this.clock = clock;
    }

    public void addTask(String title, String author, String description ) {
        taskRepository.add(new Task(nextTaskId.getAndIncrement(), title, author, description, clock.time(), new ArrayList<>()));
    }

    public void updateTask( Long id, String title, String author, String description )  {
        taskRepository.update(id, title, author, description);
    }

    public List<Task> fetchAll() {
        return taskRepository.fetchAll();
    }

    public List<Task> filterAllByQuery(String query) {
        return taskRepository.fetchAll().stream().filter(task ->
                task.getTitle().contains(query) || task.getDescription().contains(query)
        ).collect(Collectors.toList());
    }

    public void addAttachment(Long id, String fileName) {
        taskRepository.addAttachment(id, fileName);
    }

    public Task getTask(Long id) {
        return taskRepository.fetchById(id);
    }
}