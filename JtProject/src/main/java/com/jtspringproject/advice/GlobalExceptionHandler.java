package com.jtspringproject.advice;

import com.jtspringproject.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException ex) {
        logger.error("ResourceNotFoundException: URL={} and Message={}", request.getRequestURL(), ex.getMessage());
        
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("statusCode", "404 Not Found");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(HttpServletRequest request, Exception ex) {
        logger.error("Request: " + request.getRequestURL() + " raised " + ex);

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("statusCode", "500 Internal Server Error");
        modelAndView.addObject("message", "An unexpected error occurred. Please try again later.");
        // For security, we don't expose raw exception messages to the user in production.
        // We log it for developers.
        return modelAndView;
    }
} 