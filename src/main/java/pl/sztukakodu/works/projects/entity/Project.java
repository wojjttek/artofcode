package pl.sztukakodu.works.projects.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import pl.sztukakodu.works.tasks.entity.Task;

import java.util.HashSet;
import java.util.Set;

@Data
@Table("project")
@NoArgsConstructor
public class Project {
    @Id
    private Long id;
    private String name;
    private Set<Task> tasks = new HashSet<>();

    public Project(String name) {
        this.name = name;
    }
}