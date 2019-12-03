package pl.sztukakodu.works.tasks.boundary;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.tasks")
@Data
public class TaskConfig {

    private String endpointMessage;


    static class Number {
        private volatile Long value = null;


    }


}