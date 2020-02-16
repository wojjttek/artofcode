package pl.sztukakodu.works.projects.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sztukakodu.works.exceptions.NotFoundException;
import pl.sztukakodu.works.projects.boundary.ProjectView;
import pl.sztukakodu.works.projects.boundary.ProjectsCrudRepository;
import pl.sztukakodu.works.projects.entity.Project;
import pl.sztukakodu.works.tasks.control.TaskService;
import pl.sztukakodu.works.tasks.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectsCrudRepository projectsCrudRepository;
    private final TaskService taskService;

    public List<Project> fetchAll() {
        return StreamSupport.stream(projectsCrudRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void addProject(String name) {
        projectsCrudRepository.save(new Project(name));
    }

    public Project findById(Long id) {
        return projectsCrudRepository.findById(id).orElseThrow(() -> new NotFoundException("project not found"));
    }

    public void deleteProject(Long id) {
        Project project = findById(id);
        projectsCrudRepository.delete(project);
    }

    public void updateProject(Long id, String name) {
        Project project = findById(id);
        project.setName(name);
        projectsCrudRepository.save(project);
    }

    public List<Project> findByName(String name) {
        return projectsCrudRepository.findByName(name);
    }

    public List<ProjectView> findAllBy() {
        return projectsCrudRepository.findAllBy();
    }

    public void addTask(Long id, Long taskId) {
        Optional<Project> projectWithTask = projectsCrudRepository.findById(id);
        projectWithTask.ifPresent(p -> {
            Task task = taskService.getTask(taskId);
            p.getTasks().add(task);
            projectsCrudRepository.save(p);
        });
    }

    public void removeTask(Long id, Long taskId) {
        Optional<Project> projectWithTask = projectsCrudRepository.findById(id);
        projectWithTask.ifPresent(p -> {
            Task task = taskService.getTask(taskId);
            p.getTasks().remove(task);
            projectsCrudRepository.save(p);
        });
    }

    public void save(Project project) {
        projectsCrudRepository.save(project);
    }
}