package pl.sztukakodu.works.tasks.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class TaskResponse {
    private long id;
    private String title;
    private String author;
    private String description;
    private LocalDateTime createdAt;
    private List<String> files;
}
