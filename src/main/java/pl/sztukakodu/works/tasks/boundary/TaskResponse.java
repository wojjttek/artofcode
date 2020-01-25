package pl.sztukakodu.works.tasks.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.sztukakodu.works.tags.entity.Tag;
import pl.sztukakodu.works.tasks.entity.Task;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class TaskResponse {
    private long id;
    private String title;
    private String author;
    private String description;
    private LocalDateTime createdAt;
    private Set<AttachmentResponse> attachments;
    private Set<TagResponse> tag;

    public static TaskResponse from(Task t, Set<Tag> tags) {
        return new TaskResponse(t.getId(), t.getTitle(), t.getAuthor(), t.getDescription(), t.getCreateDateTime(),
                t.getAttachments().stream()
                        .map(attachment -> new AttachmentResponse(attachment.getFilename(), attachment.getComment()))
                        .collect(Collectors.toSet()),
                tags.stream()
                        .map(TagResponse::new)
                        .collect(Collectors.toSet()));
    }
}
