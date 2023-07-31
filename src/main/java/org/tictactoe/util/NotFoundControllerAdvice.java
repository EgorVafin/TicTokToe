package org.tictactoe.util;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class NotFoundControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleException(NotFoundException e){
        ModelAndView mav = new ModelAndView("errors/not_found_page", HttpStatusCode.valueOf(404));
        mav.addObject("message", e.getMessage());
        return mav;
    }
}
