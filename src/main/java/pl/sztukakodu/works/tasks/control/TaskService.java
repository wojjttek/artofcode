package pl.sztukakodu.works.tasks.control;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.sztukakodu.works.Clock;
import pl.sztukakodu.works.tags.control.TagsService;
import pl.sztukakodu.works.tags.entity.Tag;
import pl.sztukakodu.works.tasks.boundary.StorageService;
import pl.sztukakodu.works.tasks.boundary.TaskRepository;
import pl.sztukakodu.works.tasks.entity.Task;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final Clock clock;
    private final StorageService storageService;
    private final TagsService tagsService;

    @Transactional
    public Task addTask(String title, String author, String description, Set<String> tags) {
        Task task = new Task(title, author, description, clock.time());
        Set<Tag> tagsForTask = tags.stream()
                .map(tag ->
                tagsService.findByName(tag).orElse(new Tag(tag))
        ).collect(Collectors.toSet());
        task.addTags(tagsForTask);
        taskRepository.add(task);
        return task;
    }

    @Transactional
    public void updateTask(Long id, String title, String author, String description) {
        taskRepository.update(id, title, author, description);
    }

    public List<Task> fetchAll() {
        return taskRepository.fetchAll();
    }

    public List<Task> filterByTitle(String title) {
        return taskRepository.findByTitle(title);
    }

    @Transactional
    public void addAttachment(Long id, MultipartFile attachment, String comment) throws IOException {
        Task task = taskRepository.fetchById(id);
        if (!attachment.isEmpty()) {
            String fileName = storageService.saveFile(id, attachment);
            task.addAttachment(fileName, comment);
        }
    }

    public Task getTask(Long id) {
        return taskRepository.fetchById(id);
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public void addTag(Long id, Long tagId) {
        Task task = taskRepository.fetchById(id);
        Tag tag = tagsService.findById(tagId);
        task.addTag(tag);
    }

    @Transactional
    public void removeTag(Long id, Long tagId) {
        Task task = taskRepository.fetchById(id);
        Tag tag = tagsService.findById(tagId);
        task.removeTag(tag);
    }


    public List<Task> findWithAttachments() {
        return taskRepository.findWithAttachments();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
    }
    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }
}