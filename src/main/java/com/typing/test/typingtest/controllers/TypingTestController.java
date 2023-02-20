/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.typing.test.typingtest.controllers;

import com.typing.test.typingtest.models.Paragraph;
import com.typing.test.typingtest.models.TypingTest;
import com.typing.test.typingtest.models.TypingTestResults;
import com.typing.test.typingtest.services.TypingTestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Timon
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TypingTestController {
    
    @Autowired
    TypingTestService typingTestService;
    
    @GetMapping("/get/paragraph")
    public ResponseEntity<Paragraph> getActiveParagraph() {
        Paragraph result = typingTestService.getActiveParagraph();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/get/results")
    public ResponseEntity<List<TypingTest>> getAllResults() {
        List<TypingTest> result = typingTestService.getAllResults();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/insert/paragraph/{newParagraph}")
    public ResponseEntity<String> setActiveParagraph(@PathVariable("newParagraph") String newParagraph) {
        Boolean success = typingTestService.setActiveParagraph(newParagraph);
        if (success) {
            return new ResponseEntity<>("New active paragraph has been set.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to set new active paragraph.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/insert/results")
    public ResponseEntity<String> setTypingResults(@RequestBody TypingTestResults typingTestResults) {
        Boolean success = typingTestService.saveTypingTestResults(typingTestResults);
        if (success) {
            return new ResponseEntity<>("Typing test results saved successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to save typing test results.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/delete/result/{id}")
    public ResponseEntity<String> deleteResult(@PathVariable("id") String id) {
        Boolean success = typingTestService.deleteResult(id);
        if (success) {
            return new ResponseEntity<>("Result set successfully deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete Result set.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
