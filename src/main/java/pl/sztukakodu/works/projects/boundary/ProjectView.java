package pl.sztukakodu.works.projects.boundary;

import java.util.Set;

public interface ProjectView {
    Long getId();
    String getName();
    Set<TaskView> getTasks();
}