package by.itstep.shop.controller;

import by.itstep.shop.security.exceptions.JwtAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleRuntimeException(Exception ex) {
        Map<Object, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleForbiddenException(Exception ex) {
        Map<Object, Object> body = new HashMap<>();
        body.put("message", "Access Denied");
        logger.error("Access Denied");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}