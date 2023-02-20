/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.typing.test.typingtest.models;

import java.sql.Date;
import lombok.Data;

/**
 *
 * @author Timon
 */
@Data
public class TypingTest {
    private String resultsId;
    private String paragraphId;
    private String wpm;
    private String accuracy;
    private String timeRemaining;
    private Date completedDate;
}
