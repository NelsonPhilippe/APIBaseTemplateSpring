package fr.nelson.apibasetemplate.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppControllers {

    @GetMapping("/")
    public String index(){
        return "Hello world";
    }

    @GetMapping("/test")
    public String test(){
        return "Hello world";
    }
}
