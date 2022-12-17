/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.entities.AddNotification;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.bracongo.depalettisation.service.IAddNotification;
import com.bracongo.depalettisation.dao.AddNotificationDao;

/**
 *
 * @author J.LUTUNDULA
 */
@Service
@Transactional
@AllArgsConstructor
public class AddNotificationService implements IAddNotification {

    private final AddNotificationDao notificationDao;

    @Override
    public AddNotification saveOrUpdate(AddNotification notification) {
        AddNotification savedNotification = notificationDao.save(notification);
        return savedNotification;
    }

    @Override
    public int delete(long id) {
        Optional<AddNotification> notificationFound = notificationDao.findNotificationByNotificationId(id);
        if (notificationFound != null) {
            return 1;
        }
        return -1;
    }

    @Override
    public List<AddNotification> getNotifications() {
        return notificationDao.getNotifications();
    }

    @Override
    public AddNotification getNotification(long id) {
        return notificationDao.findNotificationByNotificationId(id).
                orElseThrow(() -> new CustomNotFoundException("La notification dont l'id est : " + id + " est introuvable"));
    }

    
    public List<AddNotification> getNotificationsByCenterId(long centerId) {
        return notificationDao.getNotificationsByCenterId(centerId);
    }
}
