package pl.sztukakodu.works.tasks.boundary;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.sztukakodu.works.tasks.control.TaskService;
import pl.sztukakodu.works.tasks.entity.Task;

import java.io.IOException;

@Slf4j
@Controller
@AllArgsConstructor
public class TaskViewController {
    private final TaskService taskService;

    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("tasks", taskService.fetchAll());
        model.addAttribute("newTask", new CreateTaskRequest());
        return "home";
    }

    @PostMapping("/tasks")
    public String addTask(@ModelAttribute("newTask") CreateTaskRequest request,
                          @RequestParam MultipartFile attachment) throws IOException {
        Task task = taskService.addTask(request.getTitle(), request.getAuthor(), request.getDescription());
        if (attachment!=null && !attachment.isEmpty()) {
            taskService.addAttachment(task.getId(), attachment, request.getAttachmentComment());
        }

        return "redirect:/";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}