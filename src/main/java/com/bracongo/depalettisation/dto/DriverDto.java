/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.dto;

import com.bracongo.depalettisation.entities.Driver;
import com.bracongo.depalettisation.entities.DriverAssignment;
import lombok.Data;

/**
 *
 * @author F.TSHIZUBU
 */
@Data
public class DriverDto {
    Driver driver;
    DriverAssignment driverAssignment;
}
