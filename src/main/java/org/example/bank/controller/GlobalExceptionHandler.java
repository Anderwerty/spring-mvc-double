package org.example.bank.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus
    public String handleException(Exception ex, Model model) {
        model.addAttribute("text", "Our team works on this issue");

//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error/error_page");
//        modelAndView.addObject("");
        return "error/error_page";
    }


    @ExceptionHandler(RuntimeException.class)
    public String handleException1(Exception ex, Model model) {
        model.addAttribute("text", "Our team works on this issue");

//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error/error_page");
//        modelAndView.addObject("");
        return "error/error_page";
    }
}
