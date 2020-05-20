package com.example.demo.controller;

import com.example.demo.model.Semester;
import com.example.demo.model.Student;
import com.example.demo.repository.JDBCRepository.JDBCSemesterRepository;
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
public class SemesterRestController {
    @Autowired
    private JDBCSemesterRepository semesterRepository;

    @RequestMapping(value = "/semesters", method = RequestMethod.GET)
    public ResponseEntity<List<Semester>> findAll() {
        List<Semester> semesters = semesterRepository.findAll();
        if (semesters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(semesters, HttpStatus.OK);
    }

    @RequestMapping(value = "/semesters/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Semester> getSemesterById(
            @PathVariable("id") Long id) {
        Optional<Semester> semester = Optional.ofNullable(semesterRepository.findById(id));

        if (!semester.isPresent()) {
            return new ResponseEntity<>(semester.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(semester.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/semesters",
            method = RequestMethod.POST)
    public ResponseEntity<Semester> createSemester(
            @RequestBody Semester semester,
            UriComponentsBuilder builder) {
        semesterRepository.save(semester);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("semesters/{id}")
                .buildAndExpand(semester.getId()).toUri());
        return new ResponseEntity<>(semester, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/semesters/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Semester> updateSemester(
            @PathVariable("id") Long id,
            @RequestBody Semester semester) {
        Optional<Semester> currentSemester = Optional.ofNullable(semesterRepository.findById(id));

        if (!currentSemester.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentSemester.get().setName(semester.getName());
        semesterRepository.update(currentSemester.get());
        return new ResponseEntity<>(currentSemester.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/semesters/delete/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Semester> deleteSemester(
            @PathVariable("id") Long id) {
        Optional<Semester> semester = Optional.ofNullable(semesterRepository.findById(id));
        if (!semester.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        semesterRepository.deleteById(semester.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
