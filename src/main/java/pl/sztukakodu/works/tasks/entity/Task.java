package pl.sztukakodu.works.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import pl.sztukakodu.works.tags.entity.Tag;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Table("task")
@RequiredArgsConstructor
public class Task {
    @Id
    private long id;
    private String title;
    private String author;
    private String description;
    @Column("created_at") private LocalDateTime createDateTime;
    private Set<Attachment> attachments = new HashSet<>();
    private Set<TagRef> tagRefs = new HashSet<>();


    public Task(String title, String author, String description, LocalDateTime time) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.createDateTime = time;
    }

    public void addAttachment(String fileName, String comment) {
        attachments.add(new Attachment(fileName, comment));
    }

    public void addTag(Tag tag) {
        tagRefs.add(new TagRef(tag));
    }

    public void removeTag(Tag tag) {
        tagRefs.remove(new TagRef(tag));
    }

}