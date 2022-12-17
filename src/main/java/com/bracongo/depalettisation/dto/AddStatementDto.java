/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.dto;

import com.bracongo.depalettisation.entities.Agent;
import com.bracongo.depalettisation.entities.Statement;
import com.bracongo.depalettisation.entities.StatementDetail;
import lombok.Data;

/**
 *
 * @author F.TSHIZUBU
 */
@Data
public class AddStatementDto {
    private Statement statement ;
    private StatementDetail statementDetailFull;
    private StatementDetail statementDetailEmpty;
    private String type;
    private Agent agent;
}
