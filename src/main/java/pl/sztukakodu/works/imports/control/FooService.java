package pl.sztukakodu.works.imports.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class FooService {
    @Transactional
    public void sendMail() {
        log.info("Sending mail to user");
    }
}