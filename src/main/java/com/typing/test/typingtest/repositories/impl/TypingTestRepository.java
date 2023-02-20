/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.typing.test.typingtest.repositories.impl;

import com.typing.test.typingtest.models.Paragraph;
import com.typing.test.typingtest.models.TypingTest;
import com.typing.test.typingtest.models.TypingTestResults;
import com.typing.test.typingtest.repositories.ITypingTestRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 *
 * @author Timon
 */
public class TypingTestRepository implements ITypingTestRepository {
    private DataSource dataSource;
    private JdbcTemplate template;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Paragraph getActiveParagraph() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("SP_Get_Typing_Test_Paragraph");
        Map<String, Object> out = jdbcCall.execute();
        
        Paragraph paragraph = new Paragraph();
        paragraph.setParagraphId((String) out.get("paragraphid_out"));
        paragraph.setParagraph((String) out.get("paragraph_out"));
        paragraph.setCreationDate((Date) out.get("creation_date_out"));
        
        return paragraph;
    }
    
    @Override
    public List<TypingTest> getAllResults() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("SP_Get_Typing_Test_Results");
        Map<String, Object> out = jdbcCall.execute();
        
        List<Map<String, Object>> rows = (List<Map<String, Object>>) out.get("#result-set-1");
        List<TypingTest> results = new ArrayList<>();
        
        for (Map<String, Object> row : rows) {
            TypingTest typingTest = new TypingTest();
            typingTest.setResultsId((String) row.get("results_id"));
            typingTest.setParagraphId((String) row.get("paragraph_id"));
            typingTest.setWpm((String) row.get("wpm"));
            typingTest.setAccuracy((String) row.get("accuracy"));
            typingTest.setTimeRemaining((String) row.get("time_remaining"));
            typingTest.setCompletedDate((Date) row.get("completed_date"));
            results.add(typingTest);
        }
        
        return results;
    }
    
    @Override
    public Boolean saveTypingTestResults(TypingTestResults typingTestResults) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("SP_Save_Typing_Test_Results");
        SqlParameterSource pParagraphId = new MapSqlParameterSource().addValue("pParagraphId", typingTestResults.getParagraphId());
        SqlParameterSource pWpm = new MapSqlParameterSource().addValue("pWpm", typingTestResults.getWpm());
        SqlParameterSource pAccuracy = new MapSqlParameterSource().addValue("pAccuracy", typingTestResults.getAccuracy());
        SqlParameterSource pRemainingTime = new MapSqlParameterSource().addValue("pRemainingTime", typingTestResults.getTimeRemaining());
        Map<String, Object> out = jdbcCall.execute(
                pParagraphId.getValue("pParagraphId"), 
                pWpm.getValue("pWpm"), 
                pAccuracy.getValue("pAccuracy"), 
                pRemainingTime.getValue("pRemainingTime")
        );
        
        return out != null;
    }
    
    @Override
    public Boolean setActiveParagraph(String newParagraph) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("SP_Insert_New_Typing_Test_Paragraph");
        SqlParameterSource pParagraph = new MapSqlParameterSource().addValue("pParagraph", newParagraph);
        Map<String, Object> out = jdbcCall.execute(pParagraph.getValue("pParagraph"));
        
        return out != null;
    }
    
    @Override
    public Boolean deleteResult(String id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("SP_Delete_Results");
        SqlParameterSource pResultsId = new MapSqlParameterSource().addValue("pResultsId", id);
        Map<String, Object> out = jdbcCall.execute(pResultsId.getValue("pResultsId"));
        
        return out != null;
    }
}
