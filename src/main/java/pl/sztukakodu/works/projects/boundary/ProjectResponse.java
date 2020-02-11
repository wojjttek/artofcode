package pl.sztukakodu.works.projects.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import pl.sztukakodu.works.tasks.boundary.TaskResponse;

import java.util.List;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ProjectResponse {
    private Long id;
    private String name;
    private List<TaskResponse> tasks;
}