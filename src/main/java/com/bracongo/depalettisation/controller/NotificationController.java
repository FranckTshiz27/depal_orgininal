/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.AddNotification;
import com.bracongo.depalettisation.entities.ValidateNotification;
import com.bracongo.depalettisation.enumerations.DepalettizationType;
import com.bracongo.depalettisation.service.impl.AddNotificationService;
import com.bracongo.depalettisation.service.impl.ValidateNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author F.TSHIZUBU
 */
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {

    private AddNotificationService addNotificationService;
    private ValidateNotificationService validateNotificationService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/addNotification")
    @SendTo("/depalettization/addNotification/")
    public AddNotification getNotification() {
        AddNotification notification = new AddNotification();
        notification.setDepalettizationType(DepalettizationType.PLEINS);
        notification.setNotificationId(1000);
        return notification;
    }

    @MessageMapping("/centerNotification/{centerId}")
    public void getNotificationFromCenter(@DestinationVariable String centerId) {
        AddNotification notification = new AddNotification();

        notification.setDepalettizationType(DepalettizationType.PLEINS);
        notification.setNotificationId(1000);
        simpMessagingTemplate.convertAndSend("/depalettization/addNotification/" + centerId, notification);
    }

    @MessageMapping("/validateNotification")
    @SendTo("/topic/depalettization/validateNotification/")
    public ValidateNotification validateNotification() {
        ValidateNotification validateNotification = new ValidateNotification();
        return validateNotification;
    }
}
