package pl.sztukakodu.works.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Task {
    private long id;
    private String title;
    private String author;
    private String description;
    private LocalDateTime createDateTime;
    private List<String> files;
}