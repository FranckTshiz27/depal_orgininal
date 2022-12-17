/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.Statement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
public interface IStatement {

    

    public int delete(long id);

    public Statement getStatementById(long id);

    public Page<Statement> getStatements(Pageable page);
}
