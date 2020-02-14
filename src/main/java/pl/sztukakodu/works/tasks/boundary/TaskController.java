package pl.sztukakodu.works.tasks.boundary;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.sztukakodu.works.tags.control.TagsService;
import pl.sztukakodu.works.tags.entity.Tag;
import pl.sztukakodu.works.tasks.control.TaskService;
import pl.sztukakodu.works.tasks.entity.Task;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    private final TagsService tagsService;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks(@RequestParam Optional<String> title) {
        log.info("Fetching all tasks with filter: {}", title);
        return ResponseEntity.ok(toTaskResponse(title.map(taskService::filterByTitle)
                .orElseGet(taskService::fetchAll)));
    }

    public List<TaskResponse> toTaskResponse(Collection<Task> tasks) {
        return tasks.stream().map(task -> {
            List<Long> tagIds = task.getTags().stream().map(Tag::getId).collect(Collectors.toList());
            Set<Tag> tags = tagsService.findAllById(tagIds);
            return TaskResponse.from(task, tags);
        }).collect(Collectors.toList());
    }

    @GetMapping(path = "/_search")
    public ResponseEntity<List<TaskResponse>> getTasks(@RequestParam(defaultValue = "false") Boolean attachments) {
        log.info("Fetching all tasks with attachments: {}", attachments);
        if (attachments) {
            return  ResponseEntity.ok(toTaskResponse(taskService.findWithAttachments()));
        } else {
            return  ResponseEntity.ok(toTaskResponse(taskService.fetchAll()));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskViewResponse> getTaskById(@PathVariable Long id) {
        log.info("Fetch task with id: {}", id);
        return ResponseEntity.ok(TaskViewResponse.from(taskRepository.fetchById(id)));
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
    public ResponseEntity addAttachment(@PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestParam("comment") String comment) throws IOException {
        log.info("Handling file upload: {}", file.getOriginalFilename());
        storageService.saveFile(id, file);
        taskService.addAttachment(id, file, comment);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "{id}/tags")
    public ResponseEntity addTag(@PathVariable Long id, @RequestBody AddTagRequest request) {
        log.info("Storing new tag: {}", id);
        taskService.addTag(id, request.getTagId());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(path = "/{id}/tags/{tagId}")
    public ResponseEntity removeTag(@PathVariable Long id, @PathVariable Long tagId) {
        taskService.removeTag(id, tagId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(path = "/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok(taskConfig.getEndpointMessage());
    }


    @PostMapping
    public ResponseEntity addTask(@RequestBody CreateTaskRequest task) {
        log.info("Storing new task: {}", task);
        taskService.addTask(task.getTitle(), task.getAuthor(), task.getDescription(), task.getTags());
        return ResponseEntity.status(HttpStatus.CREATED).build();
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