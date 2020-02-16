package pl.sztukakodu.works.imports.control;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sztukakodu.works.imports.boundary.ImportProject;
import pl.sztukakodu.works.imports.boundary.ImportTask;
import pl.sztukakodu.works.projects.control.ProjectService;
import pl.sztukakodu.works.tags.control.TagsService;
import pl.sztukakodu.works.tasks.control.TaskService;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class ImportServiceTest {
    @Autowired
    ImportService importService;
    @Autowired
    ProjectService projectService;
    @Autowired
    TaskService taskService;
    @Autowired
    TagsService tagsService;

    @Test
    void startImport() {
        //given
        long kursPid = 1L;
        ImportProject p1 = new ImportProject(kursPid, "Szkola springa");
        ImportTask t1 = new ImportTask(kursPid, "Nagrac modul 7", "ja", "Lekcja 4", tags("pilne", "focus"));
        ImportTask t2 = new ImportTask(kursPid, "Odpisac na pytania", "ja", "Desc", tags("pilne", "cykliczne"));
        long agdPid = 2L;
        ImportProject p2 = new ImportProject(agdPid, "Remont mieszkania");
        ImportTask t3 = new ImportTask(agdPid, "Kupic lodówkę", "ja", "Lekcja 4", tags("na mieście"));
        ImportTask t4 = new ImportTask(agdPid, "Zadzwonic do stolarza", "ja", "Desc", tags("pilne", "telefon"));
//        ImportTask poison = new ImportTask(null, "Missing projectId", "ja", "Desc", tags());

        try {
//            importService.startImport(asList(t1, t2, poison, t3, t4), asList(p1, p2));
            importService.startImport(asList(t1, t2, t3, t4), asList(p1, p2));
        } catch (Exception e) {
            log.error("Something not expected hapenned.", e);
        }

        assertThat(taskService.fetchAll().size()).isEqualTo(4);
        assertThat(projectService.fetchAll().size()).isEqualTo(2);
        assertThat(tagsService.findAll().size()).isEqualTo(5);

    }

    public static Set<String> tags(String... tags) {
        return new HashSet<>(asList(tags));
    }
}