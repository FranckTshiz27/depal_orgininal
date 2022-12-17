/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.AddNotification;
import java.util.List;

/**
 *
 * @author F.TSHIZUBU
 */
public interface IAddNotification {
    public AddNotification saveOrUpdate(AddNotification  notification);

    public int delete(long id);
    
    public List<AddNotification> getNotifications();
    
    public AddNotification getNotification(long id);
}
