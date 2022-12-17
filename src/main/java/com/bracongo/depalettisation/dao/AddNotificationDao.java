/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.AddNotification;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author J.LUTUNDULA
 */
@Repository
public interface AddNotificationDao  extends JpaRepository<AddNotification, Long> {

    Optional<AddNotification> findNotificationByNotificationId(Long id);
  
     @Query("select n from AddNotification n")
     public List<AddNotification> getNotifications();
     
     @Query("select n from AddNotification n where n.depalettization.driverAssignment.circuit.center.centerId=:centerId "
             + " order by n.depalettization.isValidated desc , "
             + " n.depalettization.isEmptyValidated desc , "
             + " n.depalettization.isFullValidated desc , "
             + " n.createdAt desc")
     public List<AddNotification> getNotificationsByCenterId(@Param("centerId") long centerId);
}
