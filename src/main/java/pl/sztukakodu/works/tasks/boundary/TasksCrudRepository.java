package pl.sztukakodu.works.tasks.boundary;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sztukakodu.works.tasks.entity.Task;

import java.util.List;

interface TasksCrudRepository extends JpaRepository<Task, Long> {
    @Modifying
    @Query("update Task set title = :title, author = :author, description = :description where id = :id")
    void updateTitleDescription(@Param("id") Long id, @Param("title") String title, @Param("description") String description, @Param("author") String author);

    List<Task> findAllByTitleLike(String title);

    @EntityGraph(value="Task.detail", type = EntityGraph.EntityGraphType.LOAD)
    List<Task> findAll();

    List<TaskView> findAllBy();

//    @Query("select * from Task t join Attachment a on t.id = a.task")
//    List<Task> findWithAttachments();

    @Modifying
    @Query("from Task where project = :projectId")
    Task findByProjectId(Long projectId);
}