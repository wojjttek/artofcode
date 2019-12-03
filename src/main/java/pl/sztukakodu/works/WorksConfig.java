package pl.sztukakodu.works;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sztukakodu.works.tasks.SystemClock;
import pl.sztukakodu.works.tasks.boundary.FileSystemStorageService;
import pl.sztukakodu.works.tasks.boundary.StorageConfig;
import pl.sztukakodu.works.tasks.boundary.StorageService;

import java.nio.file.Paths;

@Configuration
public class WorksConfig {

    private final StorageConfig storageConfig;

    public WorksConfig(StorageConfig storageConfig) {
        this.storageConfig = storageConfig;
    }

    @Bean
    public Clock clock() {
        return new SystemClock();
    }

    @Bean
    public StorageService storageService() {
        return new FileSystemStorageService(Paths.get(storageConfig.getPath()));
    }
}
