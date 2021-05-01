package com.invoke.whiteTest.Service;

import de.test.consum.whiteTest.ExamListResponse;
import de.test.consum.whiteTest.ExamRequest;
import de.test.consum.whiteTest.StudentRequest;
import de.test.consum.whiteTest.WhiteTestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class WhiteTestService {

    private WebServiceTemplate serviceTemplate;

    @Autowired
    private Jaxb2Marshaller marshaller;


    // invoke from the web service the white test response object
    public WhiteTestResponse getWhiteTestResponse(StudentRequest request){
        serviceTemplate = new WebServiceTemplate(marshaller);
        WhiteTestResponse response = (WhiteTestResponse) serviceTemplate.marshalSendAndReceive("http://localhost:9095/ws",request);
        return response;
    }

    // Exam Request class was added manually
    public ExamListResponse getExamList(ExamRequest request){
        serviceTemplate = new WebServiceTemplate(marshaller);
        ExamListResponse examListResponse = (ExamListResponse) serviceTemplate.marshalSendAndReceive("http://localhost:9095/ws",request);
        return examListResponse;
    }
}
