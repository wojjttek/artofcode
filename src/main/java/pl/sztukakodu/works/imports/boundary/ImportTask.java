package pl.sztukakodu.works.imports.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ImportTask {
    private Long projectId;
    private String name;
    private String author;
    private String description;
    private Set<String> tags;
}