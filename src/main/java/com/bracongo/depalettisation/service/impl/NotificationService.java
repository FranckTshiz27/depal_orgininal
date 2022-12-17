/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.AddNotificationDao;
import com.bracongo.depalettisation.entities.Notification;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.bracongo.depalettisation.service.IAddNotification;
import com.bracongo.depalettisation.dao.NotificationDao;
import com.bracongo.depalettisation.entities.AddNotification;

/**
 *
 * @author J.LUTUNDULA
 */
@Service
@Transactional
@AllArgsConstructor
public class NotificationService implements IAddNotification {

    private final AddNotificationDao addNotificationDao;

    @Override
    public AddNotification saveOrUpdate(AddNotification notification) {
        AddNotification savedNotification = addNotificationDao.save(notification);
        return savedNotification;
    }

    @Override
    public int delete(long id) {
        Optional<AddNotification> notificationFound = addNotificationDao.findNotificationByNotificationId(id);
        if (notificationFound != null) {
            addNotificationDao.deleteById(id);
            return 1;
        }
        return -1;
    }

    @Override
    public List<AddNotification> getNotifications() {
        return addNotificationDao.getNotifications();
    }

    @Override
    public AddNotification getNotification(long id) {
        return addNotificationDao.findNotificationByNotificationId(id).
                orElseThrow(() -> new CustomNotFoundException("La notification dont l'id est : " + id + " est introuvable"));
    }

}
