package pl.sztukakodu.works.projects.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sztukakodu.works.exceptions.NotFoundException;
import pl.sztukakodu.works.projects.boundary.ProjectsCrudRepository;
import pl.sztukakodu.works.projects.entity.Project;
import pl.sztukakodu.works.tasks.control.TaskService;

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

    public void addTask(Long id, Long taskId) {
        Project projectWithTask = projectsCrudRepository.findByTaskId(taskId);
        Optional.ofNullable(projectWithTask).ifPresent(p -> {
            projectsCrudRepository.removeProjectFromTask(p.getId(), taskId);
        });
        projectsCrudRepository.addProjectToTask(id, taskId);
    }

    public void removeTask(Long id, Long taskId) {
        projectsCrudRepository.removeProjectFromTask(id, taskId);
    }
}