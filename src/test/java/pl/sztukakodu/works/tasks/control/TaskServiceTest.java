package pl.sztukakodu.works.tasks.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sztukakodu.works.tags.control.TagsService;
import pl.sztukakodu.works.tasks.entity.Task;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    TaskService taskService;
    @Autowired
    TagsService tagsService;

    @Test
    void optimisticLockingTest() {
        //given
        Task task = taskService.addTask("Kupic lodowke", "ja", "Pod zabudowe", Collections.emptySet());
        //when
        Task findOne = taskService.findById(task.getId());
        Task findOther = taskService.findById(task.getId());
        findOne.setTitle("KUPIC LODOWKE");
        findOther.setTitle("SPRZEDAC LODOWKE");
        taskService.save(findOne);
        taskService.save(findOther);
        //then
        Task actual = taskService.findById(task.getId());
        assertThat(actual.getTitle()).isEqualTo("SPRZEDAC LODOWKE");
    }
}