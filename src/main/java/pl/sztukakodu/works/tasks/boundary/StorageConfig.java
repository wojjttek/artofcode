package pl.sztukakodu.works.tasks.boundary;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "storage")
@Data
public class StorageConfig {

    private String path;

}