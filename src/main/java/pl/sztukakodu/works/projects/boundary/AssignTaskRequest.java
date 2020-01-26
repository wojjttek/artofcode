package pl.sztukakodu.works.projects.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignTaskRequest {
    private Long taskId;
}
