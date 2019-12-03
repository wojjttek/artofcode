package pl.sztukakodu.works.boundary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.sztukakodu.works.exceptions.NotFoundException;

import javax.validation.constraints.NotNull;

@ControllerAdvice
public class WorksExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    ResponseEntity notFoundHandler(@NotNull Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
