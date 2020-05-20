package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.demo.model.Class;
import com.example.demo.repository.JDBCRepository.JDBCClassRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {("*")})
@RestController
public class ClassRestController {
    @Autowired
    private JDBCClassRepository classRepository;

    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public ResponseEntity<List<Class>> findAll1() {
        List<Class> classes = classRepository.findAll();
        if (classes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @RequestMapping(value = "/classes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Class> getClassById(
            @PathVariable("id") Long id) {
        Optional<Class> _class = Optional.ofNullable(classRepository.findById(id));

        if (!_class.isPresent()) {
            return new ResponseEntity<>(_class.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(_class.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/classes",
            method = RequestMethod.POST)
    public ResponseEntity<Class> createClass(
            @RequestBody Class _class,
            UriComponentsBuilder builder) {
        classRepository.save(_class);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/classes/{id}")
                .buildAndExpand(_class.getId()).toUri());
        return new ResponseEntity<>(_class, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/classes/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Class> updateClass(
            @PathVariable("id") Long id,
            @RequestBody Class _class) {
        Optional<Class> currentClass = Optional.ofNullable(classRepository.findById(id));
        if (!currentClass.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentClass.get().setName(_class.getName());
        classRepository.update(currentClass.get());
        return new ResponseEntity<>(currentClass.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/classes/delete/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Class> deleteClass(
            @PathVariable("id") Long id) {
        Optional<Class> _class = Optional.ofNullable(classRepository.findById(id));
        if (!_class.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classRepository.deleteById(_class.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
