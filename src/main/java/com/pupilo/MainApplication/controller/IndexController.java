package com.pupilo.MainApplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(value = "/")
    public ResponseEntity<Object> indexPage() {
        return new ResponseEntity<>("Index page", HttpStatus.OK);
    }
    
}
