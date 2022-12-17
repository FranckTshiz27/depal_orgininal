package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.AddNotification;
import com.bracongo.depalettisation.entities.Depalettization;
import com.bracongo.depalettisation.entities.ValidateNotification;
import com.bracongo.depalettisation.service.impl.ValidateNotificationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author f.tshizubu
 */
@RestController
@CrossOrigin
@RequestMapping("/validateNotification")
@RequiredArgsConstructor
@Component

public class ValidateNotificationController {

    @Autowired
    private ValidateNotificationService validateNotificationService;

    @GetMapping("/center/{centerId}")
    public ResponseEntity<List<ValidateNotification>> findNotificationsByCenterId(@PathVariable("centerId") int centerId) {
        List<ValidateNotification> notifications = validateNotificationService.getNotificationsByCenterId(centerId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<ValidateNotification>> findNotificationsByAgentId(@PathVariable("agentId") int agentId) {
        List<ValidateNotification> notifications = validateNotificationService.getNotificationsByAgent(agentId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

//    @PatchMapping("/visite/{agentId}")
//    public ResponseEntity visiteNotifications(@PathVariable("agentId") long agentId) {
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        int result = validateNotificationService.visiteNotifications(agentId);
//        System.out.println("aaaaaaaaaaaaannnnnnnnnnnnn" + result);
//        return new ResponseEntity(result, HttpStatus.OK);
//    }
    
    @PatchMapping("/visite")
    public ResponseEntity<List<Depalettization>> visiteNotifications(@RequestBody List<Depalettization> depalettizations) {
        List<Depalettization> result = validateNotificationService.visiteNotifications(depalettizations);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/full/center/{centerId}")
    public ResponseEntity<List<ValidateNotification>> findFullNotificationsByCenterId(@PathVariable("centerId") int centerId) {
        List<ValidateNotification> notifications = validateNotificationService.getFullNotificationsByCenterId(centerId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

}
