/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.AccountDao;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.dao.DriverDao;
import com.bracongo.depalettisation.dto.DriverDto;
import com.bracongo.depalettisation.service.IDriverService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;
import com.bracongo.depalettisation.dao.DriverAssignmentDao;
import com.bracongo.depalettisation.entities.Driver;
import com.bracongo.depalettisation.entities.DriverAssignment;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class DriverService implements IDriverService {

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private DriverAssignmentDao driverAssignmentDao;
    @Autowired
    private DriverAssignmentService driverAssignmentService;

    @Override
    public int delete(Long id) {

        Optional<Driver> deletingDriver = driverDao.findDriverByDriverId(id);

        if (deletingDriver != null) {
            driverDao.deleteDrivertByDriverId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public Driver getDriverById(Long id) {
        return driverDao.findDriverByDriverId(id).
                orElseThrow(() -> new CustomNotFoundException("Le driver dont le id " + id + " est introuvable"));
    }

    @Override
    public List<Driver> getDrivers() {
        return driverDao.getDriversOrderByNameAsc();
    }

    @Override
    public DriverAssignment saveOrUpdate(DriverDto driverDto) {
        Driver driver = null;
        DriverAssignment driverAssignment = null;
        try {
            driver = driverDto.getDriver();
            driverAssignment = driverDto.getDriverAssignment();

            driver = driverDao.save(driver);

            if (driver != null) {
                driverAssignment.setDriver(driver);
            }

            driverAssignment = driverAssignmentService.save(driverAssignment);

        } catch (Exception e) {
        }

        return driverAssignment;
    }

    public DriverAssignment updateDriver(Driver driver) {

        Driver savedDriver = null;

        Optional<Driver> newDriver = driverDao.findDriverByDriverId(driver.getDriverId());

        if (newDriver != null) {
            savedDriver = driverDao.save(driver);
        }
        if (savedDriver != null) {
            return driverAssignmentDao.getCurrentDriverAssignmentByDriverId(savedDriver.getDriverId());
        }
        return null;
    }
}
