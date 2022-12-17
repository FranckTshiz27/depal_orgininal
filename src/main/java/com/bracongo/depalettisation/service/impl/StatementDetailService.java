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
import com.bracongo.depalettisation.dao.StatementDetailDao;
import com.bracongo.depalettisation.entities.StatementDetail;
import com.bracongo.depalettisation.service.IStatementDetail;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class StatementDetailService implements IStatementDetail {

    @Autowired
    private final StatementDetailDao statementDetailDao;

    @Override
    public int delete(long id) {

        Optional<StatementDetail> deletingDepalettization = statementDetailDao.findStatementDetailByStatementDetailId(id);

        if (deletingDepalettization != null) {
            statementDetailDao.deleteStatementDetailByStatementDetailId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public StatementDetail getStatementDetailById(long id) {
        return statementDetailDao.findStatementDetailByStatementDetailId(id).
                orElseThrow(() -> new CustomNotFoundException("Le détail d'une déclaration  dont le id " + id + " est introuvable"));
    }


    public StatementDetail save(StatementDetail statementDetail) {
       
        try {
            return statementDetailDao.save(statementDetail);
            
        } catch (Exception e) {
        }

        return null;
    }

    public StatementDetail updateDepalettization(StatementDetail statementDetail ,long statementDetailId) {

        Optional<StatementDetail> newDepalettization = statementDetailDao.findStatementDetailByStatementDetailId(statementDetailId);

        if (newDepalettization != null) {
           return  statementDetailDao.save(statementDetail);
        }
       
        return null;
    }

   


    @Override
    public Page<StatementDetail> getStatementDetails(Pageable page, long statementDetailId) {
        return statementDetailDao.getStatementDetailsByStatement(page, statementDetailId);
    }

    public List<StatementDetail> getStatementDetails(long statementDetailId) {
        return statementDetailDao.getStatements(statementDetailId);
    } 
    
     public List<StatementDetail> saveAllDetails(List<StatementDetail> statements) { 
      return statementDetailDao.saveAll(statements);
    }
}
