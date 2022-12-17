/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.ValidateNotification;
import java.util.List;

/**
 *
 * @author F.TSHIZUBU
 */
public interface IValidateNotification {
    public ValidateNotification saveOrUpdate(ValidateNotification  notification);

    public int delete(long id);
    
    public List<ValidateNotification> getNotifications();
    
    public ValidateNotification getNotification(long id);
}
