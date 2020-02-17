package pl.sztukakodu.works.projects.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.sztukakodu.works.entity.BaseEntity;
import pl.sztukakodu.works.tasks.entity.Task;

import javax.persistence.*;
import java.util.Collection;
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
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Project extends BaseEntity {
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project")
    @org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
    private Set<Task> tasks = new HashSet<>();

    public Project(String name) {
        this.name = name;
    }

    public void addTasks(Collection<Task> tasks) {
        this.tasks.addAll(tasks);
    }
}