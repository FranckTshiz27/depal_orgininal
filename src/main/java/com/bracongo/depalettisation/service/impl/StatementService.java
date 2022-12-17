/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.exception.CustomNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;
import com.bracongo.depalettisation.dao.StatementDao;
import com.bracongo.depalettisation.dao.StatementDetailDao;
import com.bracongo.depalettisation.entities.Statement;
import com.bracongo.depalettisation.enumerations.Trip;
import com.bracongo.depalettisation.service.IStatement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class StatementService implements IStatement {

    @Autowired
    private final StatementDao statementDao;
    @Autowired
    private final StatementDetailDao statementDetailDao;

  
    
    @Override
    public int delete(long id) {

        Optional<Statement> deletingStatement = statementDao.findStatementByStatementId(id);

        if (deletingStatement != null) {
            statementDao.deleteStatementByStatementId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public Statement getStatementById(long id) {
        return statementDao.findStatementByStatementId(id).
                orElseThrow(() -> new CustomNotFoundException("Le statement dont le id " + id + " est introuvable"));
    }
   
    public Statement updateStatement(Statement statement, long statementId) {

        Optional<Statement> newStatement = statementDao.findStatementByStatementId(statementId);

        if (newStatement != null) {
            return statementDao.save(statement);
        }

        return null;
    }

    @Override
    public Page<Statement> getStatements(Pageable page) {
        return statementDao.getStatementsOrderByDate(page);
    }

    public Page<Statement> getStatementsByDate(String date, Pageable page) {
        return statementDao.getStatementsByDateOrderByDate(date, page);
    }
        
    public Page<Statement> getStatementsByCenterDate(long centerId, String date, Pageable page) {
        return statementDao.getStatementsByCenterDateOrderByDate(centerId, date, page);
    }

    public Page<Statement> getStatementsByTrip(Trip trip, Pageable page) {
        return statementDao.getStatementsByTripOrderByDate(trip, page);
    }
    
    public Page<Statement> getStatementsByCenterTrip(long centerId, Trip trip, Pageable page) {
        return statementDao.getStatementsByCenterTripOrderByDate(centerId, trip, page);
    }  
    
    public Page<Statement> getStatementsByCenterDateTrip(long centerId, String date, Trip trip, Pageable page) {
        return statementDao.getStatementsByCenterDateTripOrderByDate(centerId, date, trip, page);
    }
    
    public Page<Statement> getStatementsByDateTrip(String date, Trip trip, Pageable page) {
        return statementDao.getStatementsByDateTripOrderByDate(date, trip, page);
    }

    public Page<Statement> getStatementsByCenter(long centerId, Pageable page) {
        return statementDao.getStatementsFromCenterOrderByDate(centerId, page);
    }

    public Page<Statement> getStatementsByCenterId(Pageable page, long centerId) {
        return statementDao.getStatementsOrderByDateByCenterId(page, centerId);
    }
 
}
