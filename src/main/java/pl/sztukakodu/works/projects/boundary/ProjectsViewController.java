package pl.sztukakodu.works.projects.boundary;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sztukakodu.works.projects.control.ProjectService;

@Slf4j
@Controller
@AllArgsConstructor
public class ProjectsViewController {
    private final ProjectService projectService;

    @GetMapping("/projects/")
    public String welcome(Model model) {
        model.addAttribute("projects", projectService.fetchAll());
        model.addAttribute("newProject", new CreateProjectRequest());
        return "projects";
    }

    @PostMapping("/projects/add")
    public String addProject(@ModelAttribute("newProject") CreateProjectRequest request) {
        projectService.addProject(request.getName());
        return "redirect:/";
    }

    @PostMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/";
    }

    @PostMapping("/projects/update/{id}")
    public String updateProject(@PathVariable Long id, @ModelAttribute("newProject")  CreateProjectRequest request) {
        projectService.updateProject(id, request.getName());
        return "redirect:/";
    }
}