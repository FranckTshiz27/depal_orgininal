/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;
import com.bracongo.depalettisation.dto.DriverDto;
import com.bracongo.depalettisation.entities.Driver;
import com.bracongo.depalettisation.entities.DriverAssignment;
import java.util.List;

/**
 *
 * @author f.tshizubu
 */
public interface IDriverService{
    
    public DriverAssignment saveOrUpdate(DriverDto  driverDto);

    public int delete(Long id);
    
    public Driver getDriverById(Long id);
    
    public List<Driver> getDrivers();
}
