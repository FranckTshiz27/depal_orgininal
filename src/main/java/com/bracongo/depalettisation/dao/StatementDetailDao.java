/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.StatementDetail;
import com.bracongo.depalettisation.enumerations.Trip;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author f.tshizubu
 */
@Repository
public interface StatementDetailDao extends JpaRepository<StatementDetail, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<StatementDetail> findStatementDetailByStatementDetailId(long id);

    int deleteStatementDetailByStatementDetailId(long id);

    @Query("select  sd from StatementDetail sd where sd.statement.statementId =:statementId order by sd.cargo")
    Page<StatementDetail> getStatementDetailsByStatement(Pageable page,@Param("statementId") long statementId);
    
    
     @Query("select  sd from StatementDetail sd where sd.statement.statementId =:statementId order by sd.cargo")
     List<StatementDetail> getStatements(@Param("statementId") long statementId);
     
     @Query("select  sd from StatementDetail sd where sd.statement.center.centerId =:centerId and sd.statement.trip=:trip and sd.driverAssignment.driverAssignmentId =:driverAssignmentId and "
             + " sd.statement.statementDate =:statementDate")
     StatementDetail getStatementDetailsByCenterAndTripAndStatementDateAndDriverAssigment(@Param("centerId") long centerId, @Param("trip") Trip trip, @Param("statementDate") String statementDate,@Param("driverAssignmentId") long driverAssigment);
}
