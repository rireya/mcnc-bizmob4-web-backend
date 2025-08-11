package com.mcnc.bizmob.web.global.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandleController implements ErrorController {
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
    	return "index.html";
    }
    
    // @Override
    // public String getErrorPath() {
    //     return null;
    // }
}