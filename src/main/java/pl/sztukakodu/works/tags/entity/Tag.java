package pl.sztukakodu.works.tags.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("tag")
@Data
@AllArgsConstructor
public class Tag {
    @Id
    private Long id;
    private String name;
}
