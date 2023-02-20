/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.typing.test.typingtest.services;

import com.typing.test.typingtest.models.Paragraph;
import com.typing.test.typingtest.models.TypingTest;
import com.typing.test.typingtest.models.TypingTestResults;
import com.typing.test.typingtest.repositories.ITypingTestRepository;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Timon
 */
@Service
public class TypingTestService {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    ITypingTestRepository query = (ITypingTestRepository)context.getBean("connect");
    
    public Paragraph getActiveParagraph(){
        Paragraph paragraph = query.getActiveParagraph();
        return paragraph;
    }
    
    public List<TypingTest> getAllResults(){
        List<TypingTest> results = query.getAllResults();
        return results;
    }
    
    public Boolean saveTypingTestResults(TypingTestResults typingTestResults){
        Boolean success = query.saveTypingTestResults(typingTestResults);
        return success;
    }
    
    public Boolean setActiveParagraph(String newParagraph){
        Boolean success = query.setActiveParagraph(newParagraph);
        return success;
    }
    
    public Boolean deleteResult(String id){
        Boolean success = query.deleteResult(id);
        return success;
    }
}
