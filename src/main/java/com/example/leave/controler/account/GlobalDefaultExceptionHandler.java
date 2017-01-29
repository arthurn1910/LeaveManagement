package com.example.leave.controler.account;

import jdk.nashorn.internal.parser.JSONParser;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by arthurn on 29.01.17.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    Logger log = Logger.getLogger(GlobalDefaultExceptionHandler.class.getName());

    @ExceptionHandler(value = ObjectOptimisticLockingFailureException.class)
    public ResponseEntity
    optimisticLock(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        log.warn("Exception optimistic: "+ e.toString(),e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONParser.quote("errorOptimistic"));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        log.warn("Exception: "+ e.toString(),e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONParser.quote("error2"));
    }
}
