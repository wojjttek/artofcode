package pl.sztukakodu.works.tasks;

import pl.sztukakodu.works.Clock;

import java.time.LocalDateTime;

public class SystemClock implements Clock {
    public LocalDateTime time() {
        return LocalDateTime.now();
    }
}
