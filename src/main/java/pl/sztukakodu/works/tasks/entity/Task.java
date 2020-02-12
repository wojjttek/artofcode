package pl.sztukakodu.works.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sztukakodu.works.entity.BaseEntity;
import pl.sztukakodu.works.tags.entity.Tag;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "task")
@NoArgsConstructor
@NamedEntityGraph(
        name="Task.detail",
        attributeNodes = { @NamedAttributeNode("attachments"), @NamedAttributeNode("tags")}
)
public class Task extends BaseEntity {
    private String title;
    private String author;
    private String description;
    @Column(name="created_at") private LocalDateTime createDateTime;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "task")
    private Set<Attachment> attachments = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "tag_task", joinColumns = @JoinColumn(name = "task"), inverseJoinColumns = @JoinColumn(name="tag"))
    private Set<Tag> tags = new HashSet<>();


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
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(new TagRef(tag));
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Task;
    }

}