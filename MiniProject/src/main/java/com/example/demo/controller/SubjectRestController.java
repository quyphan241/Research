package com.example.demo.controller;

import com.example.demo.model.Subject;
import com.example.demo.repository.JDBCRepository.JDBCSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {("*")})
@RestController
public class SubjectRestController {
    @Autowired
    private JDBCSubjectRepository subjectRepository;

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public ResponseEntity<List<Subject>> findAll() {
        List<Subject> subjects = subjectRepository.findAll();
        if (subjects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subject> getSubjectById(
            @PathVariable("id") Long id) {
        Optional<Subject> subject = Optional.ofNullable(subjectRepository.findById(id));

        if (!subject.isPresent()) {
            return new ResponseEntity<>(subject.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subject.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects",
            method = RequestMethod.POST)
    public ResponseEntity<Subject> createSubject(
            @RequestBody Subject subject,
            UriComponentsBuilder builder) {
        subjectRepository.save(subject);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("subjects/{id}")
                .buildAndExpand(subject.getId()).toUri());
        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subjects/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Subject> updateSubject(
            @PathVariable("id") Long id,
            @RequestBody Subject subject) {
        Subject currentSubject = subjectRepository.findById(id);
        if (currentSubject ==null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentSubject.setName(subject.getName());
        subjectRepository.update(currentSubject);
        return new ResponseEntity<>(currentSubject, HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects/delete/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Subject> deleteSubject(
            @PathVariable("id") Long id) {
        Optional<Subject> subject = Optional.ofNullable(subjectRepository.findById(id));
        if (!subject.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        subjectRepository.deleteById(subject.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

