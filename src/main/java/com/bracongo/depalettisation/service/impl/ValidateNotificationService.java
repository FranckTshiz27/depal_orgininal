/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.DepalettizationDao;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.bracongo.depalettisation.dao.ValidateNotificationDao;
import com.bracongo.depalettisation.entities.Depalettization;
import com.bracongo.depalettisation.entities.ValidateNotification;
import com.bracongo.depalettisation.service.IValidateNotification;

/**
 *
 * @author J.LUTUNDULA
 */
@Service
@Transactional
@AllArgsConstructor
public class ValidateNotificationService implements IValidateNotification {

    private final ValidateNotificationDao notificationDao;
    private final DepalettizationDao depalettizationDao;

    @Override
    public ValidateNotification saveOrUpdate(ValidateNotification notification) {
        ValidateNotification savedNotification = notificationDao.save(notification);
        return savedNotification;
    }

    @Override
    public int delete(long id) {
        Optional<ValidateNotification> notificationFound = notificationDao.findValidateNotificationByNotificationId(id);
        if (notificationFound != null) {
            return 1;
        }
        return -1;
    }

    @Override
    public List<ValidateNotification> getNotifications() {
        return notificationDao.getValidateNotifications();
    }

    @Override
    public ValidateNotification getNotification(long id) {
        return notificationDao.findValidateNotificationByNotificationId(id).
                orElseThrow(() -> new CustomNotFoundException("La notification dont l'id est : " + id + " est introuvable"));
    }
    
       
    public List<ValidateNotification> getNotificationsByCenterId(long centerId) {
        return notificationDao.getValidateNotificationsByCenter(centerId);
    }
    
    public List<ValidateNotification> getNotificationsByAgent(long agentId) {
       List<ValidateNotification> notifications = notificationDao.getValidateNotificationsByAgent(agentId);
       return notifications;
    }
    
    public List<ValidateNotification> getFullNotificationsByCenterId(long centerId) {
        return notificationDao.getFullValidateNotificationsByCenter(centerId);
    }
    
//    public int visiteNotifications(long agentId){ 
//        return notificationDao.visiteValidateNotification(agentId);
//    }
    
    public List<Depalettization> visiteNotifications(List<Depalettization> depalettizations){
        return depalettizationDao.saveAll(depalettizations);
    }

}
