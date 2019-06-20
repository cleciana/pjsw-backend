package com.example.demo.rest.controller;

import java.util.List;

import com.example.demo.exception.disciplina.ClassNotRegisteredException;
import com.example.demo.rest.model.Disciplina;
import com.example.demo.rest.service.DisciplinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassController
 */
@RestController
@RequestMapping({"v1/class"})
public class DisciplinaController {

    private DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService service) {
        this.disciplinaService = service;
    }

    @GetMapping("/all")
    public List<Disciplina> getAll(@RequestBody String subString) {
        // to com sono
        return null;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Disciplina> findById(@PathVariable int id) {
        Disciplina auxClass = this.disciplinaService.findById(id);

        if (auxClass == null) {
            throw new ClassNotRegisteredException("Sorry, class does not exist.");
        }
        return new ResponseEntity<Disciplina>(auxClass, HttpStatus.OK);
    }
}