package pl.sztukakodu.works;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.sztukakodu.works.projects.boundary.ProjectsCrudRepository;
import pl.sztukakodu.works.projects.entity.Project;
import pl.sztukakodu.works.tags.boundary.TagsRepository;
import pl.sztukakodu.works.tags.entity.Tag;
import pl.sztukakodu.works.tasks.boundary.TaskRepository;
import pl.sztukakodu.works.tasks.entity.Task;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Profile("dev")
@Component
@AllArgsConstructor
@Slf4j
public class DevStartup {

    private final TaskRepository taskRepository;
    private final TagsRepository tagsRepository;
    private final ProjectsCrudRepository projectsCrudRepository;
    private final Clock clock;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initializeApplication() {
        log.info("Initializing DEV environment");

        insertTags();
        insertProject();
        insertProjectShopping();
        insertTasks();

        log.info("DEV env initialized");
    }

    private void insertProject() {
        Project project = new Project("Nagrac szkole Springa");
        Task webinarTask = new Task("Zorganizowac webinar 3", "Ja", "Na zoom.us", clock.time());
        tagsRepository.findByNameContainingIgnoreCase("w domu").ifPresent(webinarTask::addTag);
        List<Task> tasks = Arrays.asList(
                new Task("Nagrac modul 6", "ja", "JPA i Hibernate cz.1", clock.time()),
                new Task("Nagrac modul 7", "ja", "JPA i Hibernate cz.2", clock.time()),
                webinarTask
        );
        project.addTasks(tasks);
        projectsCrudRepository.save(project);
        log.info("Added {} task to 1 project", tasks.size());
    }

    private void insertProjectShopping() {
        Project project = new Project("Zakupy AGD do Mieszkania");
        List<Task> tasks = Arrays.asList(
                new Task("Pralka", "ja", "Bosch lub Samsung", clock.time()),
                new Task("Lodówka", "ja", "No frost, zamrażarka na dole z trzema szufladami", clock.time()),
                new Task("Zmywarka", "ja", "60 cm szerokości, pod zabudowę", clock.time()),
                new Task("Piekarnik", "ja", "z teleskopami i pyrolizą", clock.time())
        );
        tagsRepository.findByNameContainingIgnoreCase("pilne").ifPresent(tag -> {
            tasks.forEach(task -> task.addTag(tag));
        });
        project.addTasks(tasks);
        projectsCrudRepository.save(project);
        log.info("Added {} task to 1 project", tasks.size());
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