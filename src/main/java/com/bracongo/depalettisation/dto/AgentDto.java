/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.dto;

import com.bracongo.depalettisation.entities.Agent;
import com.bracongo.depalettisation.entities.Affectation;
import lombok.Data;

/**
 *
 * @author F.TSHIZUBU
 */
@Data
public class AgentDto {
    Agent agent;
    Affectation affectation;
}
