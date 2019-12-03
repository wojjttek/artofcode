package pl.sztukakodu.works;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Clock {
    LocalDateTime time();
    default LocalDate date() {
        return LocalDate.now();
    };
}
