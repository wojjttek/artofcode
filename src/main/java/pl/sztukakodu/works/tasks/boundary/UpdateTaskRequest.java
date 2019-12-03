package pl.sztukakodu.works.tasks.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateTaskRequest {
    private String title;
    private String author;
    private String description;
}
