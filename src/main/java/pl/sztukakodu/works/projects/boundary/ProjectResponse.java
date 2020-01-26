package pl.sztukakodu.works.projects.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.sztukakodu.works.tasks.boundary.TaskResponse;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectResponse {
    private Long id;
    private String name;
    private List<TaskResponse> tasks;
}