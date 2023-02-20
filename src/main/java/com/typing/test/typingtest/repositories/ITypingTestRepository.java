/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.typing.test.typingtest.repositories;

import com.typing.test.typingtest.models.Paragraph;
import com.typing.test.typingtest.models.TypingTest;
import com.typing.test.typingtest.models.TypingTestResults;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Timon
 */
public interface ITypingTestRepository{
    
    public void setDataSource(DataSource ds);
    
    public Paragraph getActiveParagraph();
    
    public List<TypingTest> getAllResults();
    
    public Boolean saveTypingTestResults(TypingTestResults typingTestResults);
    
    public Boolean setActiveParagraph(String newParagraph);
    
    public Boolean deleteResult(String id);
}
