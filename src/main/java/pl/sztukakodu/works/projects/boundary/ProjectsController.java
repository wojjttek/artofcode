package pl.sztukakodu.works.projects.boundary;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sztukakodu.works.projects.control.ProjectService;
import pl.sztukakodu.works.projects.entity.Project;
import pl.sztukakodu.works.tasks.boundary.TaskController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "/api/projects")
@RequiredArgsConstructor
public class ProjectsController {

    private final ProjectService projectService;
    private final TaskController taskController;

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getProjects(@RequestParam Optional<String> name) {
        log.info("Fetching all projects with filter: {}", name);
        return ResponseEntity.ok(toProjectResponse(name.map(projectService::findByName)
                .orElseGet(projectService::fetchAll)));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        log.info("Fetch project with id: {}", id);
        Project project = projectService.findById(id);
        return ResponseEntity.ok(new ProjectResponse(project.getId(), project.getName(), taskController.toTaskResponse(project.getTasks())));
    }

    public List<ProjectResponse> toProjectResponse(List<Project> projects) {
        return projects.stream()
                .map(project -> new ProjectResponse(project.getId(), project.getName(), taskController.toTaskResponse(project.getTasks())))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity addProject(@RequestBody CreateProjectRequest project) {
        log.info("Storing new project: {}", project);
        projectService.addProject(project.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        log.info("Deleting a project with id: {}", id);
        projectService.deleteProject(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateProject(@PathVariable Long id, @RequestBody CreateProjectRequest request) {
        log.info("Update a project with id {}", id);
        projectService.updateProject(id, request.getName());
        return ResponseEntity.accepted().build();
    }

    @PutMapping(path = "/{id}/addTask")
    public ResponseEntity addTask(@PathVariable Long id, @RequestBody AssignTaskRequest request) {
        log.info("Add task with id {} to project id {}", request.getTaskId(), id);
        projectService.addTask(id, request.getTaskId());
        return ResponseEntity.accepted().build();
    }

    @PutMapping(path = "/{id}/removeTask")
    public ResponseEntity removeTask(@PathVariable Long id, @RequestBody AssignTaskRequest request) {
        log.info("Remove task with id {} to project id {}", request.getTaskId(), id);
        projectService.removeTask(id, request.getTaskId());
        return ResponseEntity.accepted().build();
    }

}