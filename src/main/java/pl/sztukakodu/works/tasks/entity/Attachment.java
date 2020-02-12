package pl.sztukakodu.works.tasks.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sztukakodu.works.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "attachment")
@NoArgsConstructor
public class Attachment extends BaseEntity {
    private String filename;
    private String comment;

    public Attachment(String filename, String comment) {
        this.filename = filename;
        this.comment = comment;
    }
}