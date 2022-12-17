/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

//import com.bracongo.depalettisation.dto.Perform;
import com.bracongo.depalettisation.entities.Statement;
import com.bracongo.depalettisation.enumerations.Trip;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author f.tshizubu
 */
@Repository
public interface StatementDao extends JpaRepository<Statement, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<Statement> findStatementByStatementId(Long id);

    int deleteStatementByStatementId(Long id);

    @Query("select s from Statement s order by s.statementDate desc , s.trip asc ")
    Page<Statement> getStatementsOrderByDate(Pageable page);

    @Query("select s from Statement s where s.statementId =:id")
    Statement getStatementByStatementId(@Param("id") long id);

    @Query("select s from Statement s  "
            + " where s.center.centerId =:centerId order by s.statementDate desc, s.createdAt desc")
    Page<Statement> getStatementsOrderByDateByCenterId(Pageable page, @Param("centerId") long centerId);

    @Query("select s from Statement s where s.center.centerId =:centerId order by s.statementDate desc  , s.trip asc ")
    Page<Statement> getStatementsFromCenterOrderByDate(@Param("centerId") long centerId, Pageable page);

    @Query("select s from Statement s where s.center.centerId=:centerId and s.trip=:trip and statementDate=:date ")
    Statement getStatementByTripAndDateFromCenter(@Param("centerId") long centerId, @Param("trip") Trip trip, @Param("date") String date);

    @Query("select s from Statement s where s.statementDate=:date order by s.statementDate desc , s.trip asc")
    Page<Statement> getStatementsByDateOrderByDate(@Param("date") String date, Pageable page);

    @Query("select s from Statement s where s.statementDate=:date and s.center.centerId=:centerId order by s.statementDate desc , s.trip asc")
    Page<Statement> getStatementsByCenterDateOrderByDate(@Param("centerId") long centerId, @Param("date") String date, Pageable page);

    @Query("select s from Statement s where s.trip=:trip order by s.statementDate desc , s.trip asc ")
    Page<Statement> getStatementsByTripOrderByDate(@Param("trip") Trip trip, Pageable page);
    
    @Query("select s from Statement s where s.trip=:trip and s.center.centerId=:centerId order by s.statementDate desc , s.trip asc ")
    Page<Statement> getStatementsByCenterTripOrderByDate(@Param("centerId") long centerId, @Param("trip") Trip trip, Pageable page);
    
    @Query("select s from Statement s where s.trip=:trip and s.statementDate=:date order by s.statementDate desc , s.trip asc ")
    Page<Statement> getStatementsByDateTripOrderByDate(@Param("date") String date, @Param("trip") Trip trip, Pageable page);
    
    @Query("select s from Statement s where s.trip=:trip and s.center.centerId=:centerId and s.statementDate=:date order by s.statementDate desc , s.trip asc ")
    Page<Statement> getStatementsByCenterDateTripOrderByDate(@Param("centerId") long centerId, @Param("date") String date, @Param("trip") Trip trip, Pageable page);

}
