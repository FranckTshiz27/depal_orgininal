/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.ValidateNotification;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author J.LUTUNDULA
 */
@Repository
public interface ValidateNotificationDao  extends JpaRepository<ValidateNotification, Long> {

    Optional<ValidateNotification> findValidateNotificationByNotificationId(Long id);
    
    @Query("select vn from ValidateNotification vn")
    public List<ValidateNotification> getValidateNotifications();
    
    /*@Query("select vn from ValidateNotification vn where vn.perform.performAgent.agentId =:agentId "
            + " order by vn.depalettization.isValidated desc ,"
            + " vn.depalettization.isEmptyValidated desc ,"
            + " vn.depalettization.isFullValidated desc , "
            + " vn.createdAt desc")
    public List<ValidateNotification> getValidateNotificationsByAgent( @Param("agentId") long agentId);*/
    
    @Query("select vn from ValidateNotification vn where "
            + " vn.depalettization.driverAssignment.circuit.center.centerId =:centerId "
            + " and vn.depalettization.isValidated = true "
            + " order by vn.depalettization.isValidated desc ,"
            + " vn.createdAt desc")
    public List<ValidateNotification> getValidateNotificationsByCenter( @Param("centerId") long agentId);
    
    @Query("select vn from ValidateNotification vn where vn.perform.performAgent.agentId=:agentId order by vn.createdAt desc")
    public List<ValidateNotification> getValidateNotificationsByAgent( @Param("agentId") long agentId);
    
    @Query("select vn from ValidateNotification vn where "
            + " vn.depalettization.driverAssignment.circuit.center.centerId =:centerId "
            + " and vn.depalettization.isValidated = true "
            + " and vn.validationType='EMPTYSTATE'"
            + " and vn.depalettization.isVisited=false "
            + " order by vn.depalettization.isValidated desc ,"
            + " vn.createdAt desc")
    public List<ValidateNotification> getFullValidateNotificationsByCenter( @Param("centerId") long centerId);
    
//    @Modifying
//    @Query("update Depalettization dep inner join ValidateNotification vn "
//            + " on dep.depalettizationId = vn.depalettization.depalettizationId  set dep.hasBeenVisitedByAgent=true "
//            + " where dep.hasBeenVisitedByAgent=false "
//            + " "
//            + " ")
//    public int visiteValidateNotification( @Param("agentId") long agentId);
}
