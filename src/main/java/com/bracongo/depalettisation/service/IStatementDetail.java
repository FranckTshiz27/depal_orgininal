/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.StatementDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
public interface IStatementDetail {

    public StatementDetail save(StatementDetail depalettization);

    public int delete(long id);

    public StatementDetail getStatementDetailById(long id);

    public Page<StatementDetail> getStatementDetails(Pageable page, long depalettizationId);
}
