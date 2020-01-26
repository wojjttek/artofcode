package pl.sztukakodu.works.tasks.boundary;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.sztukakodu.works.tasks.entity.Task;

import java.util.List;

interface TasksCrudRepository extends CrudRepository<Task, Long> {
    @Modifying
    @Query("update Task set title = :title, author = :author, description = :description where id = :id")
    void updateTitleDescription(@Param("id") Long id, @Param("title") String title, @Param("description") String description, @Param("author") String author);

    @Query("select * from Task t where upper(t.title) like '%' || upper(:title) || '%'")
    List<Task> findByTitle(@Param("title") String title);

    @Query("select * from Task t join Attachment a on t.id = a.task")
    List<Task> findWithAttachments();

    @Modifying
    @Query("select * from Task where project = :projectId")
    Task findByProjectId(Long projectId);
}
