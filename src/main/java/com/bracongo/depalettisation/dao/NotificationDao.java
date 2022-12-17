/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Notification;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *
 * @author J.LUTUNDULA
 */
@Repository
public interface NotificationDao  extends JpaRepository<Notification, Long> {

    Optional<Notification> findNotificationByNotificationId(Long id);
    
     @Query("select n from Notification n")
    public List<Notification> getNotifications();
}
