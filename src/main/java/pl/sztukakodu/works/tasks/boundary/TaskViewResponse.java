package pl.sztukakodu.works.tasks.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.sztukakodu.works.tasks.entity.Task;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskViewResponse {
    private long id;
    private String title;
    private String author;
    private String description;
    private LocalDateTime createdAt;
    public static TaskViewResponse from(Task t) {
        return new TaskViewResponse(t.getId(), t.getTitle(), t.getAuthor(), t.getDescription(), t.getCreateDateTime());
    }
}
