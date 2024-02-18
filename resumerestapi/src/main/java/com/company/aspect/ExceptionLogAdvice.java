package com.company.aspect;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionLogAdvice {

    private static final Logger LOG = Logger.getLogger(ExceptionLogAdvice.class.getName());

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleValidationException(final Exception ex) {
        System.out.println("Error AOP sehifesine geldi!");
        LOG.log(Level.SEVERE, null, ex);
        return "error";
    }
}