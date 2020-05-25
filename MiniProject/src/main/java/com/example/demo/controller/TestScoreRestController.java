package com.example.demo.controller;

import com.example.demo.model.Subject;
import com.example.demo.model.TestScore;
import com.example.demo.repository.JDBCRepository.JDBCSubjectRepository;
import com.example.demo.repository.JDBCRepository.JDBCTestScoreRepository;
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
public class TestScoreRestController {

    @Autowired
    private JDBCTestScoreRepository testScoreRepository;

    @RequestMapping(value = "/testscores", method = RequestMethod.GET)
    public ResponseEntity<List<TestScore>> findAll() {
        List<TestScore> testScores = testScoreRepository.findAll();
        if (testScores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(testScores, HttpStatus.OK);
    }

    @RequestMapping(value = "/testScores/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestScore> getTestScoreById(
            @PathVariable("id") Long id) {
        Optional<TestScore> testScore = Optional.ofNullable(testScoreRepository.findById(id));

        if (!testScore.isPresent()) {
            return new ResponseEntity<>(testScore.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(testScore.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/testScores",
            method = RequestMethod.POST)
    public ResponseEntity<TestScore> createtestScore(
            @RequestBody TestScore testScore,
            UriComponentsBuilder builder) {
        testScoreRepository.save(testScore);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("testScores/{id}")
                .buildAndExpand(testScore.getId()).toUri());
        return new ResponseEntity<>(testScore, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/testScores/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<TestScore> updateTestScore(
            @PathVariable("id") Long id,
            @RequestBody TestScore testScore) {
        Optional<TestScore> currentTestScore = Optional.ofNullable(testScoreRepository.findById(id));

        if (!currentTestScore.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        testScoreRepository.update(currentTestScore.get());
        return new ResponseEntity<>(currentTestScore.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/testScores/delete/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<TestScore> deleteTestScore(
            @PathVariable("id") Long id) {
        Optional<TestScore> testScore = Optional.ofNullable(testScoreRepository.findById(id));
        if (!testScore.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        testScoreRepository.deleteById(testScore.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/students/{id}/scores", method = RequestMethod.GET)
    public ResponseEntity<List<TestScore>> findAllByStudentId(@PathVariable("id") Long id) {
        List<TestScore> testScores = testScoreRepository.findAllByIdStudent(id);
        if (testScores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(testScores, HttpStatus.OK);
    }}