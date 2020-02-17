package pl.sztukakodu.works.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.sztukakodu.works.entity.BaseEntity;
import pl.sztukakodu.works.tags.entity.Tag;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "task")
@NoArgsConstructor
@NamedEntityGraph(
        name = "Task.detail",
        attributeNodes = {@NamedAttributeNode("attachments"), @NamedAttributeNode("tags")}
)
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Task extends BaseEntity {
    private String title;
    private String author;
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createDateTime;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "task")
    @org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
    private Set<Attachment> attachments = new HashSet<>();
    @ManyToMany(cascade = {
            CascadeType.MERGE, CascadeType.PERSIST
    })
    @JoinTable(name = "tag_task", joinColumns = @JoinColumn(name = "task"), inverseJoinColumns = @JoinColumn(name = "tag"))
    @org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
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

    public void addTags(Collection<Tag> tags) {
        this.tags.addAll(tags);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

}