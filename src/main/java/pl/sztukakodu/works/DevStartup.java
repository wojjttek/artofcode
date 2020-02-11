package pl.sztukakodu.works;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.sztukakodu.works.tags.boundary.TagsRepository;
import pl.sztukakodu.works.tags.entity.Tag;
import pl.sztukakodu.works.tasks.boundary.TaskRepository;
import pl.sztukakodu.works.tasks.entity.Task;

import java.util.Arrays;
import java.util.List;

@Profile("dev")
@Component
@AllArgsConstructor
@Slf4j
public class DevStartup {

    private final TaskRepository taskRepository;
    private final TagsRepository tagsRepository;
    private final Clock clock;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeApplication() {
        log.info("Initilizing DEV environment");
        insertTasks();

        insertTags();
        log.info("DEV env initialized");
    }


    private void insertTasks() {
        List<Task> tasks = Arrays.asList(
                new Task("Dokonczyc zadanie z modulu 1", "A", "Rejestracja na FB", clock.time()),
                new Task("Obejrzeć moduł 2", "B", "Wprowadzenie do SPirnga", clock.time()),
                new Task("Stworzyć własny projekt na githubie", "ABC", "https://github.com", clock.time()));
        taskRepository.addAll(tasks);
        log.info("Add {} tasks", tasks.size());
    }

    private void insertTags() {
        List<Tag> tags = Arrays.asList(new Tag("Pilne"), new Tag("W domu"), new Tag("Na mieście"));
        tagsRepository.saveAll(tags);
        log.info("Add {} tags", tags.size());
    }

}
