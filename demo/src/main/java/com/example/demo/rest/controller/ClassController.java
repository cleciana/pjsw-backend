package com.example.demo.rest.controller;

import java.util.List;

import com.example.demo.rest.model.Class;
import com.example.demo.rest.service.ClassService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassController
 */
@RestController
@RequestMapping({"v1/class"})
public class ClassController {

    private ClassService classService;

    public ClassController(ClassService service) {
        this.classService = service;
    }

    @GetMapping("/all")
    public List<Class> getAll(@RequestBody String subString) {
        // to com sono
        return null;
    }
}