package com.example.demo.controller.excel;

import com.example.demo.model.Class;
import com.example.demo.model.Subject;
import com.example.demo.model.TestScore;
import com.example.demo.report.ClassResultExport;
import com.example.demo.report.ExcelScoreReport;
import com.example.demo.repository.JDBCRepository.JDBCClassRepository;
import com.example.demo.repository.JDBCRepository.JDBCSubjectRepository;
import com.example.demo.repository.JDBCRepository.JDBCTestScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static com.example.demo.report.CreateScoreReport.writeExcel;

@Controller
public class ExportExcelController {
    @Autowired
    JDBCTestScoreRepository testScoreRepository;
    @Autowired
    JDBCSubjectRepository subjectRepository;
    @Autowired
    JDBCClassRepository classRepository;


    @GetMapping("/excel/score/{id_class}/{id_subject}")
    public void downLoadExcelFile(HttpServletResponse response, @PathVariable Long id_class, @PathVariable Long id_subject) throws IOException {
        String excelFilePath = "D:\\ExcelReport\\testScores" +((int)(Math.random() * 100000))+".xlsx";
        List<TestScore> testScores = testScoreRepository.findAllByIdClassAndIdSubject(id_class, id_subject);
        writeExcel( testScores, excelFilePath);
    }

    @GetMapping(value = "/scores/report/{id_class}/{id_subject}")
    public ResponseEntity<InputStreamResource> excelScoresReport(@PathVariable Long id_class, @PathVariable Long id_subject) throws IOException {
        List<TestScore> testScores = testScoreRepository.findAllByIdClassAndIdSubject(id_class, id_subject);
        Class _class = classRepository.findById(id_class);
        Subject subject = subjectRepository.findById(id_subject);

        ByteArrayInputStream in = ExcelScoreReport.scoresToExcel(testScores, _class, subject);
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=scores.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

//    @GetMapping(value = "/scores/report/{id_class}")
//    public ResponseEntity<InputStreamResource> excelClassReport(@PathVariable Long id_class) throws IOException {
//        Class _class = classRepository.findById(id_class);
//        List<Subject> subjects = subjectRepository.findAll();
//        for (Subject subject : subjects) {
//            List<TestScore> testScores = testScoreRepository.findAllByIdClassAndIdSubject(id_class, subject.getId());
//            ByteArrayInputStream in = ClassResultExport.scoresToExcel(testScores, _class, subjects);
//            // return IOUtils.toByteArray(in);
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "attachment; filename=scores.xlsx");
//
//            return ResponseEntity
//                    .ok()
//                    .headers(headers)
//                    .body(new InputStreamResource(in));
//        }
//    }
}