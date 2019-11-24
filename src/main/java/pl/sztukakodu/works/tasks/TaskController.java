package pl.sztukakodu.works.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
/*
    public TaskController() {
        this.taskConfig = new TaskConfig();
        this.taskConfig.setEndpointMessage("hello from Constructor!");

    }
*/

    @GetMapping
    public List<Task> getTasks() {
        log.info("Fetching all tasks...");
        return taskRepository.fetchAll();
    }

    @PostMapping
    public void addTask(@RequestBody Task task) {
        log.info("Storing new task: {}", task);
//        task.setCreateDateTime( LocalDateTime.now());
        taskRepository.add(task);
    }
}