package pl.sztukakodu.works.tasks.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import pl.sztukakodu.works.tags.entity.Tag;

@Data
@Table("tag_task")
@NoArgsConstructor
public class TagRef {
    private Long tag;

    public TagRef(Tag tag) {
        this.tag= tag.getId();
    }
}
