package pl.sztukakodu.works.tasks;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.tasks")
@Data
public class TaskConfig {

    private String endpointMessage;

}
