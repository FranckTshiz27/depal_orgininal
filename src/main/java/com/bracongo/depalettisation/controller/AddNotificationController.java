package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.AddNotification;
import com.bracongo.depalettisation.service.impl.AddNotificationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author f.tshizubu
 */
@RestController
@CrossOrigin
@RequestMapping("/addNotification")
@RequiredArgsConstructor
@Component

public class AddNotificationController {   
    @Autowired
    private  AddNotificationService  addNotificationService;
    
    @GetMapping("/center/{centerId}")
    public ResponseEntity<List<AddNotification>> findNotificationsByCenterId(@PathVariable("centerId") int centerId){
        List<AddNotification> notifications = addNotificationService.getNotificationsByCenterId(centerId);
        return new ResponseEntity<>(notifications,HttpStatus.OK);
    }    
  
}