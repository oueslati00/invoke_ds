package com.invoke.whiteTest.Controllers;

import com.invoke.whiteTest.Service.WhiteTestService;
import de.test.consum.whiteTest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WhiteTestControle {

    @Autowired
    private WhiteTestService service;

    @GetMapping("/student/{id}/{examCode}")
    public List getStudent(@PathVariable("id") int id , @PathVariable("examCode") String examCode){
        StudentRequest studentRequest = new ObjectFactory().createStudentRequest();
        studentRequest.setExamCode(examCode);
        studentRequest.setStudentId(id);

        WhiteTestResponse whiteTestResponse = service.getWhiteTestResponse(studentRequest);
        List list = new ArrayList();
        if(whiteTestResponse.getCriteriaMismatch().isEmpty())
        {
            Student student =whiteTestResponse.getStudent();
            Exam exam =whiteTestResponse.getExam();
            String date =whiteTestResponse.getDate().toString();

            list.add(student);
            list.add(exam);
            list.add(date);
        }
        else
            list.addAll(whiteTestResponse.getCriteriaMismatch());
        return list;


    }
    @GetMapping("/exams")
    public List<Exam> getExams(){
        ExamRequest examrequest = new ExamRequest();
        ExamListResponse examListResponse = service.getExamList(examrequest);
        List<Exam> exams = examListResponse.getExamList();
        return exams;
    }


}
