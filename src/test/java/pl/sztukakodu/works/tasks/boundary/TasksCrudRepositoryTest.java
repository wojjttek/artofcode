package pl.sztukakodu.works.tasks.boundary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.sztukakodu.works.Clock;
import pl.sztukakodu.works.tasks.SystemClock;
import pl.sztukakodu.works.tasks.entity.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TasksCrudRepositoryTest {
    private Clock clock = new SystemClock();
    @Autowired
    TasksCrudRepository repository;
    @Test
    public void shouldLoadEntity() {
        //given
        Task task = new Task("Kupic lodowke", "Ja","Opis", clock.time());
        repository.save(task);
        //when
        List<Task> tasks = repository.findAll();
        //then
        assertThat(tasks.size()).isEqualTo(1);
        assertThat(tasks.get(0).getTitle()).isEqualToIgnoringCase("kupic lodowke");
    }

    @Test
    void shouldLoadView() {
        //given
        Task task = new Task("Kupic lodowke", "Ja","Opis", clock.time());
        repository.save(task);
        //when
        List<TaskView> tasks = repository.findAllBy();
        //then
        assertThat(tasks.size()).isEqualTo(1);
        assertThat(tasks.get(0).getTitle()).isEqualToIgnoringCase("kupic lodowke");
    }
}