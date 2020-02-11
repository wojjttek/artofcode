package pl.sztukakodu.works.tasks.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sztukakodu.works.tags.entity.Tag;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="tag_task")
@NoArgsConstructor
public class TagRef {
    @Id private Long tag;

    public TagRef(Tag tag) {
        this.tag= tag.getId();
    }
}
