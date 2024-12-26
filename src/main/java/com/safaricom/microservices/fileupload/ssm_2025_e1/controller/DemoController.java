package com.safaricom.microservices.fileupload.ssm_2025_e1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    //csrf protection for POST Requests is in place by default
    @PostMapping("/demo")
    public String demo(){
        return "Demo";
    }

}
