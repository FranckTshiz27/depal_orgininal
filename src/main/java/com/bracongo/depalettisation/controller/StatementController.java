package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.Depalettization;
import com.bracongo.depalettisation.entities.EmptyDepalettizationDetail;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservationDetail;
import com.bracongo.depalettisation.entities.FullDepalettizationDetail;
import com.bracongo.depalettisation.entities.Perform;
import com.bracongo.depalettisation.entities.Statement;
import com.bracongo.depalettisation.entities.StatementDetail;
import com.bracongo.depalettisation.enumerations.Trip;
import com.bracongo.depalettisation.enumerations._Role;
import com.bracongo.depalettisation.service.impl.StatementDetailService;
import com.bracongo.depalettisation.service.impl.StatementService;
import com.bracongo.depalettisation.util.Converter;
import com.bracongo.depalettisation.util.DateConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author f.tshizubu
 */
@RestController
@CrossOrigin
@RequestMapping("/statement")
@RequiredArgsConstructor
@Component
public class StatementController {

    @Autowired
    private StatementService statementService;

    @Autowired
    private StatementDetailService statementDetailService;

    @PatchMapping("{statementId}")
    public ResponseEntity<Statement> update(@RequestBody Statement statement, @PathVariable("statementId") long statementId) {
        Statement updateStatement = statementService.updateStatement(statement, statementId);
        return new ResponseEntity<>(updateStatement, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {

        if (statementService.delete(id) > 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping("/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Statement>> getDepalettizations(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageSize, pageNumber);

        Page<Statement> statements = statementService.getStatements(page);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }

    @GetMapping("/date/{date}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Statement>> getStatementsByDate(@PathVariable("date") String date, @PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageSize, pageNumber);
        Page<Statement> statements = statementService.getStatementsByDate(date, page);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }

    @GetMapping("/center/date/{centerId}/{date}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Statement>> getStatementsByCenterDate(@PathVariable("centerId") long centerId, @PathVariable("date") String date, @PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageSize, pageNumber);
        Page<Statement> statements = statementService.getStatementsByCenterDate(centerId, date, page);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }

    @GetMapping("/{centerId}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Statement>> getDepalettizationsByCenter(@PathVariable("centerId") long centerId, @PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Statement> statements = statementService.getStatementsByCenter(centerId, page);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }

    @GetMapping("/trip/{trip}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Statement>> getDepalettizationsByTrip(@PathVariable("trip") Trip trip, @PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Statement> statements = statementService.getStatementsByTrip(trip, page);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }
    
    @GetMapping("/center/trip/{centerId}/{trip}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Statement>> getStatementsByCenterTrip(@PathVariable("centerId") long centerId, @PathVariable("trip") Trip trip, @PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageSize, pageNumber);
        Page<Statement> statements = statementService.getStatementsByCenterTrip(centerId, trip, page);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }     
    
    @GetMapping("/date/trip/{date}/{trip}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Statement>> getStatementsByDateTrip(@PathVariable("date") String date, @PathVariable("trip") Trip trip, @PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageSize, pageNumber);
        Page<Statement> statements = statementService.getStatementsByDateTrip(date, trip, page);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }
    
    @GetMapping("/center/date/trip/{centerId}/{date}/{trip}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Statement>> getStatementsByCenterDateTrip(@PathVariable("centerId") long centerId, @PathVariable("date") String date, @PathVariable("trip") Trip trip, @PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageSize, pageNumber);
        Page<Statement> statements = statementService.getStatementsByCenterDateTrip(centerId, date, trip, page);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Statement> findById(@PathVariable("id") Long id) {
        Statement statement = statementService.getStatementById(id);
        return new ResponseEntity<>(statement, HttpStatus.OK);
    }

    @GetMapping("center/{centerId}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Statement>> getDepalettizationsByCenterId(@RequestBody HashMap<String,Object> parameters,@PathVariable("centerId") long centerId, @PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        
        Iterator iterator = parameters.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(" iterator   "+iterator);
            
            
        }
        
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Statement> statements = statementService.getStatementsByCenter(centerId, page);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }

    @GetMapping("report/{statementId}")
    public ResponseEntity<byte[]> getStatementReport(@PathVariable("statementId") long statementId) throws FileNotFoundException, JRException {

        Statement statement = statementService.getStatementById(statementId);

        List<StatementDetail> statementDetails = statementDetailService.getStatementDetails(statementId);

        HashMap parameters = new HashMap();
        
         InputStream imgInputStream = this.getClass().getClassLoader().getResourceAsStream("logo21.jpg");
        parameters.put("logo", imgInputStream);
        parameters.put("reportNumber", statement.getStatementId());
        parameters.put("centerName", statement.getCenter().getName());
      
        parameters.put("statementDate", DateConverter.convertDateToFrenchFormat(statement.getStatementDate()));

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(statementDetails);
        //src/main/resources/ 
        JasperReport compileReport = JasperCompileManager.compileReport(this.getClass().getClassLoader()
                .getResourceAsStream("statement.jrxml"));
        JasperPrint report = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

        byte[] data = JasperExportManager.exportReportToPdf(report);

        HttpHeaders headers = new HttpHeaders();

        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=rapport.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }

}
