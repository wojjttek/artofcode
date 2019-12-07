package pl.sztukakodu.works.tasks.boundary;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.sztukakodu.works.tasks.control.TaskService;
import pl.sztukakodu.works.tasks.entity.Task;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final StorageService storageService;
    private final TaskRepository taskRepository;
    private final TaskConfig taskConfig;
    private final TaskService taskService;

    @PostConstruct
    void init() {
        taskService.addTask("zadanie 1", "author 1", "description 1");
        taskService.addTask("zadanie 2", "author 2", "description 2");
        taskService.addTask("zadanie 3", "author 3", "description 3");
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks(@RequestParam Optional<String> query) {
        log.info("Fetching all tasks with filter: {}", query);
        return ResponseEntity.ok(query.map(taskService::filterAllByQuery)
                .orElseGet(taskService::fetchAll).stream()
                .map(this::toTaskResponse)
                .collect(Collectors.toList()));
    }

    private TaskResponse toTaskResponse(Task t) {
        return new TaskResponse(t.getId(), t.getTitle(), t.getAuthor(), t.getDescription(), t.getCreateDateTime(), t.getFiles());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        log.info("Fetch task with id: {}", id);
        return ResponseEntity.ok(toTaskResponse(taskRepository.fetchById(id)));
    }

    @GetMapping(path = "/{id}/attachments/{filename}")
    public ResponseEntity getAttachment(@PathVariable Long id, @PathVariable String filename, HttpServletRequest request) throws IOException {
        log.info("Fetch task with id: {}", id);
        taskService.getTask(id);
        Resource resource = storageService.loadFile(filename);
        String mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(resource);
    }

    @PostMapping(path = "/{id}/attachments")
    public ResponseEntity addAttachment(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        log.info("Handling file upload: {}", file.getOriginalFilename());
        storageService.saveFile(id, file);
        taskService.addAttachment(id, file.getOriginalFilename());
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity addTask(@RequestBody CreateTaskRequest task) {
        log.info("Storing new task: {}", task);
        taskService.addTask(task.getTitle(), task.getAuthor(), task.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok(taskConfig.getEndpointMessage());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        log.info("Deleting a task with id: {}", id);
        taskRepository.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        log.info("Update a task");
        taskService.updateTask(id, request.getTitle(), request.getAuthor(), request.getDescription());
        return ResponseEntity.accepted().build();
    }
}