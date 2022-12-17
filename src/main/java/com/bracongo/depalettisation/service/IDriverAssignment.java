/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;
import com.bracongo.depalettisation.entities.DriverAssignment;
import java.util.List;

/**
 *
 * @author f.tshizubu
 */
public interface IDriverAssignment{
    
    public DriverAssignment save(DriverAssignment driverAssignment);

    public int delete(Long id);
    
    public DriverAssignment getDriverAssignmentById(Long id);
    
    public List<DriverAssignment> getDriverAssignments();
}
