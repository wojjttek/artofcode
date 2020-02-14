package pl.sztukakodu.works.tasks.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {
    private String title;
    private String author;
    private String description;
    private String attachmentComment;
    private Set<String> tags;
}