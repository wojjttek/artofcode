package pl.sztukakodu.works.tasks.boundary;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface StorageService {

    void saveFile(Long taskId, MultipartFile file) throws IOException;

    Resource loadFile(String fileName) throws MalformedURLException;
}
