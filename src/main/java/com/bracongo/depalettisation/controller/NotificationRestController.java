/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.AddNotification;
import com.bracongo.depalettisation.enumerations.DepalettizationType;
import com.bracongo.depalettisation.service.impl.AddNotificationService;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author F.TSHIZUBU
 */

@RestController
@RequestMapping("/notificate")
public class NotificationRestController {
  
   @Autowired
   private SimpMessagingTemplate simpMessagingTemplate;
   
    @Autowired
   private AddNotificationService addNotificationService;
   
  @PostMapping("/center/{centerId}")
   public void  getNotificationFromCenter(@PathVariable("centerId") String  centerId){
        AddNotification notification = new AddNotification();
        
        long convertedCenterId = Long.parseLong(centerId);
       
        notification.setDepalettizationType(DepalettizationType.PLEINS);
        notification.setNotificationId(1000);
        
        simpMessagingTemplate.convertAndSend("/depalettization/addNotification/"+convertedCenterId,notification);
    }
   
    @GetMapping("/center/{centerId}")
   public List<AddNotification>  getNotificationsByCenter(@PathVariable("centerId") long  centerId){
       return addNotificationService.getNotificationsByCenterId(centerId);
    }
    
}
