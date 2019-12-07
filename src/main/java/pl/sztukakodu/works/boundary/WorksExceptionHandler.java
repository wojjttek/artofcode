package pl.sztukakodu.works.boundary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.sztukakodu.works.exceptions.NotFoundException;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@ControllerAdvice
public class WorksExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    ResponseEntity notFoundHandler(@NotNull Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    ResponseEntity illegalArgumentHandler(@NotNull Exception e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({IOException.class})
    ResponseEntity ioExceptionHandler(@NotNull Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}