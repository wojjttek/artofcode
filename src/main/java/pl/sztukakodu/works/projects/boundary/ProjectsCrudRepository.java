package pl.sztukakodu.works.projects.boundary;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sztukakodu.works.projects.entity.Project;

import java.util.List;

public interface ProjectsCrudRepository extends JpaRepository<Project, Long> {
    @Query("from Project where upper(name) like '%' || upper(:name) || '%'")
    List<Project> findByName(String name);

    @EntityGraph(value = "Project.all", type = EntityGraph.EntityGraphType.FETCH)
    List<Project> findAll();

    List<ProjectView> findAllBy();
}