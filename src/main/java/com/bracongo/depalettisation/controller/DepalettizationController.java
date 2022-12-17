/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.dto.AddEmptyDepalettizationDto;
import com.bracongo.depalettisation.dto.AddFullDepalettizationDto;
import com.bracongo.depalettisation.dto.AddStatementDto;
import com.bracongo.depalettisation.entities.Depalettization;
import com.bracongo.depalettisation.entities.EmptyDepalettizationDetail;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservationDetail;
import com.bracongo.depalettisation.entities.FullDepalettizationDetail;
import com.bracongo.depalettisation.entities.Perform;
import com.bracongo.depalettisation.enumerations._Role;
import com.bracongo.depalettisation.service.impl.DepalettizationService;
import com.bracongo.depalettisation.service.impl.EmptyDepalettizationDetailObservationService;
import com.bracongo.depalettisation.service.impl.EmptyDepalettizationDetailService;
import com.bracongo.depalettisation.service.impl.FullDepalettizationDetailService;
import com.bracongo.depalettisation.service.impl.PerformService;
import com.bracongo.depalettisation.util.Converter;
import com.bracongo.depalettisation.util.DateConverter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author f.tshizubu
 */
@RestController
@CrossOrigin
@RequestMapping("/depalettization")
@RequiredArgsConstructor
@Component
public class DepalettizationController {

    @Autowired
    private DepalettizationService depalettizationService;

    @Autowired
    private PerformService performService;

    @Autowired
    private FullDepalettizationDetailService fullDepalettizationDetailService;

    @Autowired
    private EmptyDepalettizationDetailService emptyDepalettizationDetailService;

    @Autowired
    private EmptyDepalettizationDetailObservationService emptyDepalettizationDetailObservationService;

    @PostMapping("full")
    public ResponseEntity<Depalettization> createFullDepalettization(@Valid @RequestBody AddFullDepalettizationDto depalettization) {
        Depalettization newDepalettization = depalettizationService.saveFullDepalettization(depalettization);

        return new ResponseEntity<>(newDepalettization, HttpStatus.CREATED);
    }

    @PostMapping("empty")
    public ResponseEntity<Depalettization> createEmptyDepalettization(@Valid @RequestBody AddEmptyDepalettizationDto depalettization) {
        Depalettization newDepalettization = depalettizationService.saveEmptyDepalettization(depalettization);
        return new ResponseEntity<>(newDepalettization, HttpStatus.CREATED);
    }

