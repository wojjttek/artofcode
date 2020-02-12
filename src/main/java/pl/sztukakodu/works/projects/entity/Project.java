package pl.sztukakodu.works.projects.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sztukakodu.works.tasks.entity.Task;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "project")
@NoArgsConstructor
@NamedEntityGraph(name = "Project.all",
        attributeNodes = @NamedAttributeNode(value = "tasks", subgraph = "subgraph.task"),
        subgraphs = {
                @NamedSubgraph(name = "subgraph.task", type=Task.class, attributeNodes = { @NamedAttributeNode("attachments"), @NamedAttributeNode("tags")})
        })
@NamedEntityGraph(name = "Project.short",
        attributeNodes = {@NamedAttributeNode("tasks")})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project")
    private Set<Task> tasks = new HashSet<>();

    public Project(String name) {
        this.name = name;
    }
}