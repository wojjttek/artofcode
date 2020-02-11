package pl.sztukakodu.works.tasks.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "attachment")
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String comment;

    public Attachment(String filename, String comment) {
        this.filename = filename;
        this.comment = comment;
    }
}