    @PatchMapping("{depalettizationId}")
    public ResponseEntity<Depalettization> update(@RequestBody Depalettization depalettization, @PathVariable("depalettizationId") long depalettizationId) {
        Depalettization updateDepalettization = depalettizationService.updateDepalettization(depalettization, depalettizationId);
        return new ResponseEntity<>(updateDepalettization, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {

        if (depalettizationService.delete(id) > 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping("/validated/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Depalettization>> getValidatedDepalettizations(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Depalettization> depalettizations = depalettizationService.getValidatedDepalettizations(page);
        return new ResponseEntity<>(depalettizations, HttpStatus.OK);
    }

    @GetMapping("/unValidated/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Depalettization>> getUnValidatedDepalettizations(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Depalettization> depalettizations = depalettizationService.getUnValidatedDepalettizations(page);
        return new ResponseEntity<>(depalettizations, HttpStatus.OK);
    }

    @PatchMapping("/validate/{depalettizationId}")
    public ResponseEntity<Depalettization> validateDepalettization(@RequestBody AddStatementDto addStatementDto, @PathVariable("depalettizationId") long depalettizationId) {
        Depalettization depalettization = depalettizationService.validateDepalettization(depalettizationId, addStatementDto);
        return new ResponseEntity<>(depalettization, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Depalettization> findById(@PathVariable("id") Long id) {
        Depalettization depalettization = depalettizationService.getDepalettizationById(id);
        return new ResponseEntity<>(depalettization, HttpStatus.OK);
    }

    @GetMapping("agent/{agentId}/{depalettizationDate}")
    public ResponseEntity<List<Depalettization>> getDepalettizationsByDateAndAgentId(@PathVariable("agentId") long agentId, @PathVariable("depalettizationDate") String depalettizationDate) {
        List<Depalettization> depalettizations = depalettizationService.getDepalettizationsByDateAndAgentId(agentId, depalettizationDate);
        return new ResponseEntity<>(depalettizations, HttpStatus.OK);
    }

    @GetMapping("center/{centerId}/{pageSize}/{pageNumber}/{request}")
    public ResponseEntity<Page<Depalettization>> getDepalettizationsByCenterId(@PathVariable("centerId") long centerId, @PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("request") String request) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Depalettization> depalettizations = depalettizationService.getDepalettizationsByCenterId(page, centerId, request);
        return new ResponseEntity<>(depalettizations, HttpStatus.OK);
    }

    @GetMapping("/{pageSize}/{pageNumber}/{request}")
    public ResponseEntity<Page<Depalettization>> getDepalettizations(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("request") String request) {

        Pageable page = PageRequest.of(pageSize, pageNumber);

        Page<Depalettization> depalettizations = depalettizationService.getDepalettizations(page, request);
        return new ResponseEntity<>(depalettizations, HttpStatus.OK);
    }

    @GetMapping("userStat/center/{centerId}/{pageSize}/{pageNumber}/{request}")
    public ResponseEntity<Page<Depalettization>> getValidatedDepalettizationsByCenterId(@PathVariable("centerId") long centerId, @PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("request") String request) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Depalettization> depalettizations = depalettizationService.getValidatedDepalettizationsByCenterId(page, centerId, request);
        return new ResponseEntity<>(depalettizations, HttpStatus.OK);
    }

    @PatchMapping("/visite/{depalettizationId}")
    public ResponseEntity visitedDepalettization(@PathVariable("depalettizationId") long depalettizationId) {
        int state = depalettizationService.visitedDepalettization(depalettizationId);
        return new ResponseEntity<>(state, HttpStatus.OK);
    }

    @GetMapping("report/{depalettizationId}")
    public ResponseEntity<byte[]> getFullReport(@PathVariable("depalettizationId") long depalettizationId) throws FileNotFoundException, JRException {
        boolean isObservation = false, isEmpty = false, isFull = false;
        List<FullDepalettizationDetail> details = fullDepalettizationDetailService.getFullDepalettizationDetails(depalettizationId);
        List<EmptyDepalettizationDetail> emptydetails = emptyDepalettizationDetailService.getEmptyDepalettizationDetails(depalettizationId);
        List<EmptyDepalettizationObservationDetail> emptyObserbationsdetails = emptyDepalettizationDetailObservationService.getEmptyDepalettizationDetails(depalettizationId);

        if (details != null && !details.isEmpty()) {
            isFull = true;
        }

        if (!emptydetails.isEmpty() && emptydetails != null) {
            isEmpty = true;
        }

        if (!emptyObserbationsdetails.isEmpty() && emptyObserbationsdetails != null) {
            isObservation = true;
        }

        Depalettization depalettization = depalettizationService.getDepalettizationById(depalettizationId);

        HashMap parameters = new HashMap();
        String link = "src/main/resources/";

        parameters.put("reportNumber", depalettization.getDepalettizationId());
        parameters.put("depalettizationDate",DateConverter.convertDateToFrenchFormat(depalettization.getDepalettizationDate()));
        parameters.put("centerName", depalettization.getDriverAssignment().getCircuit().getCenter().getName());
        parameters.put("driverName", depalettization.getDriverAssignment().getDriver().getName() + " " + depalettization.getDriverAssignment().getDriver().getFirstname());
        parameters.put("driverCode", depalettization.getDriverAssignment().getDriver().getCode());
        parameters.put("ub", depalettization.getDriverAssignment().getTruck().getUb());
        parameters.put("circuit", depalettization.getDriverAssignment().getCircuit().getCode());
        parameters.put("trip", Converter.tripToString(depalettization.getTrip()));

        parameters.put("isEmpty", isEmpty);
        parameters.put("isFull", isFull);
        parameters.put("isObservation", isObservation);

        parameters.put("SUBREPORT_DIR", link);

        parameters.put("DATASOURCE1", new JRBeanCollectionDataSource(details));
        parameters.put("DATASOURCE2", new JRBeanCollectionDataSource(emptyObserbationsdetails));
        parameters.put("DATASOURCE3", new JRBeanCollectionDataSource(emptydetails));
        
        
        InputStream imgInputStream = this.getClass().getClassLoader().getResourceAsStream("logo21.jpg");
        parameters.put("logo", imgInputStream);

        List<Perform> ltsPerform = performService.getPerformByFullDePalettization(depalettizationId);
        Perform perform = null;
        Perform perform_full = null;
        Perform perform_empty = null;
        for (Perform perform1 : ltsPerform) {
            if (perform1.getPerformAgent().getAccount().get_role() == _Role.USER_MAG_EMPTY) {
                perform_empty = perform1;
            } else {
                perform_full = perform1;
            }
        }
        if (perform_full != null) {
            parameters.put("shift_full", Converter.shiftToString(perform_full.getShift()));
            parameters.put("agentName_full", perform_full.getPerformAgent().getName() + " " + perform_full.getPerformAgent().getFirstname());
        }

        if (perform_empty != null) {
            parameters.put("shift_empty", Converter.shiftToString(perform_empty.getShift()));
            parameters.put("agentName_empty", perform_empty.getPerformAgent().getName() + " " + perform_empty.getPerformAgent().getFirstname());
        }

        JasperReport compileReport = JasperCompileManager.compileReport(this.getClass().getClassLoader()
                .getResourceAsStream("generalReport.jrxml"));
        JasperPrint report = JasperFillManager.fillReport(compileReport, parameters, new JREmptyDataSource());
        

        byte[] data = JasperExportManager.exportReportToPdf(report);

        HttpHeaders headers = new HttpHeaders();

        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=rapport.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }

}
