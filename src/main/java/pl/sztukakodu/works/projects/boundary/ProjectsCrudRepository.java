package pl.sztukakodu.works.projects.boundary;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import pl.sztukakodu.works.projects.entity.Project;

import java.util.List;

public interface ProjectsCrudRepository extends CrudRepository<Project, Long> {
    @Query("select * from Project where upper(name) like '%' || upper(:name) || '%'")
    List<Project> findByName(String name);

    @Query("select * from Project p join Task t on p.id = t.project where t.id = :taskId")
    Project findByTaskId(Long taskId);

    @Modifying
    @Query("update Task set project = null where id = :taskId and project = :id")
    void removeProjectFromTask(Long id, Long taskId);

    @Modifying
    @Query("update Task set project = :id where id = :taskId ")
    void addProjectToTask(Long id, Long taskId);
}