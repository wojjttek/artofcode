package pl.sztukakodu.works.imports.control;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sztukakodu.works.imports.boundary.ImportProject;
import pl.sztukakodu.works.imports.boundary.ImportTask;
import pl.sztukakodu.works.projects.control.ProjectService;
import pl.sztukakodu.works.projects.entity.Project;
import pl.sztukakodu.works.tasks.control.TaskService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ImportService {
    private final TaskService taskService;
    private final ProjectService projectService;
    private final FooService fooService;

    public void start(List<ImportTask> tasks, List<ImportProject> projects) {
        startImport(tasks, projects);
    }

    @Transactional
    public void startImport(List<ImportTask> tasks, List<ImportProject> projects) {
        log.info("Importing {} projects, {} tasks.", projects.size(), tasks.size());
        for (ImportProject project : projects) {
            log.info("Importing project: {}", project.getName());
            fooService.sendMail();
            projectService.save(new Project(project.getName()));
            tasks.stream().filter(t -> t.getProjectId().equals(project.getId())).forEach(
                    t -> taskService.addTask(t.getName(), t.getAuthor(), t.getDescription(), t.getTags())
            );
        }
    }
}
