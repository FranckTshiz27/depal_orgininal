/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.AddNotificationDao;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;
import com.bracongo.depalettisation.dao.DepalettizationDao;
import com.bracongo.depalettisation.dao.EmptyDepalettizationDao;
import com.bracongo.depalettisation.dao.EmptyDepalettizationDetailDao;
import com.bracongo.depalettisation.dao.EmptyDepalettizationObservationDao;
import com.bracongo.depalettisation.dao.EmptyDepalettizationObservationDetailDao;
import com.bracongo.depalettisation.dao.FullDepalettizationDao;
import com.bracongo.depalettisation.dao.FullDepalettizationDetailDao;
import com.bracongo.depalettisation.dao.PerformDao;
import com.bracongo.depalettisation.dao.StatementDao;
import com.bracongo.depalettisation.dao.StatementDetailDao;
import com.bracongo.depalettisation.dao.ValidateNotificationDao;
import com.bracongo.depalettisation.dto.AddEmptyDepalettizationDto;
import com.bracongo.depalettisation.dto.AddFullDepalettizationDto;
import com.bracongo.depalettisation.dto.AddStatementDto;
import com.bracongo.depalettisation.entities.AddNotification;
import com.bracongo.depalettisation.entities.Center;
import com.bracongo.depalettisation.entities.Depalettization;
import com.bracongo.depalettisation.entities.EmptyDepalettization;
import com.bracongo.depalettisation.entities.EmptyDepalettizationDetail;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservation;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservationDetail;
import com.bracongo.depalettisation.entities.FullDepalettization;
import com.bracongo.depalettisation.entities.FullDepalettizationDetail;
import com.bracongo.depalettisation.entities.Perform;
import com.bracongo.depalettisation.entities.Statement;
import com.bracongo.depalettisation.entities.StatementDetail;
import com.bracongo.depalettisation.entities.ValidateNotification;
import com.bracongo.depalettisation.enumerations.DepalettizationType;
import com.bracongo.depalettisation.enumerations.NotificationType;
import com.bracongo.depalettisation.enumerations.Trip;
import com.bracongo.depalettisation.enumerations.ValidationType;
import com.bracongo.depalettisation.enumerations._Role;
import com.bracongo.depalettisation.service.IDepalettization;
import com.bracongo.depalettisation.util.Converter;
import static com.lowagie.text.xml.simpleparser.EntitiesToUnicode.map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class DepalettizationService implements IDepalettization {

    @Autowired
    private final DepalettizationDao depalettizationDao;

    @Autowired
    private final FullDepalettizationDao fullDepalettizationDao;

    @Autowired
    private final EmptyDepalettizationDao emptyDepalettizationDao;

    @Autowired
    private final EmptyDepalettizationObservationDao emptyDepalettizationObservationDao;

    @Autowired
    private final FullDepalettizationDetailDao fullDepalettizationDetailDao;

    @Autowired
    private final EmptyDepalettizationObservationDetailDao emptyDepalettizationObservationDetailDao;

    @Autowired
    private final EmptyDepalettizationDetailDao emptyDepalettizationDetailDao;

    @Autowired
    private final PerformDao performDao;

    @Autowired
    private final ValidateNotificationDao validateNotificationDao;

    @Autowired
    private final StatementDao statementDao;
    @Autowired
    private final StatementDetailDao statementDetailDao;

    @Autowired
    private final AddNotificationDao addNotificationDao;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @Autowired
    ValidateNotification validateNotification;

    @Override
    public int delete(long id) {

        Optional<Depalettization> deletingDepalettization = depalettizationDao.findDepalettizationByDepalettizationId(id);

        if (deletingDepalettization != null) {
            depalettizationDao.deleteDepalettizationByDepalettizationId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public Depalettization getDepalettizationById(long id) {
        return depalettizationDao.findDepalettizationByDepalettizationId(id).
                orElseThrow(() -> new CustomNotFoundException("Le depalettization dont le id " + id + " est introuvable"));
    }

    public Depalettization saveFullDepalettization(AddFullDepalettizationDto fullDepalettizationDto) {

        Depalettization depalettization = null;
        FullDepalettization fullDepalettization = null;
        Perform perform = null;
        Depalettization savedDepalettization = null;

        Depalettization findDepalettization = depalettizationDao.getDepalettizationByAssignmentAndTripAndDate(fullDepalettizationDto.getDepalettization().getDriverAssignment().getDriverAssignmentId(), fullDepalettizationDto.getDepalettization().getTrip(), fullDepalettizationDto.getDepalettization().getDepalettizationDate());

        if (findDepalettization == null) {
            depalettization = fullDepalettizationDto.getDepalettization();
            depalettization.setFullPerformed(true);
            savedDepalettization = depalettizationDao.save(depalettization);
            save(fullDepalettization, savedDepalettization, fullDepalettizationDto, perform);
        } else if (!findDepalettization.isFullPerformed()) {
            findDepalettization.setFullPerformed(true);
            savedDepalettization = depalettizationDao.save(findDepalettization);
            save(fullDepalettization, savedDepalettization, fullDepalettizationDto, perform);

        }

        if (savedDepalettization != null && fullDepalettizationDto.
                getEmptyDepalettizationObservationDetails() != null
                && !fullDepalettizationDto.getEmptyDepalettizationObservationDetails().isEmpty()
                && savedDepalettization != null) {
            savedDepalettization.setObsevationPerformed(true);
            savedDepalettization = depalettizationDao.save(savedDepalettization);
            save(fullDepalettizationDto.getEmptyDepalettizationObservation(), savedDepalettization, fullDepalettizationDto.getEmptyDepalettizationObservationDetails());

        }

        return savedDepalettization;
    }

    public Depalettization saveEmptyDepalettization(AddEmptyDepalettizationDto emptyDepalettizationDto) {
        Depalettization depalettization = null;
        
        
        EmptyDepalettization emptyDepalettization = null;
        Perform perform = null;
        Depalettization savedDepalettization = null;
         List<Depalettization> findDepalettizations = depalettizationDao.getDepalettizationByAssignmentAndTripAndDate2(emptyDepalettizationDto.getDepalettization().getDriverAssignment().getDriverAssignmentId(), emptyDepalettizationDto.getDepalettization().getTrip(), emptyDepalettizationDto.getDepalettization().getDepalettizationDate());
        
         Depalettization findDepalettization = depalettizationDao.getDepalettizationByAssignmentAndTripAndDate(emptyDepalettizationDto.getDepalettization().getDriverAssignment().getDriverAssignmentId(), emptyDepalettizationDto.getDepalettization().getTrip(), emptyDepalettizationDto.getDepalettization().getDepalettizationDate());
          
        if (findDepalettization == null) {
            depalettization = emptyDepalettizationDto.getDepalettization();
            depalettization.setEmptyPerformed(true);
            savedDepalettization = depalettizationDao.save(depalettization);
            save(emptyDepalettization, savedDepalettization, emptyDepalettizationDto, perform);
        } else if (!findDepalettization.isEmptyPerformed()) {
            findDepalettization.setEmptyPerformed(true);
            savedDepalettization = depalettizationDao.save(findDepalettization);
            save(emptyDepalettization, savedDepalettization, emptyDepalettizationDto, perform);
        }

        return savedDepalettization;
    }

    private void save(EmptyDepalettization emptyDepalettization, Depalettization savedDepalettization, AddEmptyDepalettizationDto emptyDepalettizationDto, Perform perform) {
        emptyDepalettization = new EmptyDepalettization();
        emptyDepalettization.setEmptyDepalettizationId(savedDepalettization.getDepalettizationId());
        emptyDepalettization.setDepalettizationType(DepalettizationType.VIDES);

        EmptyDepalettization savedEmptyDepalellization = emptyDepalettizationDao.save(emptyDepalettization);

        perform = new Perform();
        perform.setPerformAgent(emptyDepalettizationDto.getAgent());
        perform.setDepalettization(savedDepalettization);
        perform.setShift(emptyDepalettizationDto.getShift());

        List<EmptyDepalettizationDetail> details = new ArrayList<>();
        for (EmptyDepalettizationDetail fd : emptyDepalettizationDto.getEmptyDepalettizationDetails()) {
            fd.setEmptyDepalettization(savedEmptyDepalellization);
            details.add(fd);
        }
        emptyDepalettizationDetailDao.saveAll(details);

        AddNotification addNotification = new AddNotification();
        addNotification.setDepalettization(savedDepalettization);
        addNotification.setDepalettizationType(DepalettizationType.VIDES);
        addNotification.setNotificationType(NotificationType.ADD);
        addNotification.setPerform(perform);
        Perform savedPerform = performDao.save(perform);
        addNotification.setPerform(savedPerform);
        AddNotification savedAddNotification = addNotificationDao.save(addNotification);

        if (savedAddNotification != null && savedDepalettization != null) {
            simpMessagingTemplate.convertAndSend("/depalettization/addNotification/" + savedDepalettization.getDriverAssignment().getCircuit().getCenter().getCenterId(), addNotification);
        }
    }

    private void save(EmptyDepalettizationObservation emptyDepalettization, Depalettization savedDepalettization, List<EmptyDepalettizationObservationDetail> emptyDepalettizationObservationDetails) {
        emptyDepalettization.setEmptyDepalettizationObservationId(savedDepalettization.getDepalettizationId());
        emptyDepalettization.setDepalettizationType(DepalettizationType.VIDES);

        EmptyDepalettizationObservation savedEmptyDepalellization = emptyDepalettizationObservationDao.save(emptyDepalettization);
        List<EmptyDepalettizationObservationDetail> details = new ArrayList<>();
        for (EmptyDepalettizationObservationDetail emo : emptyDepalettizationObservationDetails) {
            emo.setEmptyDepalettizationObservation(savedEmptyDepalellization);
            details.add(emo);
        }
        emptyDepalettizationObservationDetailDao.saveAll(details);
    }

    private void save(FullDepalettization fullDepalettization, Depalettization savedDepalettization, AddFullDepalettizationDto fullDepalettizationDto, Perform perform) {
        fullDepalettization = new FullDepalettization();
        fullDepalettization.setFullDepalettizationId(savedDepalettization.getDepalettizationId());
        fullDepalettization.setDepalettizationType(DepalettizationType.PLEINS);
        FullDepalettization savedFullDepalellization = fullDepalettizationDao.save(fullDepalettization);

        perform = new Perform();
        perform.setPerformAgent(fullDepalettizationDto.getAgent());
        perform.setDepalettization(savedDepalettization);
        perform.setShift(fullDepalettizationDto.getShift());

        List<FullDepalettizationDetail> details = new ArrayList<>();
        for (FullDepalettizationDetail fd : fullDepalettizationDto.getFullDepalettizationDetails()) {
            fd.setFullDepalettization(savedFullDepalellization);
            details.add(fd);
        }
        fullDepalettizationDetailDao.saveAll(details);
        AddNotification addNotification = new AddNotification();
        addNotification.setDepalettization(savedDepalettization);
        addNotification.setDepalettizationType(DepalettizationType.PLEINS);
        addNotification.setNotificationType(NotificationType.ADD);
       
        Perform savedPerform = performDao.save(perform);
        
        addNotification.setPerform(savedPerform);
        AddNotification savedAddNotification = addNotificationDao.save(addNotification);
        
        if (savedAddNotification != null && savedDepalettization != null) {
            simpMessagingTemplate.convertAndSend("/depalettization/addNotification/" + savedDepalettization.getDriverAssignment().getCircuit().getCenter().getCenterId(), addNotification);
        }
        
    }

    public Depalettization updateDepalettization(Depalettization depalettization, long depalettizationId) {

        Optional<Depalettization> newDepalettization = depalettizationDao.findDepalettizationByDepalettizationId(depalettizationId);

        if (newDepalettization != null) {
            return depalettizationDao.save(depalettization);
        }

        return null;
    }
    
    @Override
    public int visitedDepalettization(long depalettizationId){
        
        Depalettization depalettization = depalettizationDao.getById(depalettizationId);
        ValidateNotification notification = new ValidateNotification();
        
        if (depalettization == null) return -1;
        else {
            depalettization.setVisited(true);
            depalettizationDao.save(depalettization);
            simpMessagingTemplate.convertAndSend("/depalettization/visited/" + depalettization.getDriverAssignment().getCircuit().getCenter().getCenterId(),notification);
            return 1;
        }
    }

    @Override
    public Page<Depalettization> getDepalettizations(Pageable page,String param) {
         String[] params = param.split(",");
        String[] myParams =null;
        
        HashMap<String, String> parameters = new HashMap<>();
        if (params.length==1) {
            myParams = params[0].split("=");
            String flag = myParams[0].trim();
            switch(flag) {
                case "state":
                    myParams = params[0].split("=");
                    boolean state =Boolean.parseBoolean(myParams[1].trim());
                    return depalettizationDao.getDepalettizationsOrderByDateAndState(page,state);
                   
                 case "trip": 
                     myParams = params[0].split("=");
                    String trip = myParams[1];
                     return depalettizationDao.getDepalettizationsOrderByDateAndTrip(page, Converter.tripConverter2(trip));
                  
                case "date":
                    myParams = params[0].split("=");
                    String date = myParams[1];
                     return depalettizationDao.getDepalettizationsByDateOrderByDate(page, date.trim());
                default:
                    break;
            }
        }
        
        
          if (params.length==2) {
              for (int i = 0; i < params.length; i++) {
                  String[] newParams = params[i].split(" = ");
                  parameters.put(newParams[0].trim(), newParams[1].trim());
              }
              
              if (parameters.containsKey("state")&&parameters.containsKey("date")) {
              return  depalettizationDao.getDepalettizationsByStateAndDateOrderByDate(page,Boolean.parseBoolean(parameters.get("state")), parameters.get("date"));
        }
        
          
         if (parameters.containsKey("state")&&parameters.containsKey("trip")) {
              return  depalettizationDao.getDepalettizationsByStateAndTripOrderByDate(page, Boolean.parseBoolean(parameters.get("state")), Converter.tripConverter2(parameters.get("trip")));
        }
          
         
         if (parameters.containsKey("date")&&parameters.containsKey("trip")) {
             return  depalettizationDao.getDepalettizationsByDateAndTripOrderByDate(page,Converter.tripConverter2(parameters.get("trip")) ,parameters.get("date"));
        }
        }
          
          
           if (params.length==3) {
              for (int i = 0; i < params.length; i++) {
                  String[] newParams = params[i].split(" = ");
                  parameters.put(newParams[0].trim(), newParams[1].trim());
              }
              
              return  depalettizationDao.getDepalettizationsByStateAndDateAndTripOrderByDate(page,Boolean.parseBoolean(parameters.get("state")), parameters.get("date"),Converter.tripConverter2(parameters.get("trip")));
       
        }
         
         
      
        
        return depalettizationDao.getDepalettizationsOrderByDate(page);
    }

    public Page<Depalettization> getValidatedDepalettizations(Pageable page) {
        return depalettizationDao.getValidatedDepalettizationsOrderByDate(page);
    }

    public Page<Depalettization> getUnValidatedDepalettizations(Pageable page) {
        return depalettizationDao.getUnValidatedDepalettizationsOrderByDate(page);
    }

    public Page<Depalettization> getDepalettizationsByDate(Date date, Pageable page) {
        return depalettizationDao.getDepalettizationsByDateOrderByDate(date, page);
    }

    public Page<Depalettization> getDepalettizationsByTrip(Trip trip, Pageable page) {
        return depalettizationDao.getDepalettizationsByTripOrderByDate(trip, page);
    }

    public Page<Depalettization> getDepalettizationsByCenter(Center center, Pageable page) {
        return depalettizationDao.getDepalettizationsFromCenterOrderByDate(center.getCenterId(), page);
    }

    public Page<Depalettization> getValidatedDepalettizationsByCenter(Center center, Pageable page) {
        return depalettizationDao.getValidatedDepalettizationsFromCenterOrderByDate(center.getCenterId(), page);
    }

    public Page<Depalettization> getUnValidatedDepalettizationsByCenter(Center center, Pageable page) {
        return depalettizationDao.getUnValidatedDepalettizationsFromCenterOrderByDate(center.getCenterId(), page);
    }

    public Depalettization validateDepalettization(long depalettizationId, AddStatementDto addStatementDto) {
        Depalettization findDepalettization = depalettizationDao.getDepalettizationByDepalettizationId(depalettizationId);

        Perform perform = null;
        if (findDepalettization != null) {
            ValidateNotification validateNotification = null;
            switch (Converter.stringToValidationType(addStatementDto.getType())) {

                case FULLSTATE:
                    if (findDepalettization.isEmptyValidated()) {
                        findDepalettization.setValidated(true);
                    }

                    findDepalettization.setFullValidated(true);
                    saveFull(addStatementDto);
                    validateNotification = notifyFull(findDepalettization, addStatementDto, depalettizationId);

                    break;
                case EMPTYSTATE:
                    if (findDepalettization.isFullValidated()) {
                        findDepalettization.setValidated(true);
                    }
                    findDepalettization.setEmptyValidated(true);
                    saveEmpty(addStatementDto);
                    validateNotification = notifyEmpty(findDepalettization, addStatementDto, depalettizationId);

                    break;
                default:
                    findDepalettization.setValidated(true);
                    findDepalettization.setEmptyValidated(true);
                    findDepalettization.setFullValidated(true);
                    notifyEmpty(findDepalettization, addStatementDto, depalettizationId);
                    notifyFull(findDepalettization, addStatementDto, depalettizationId);
                    saveEmpty(addStatementDto);
                    saveFull(addStatementDto);
                    break;

            }

            if (findDepalettization.isValidated()) {
                simpMessagingTemplate.convertAndSend("/depalettization/validateNotification/statistique/" + findDepalettization.getDriverAssignment().getCircuit().getCenter().getCenterId(), validateNotification);
            }

        }

        return depalettizationDao.save(findDepalettization);
    }

    private ValidateNotification notifyFull(Depalettization findDepalettization, AddStatementDto addStatementDto, long depalettizationId) {
        ValidateNotification validateNotification = new ValidateNotification();
        Perform perform = null;
        validateNotification.setDepalettization(findDepalettization);
        validateNotification.setAgent(addStatementDto.getAgent());
        validateNotification.setValidationType(ValidationType.FULLSTATE);
        perform = performDao.getPerformByDepalettizationAndAgentRole(depalettizationId, _Role.USER_MAG_FULL);
        validateNotification.setPerform(perform);
        ValidateNotification savedValidateNotification = validateNotificationDao.save(validateNotification);

        if (savedValidateNotification != null) {
            simpMessagingTemplate.convertAndSend("/depalettization/validateNotification/" +perform.getPerformAgent().getAgentId(), validateNotification);
        }

        return validateNotification;
    }

    private ValidateNotification notifyEmpty(Depalettization findDepalettization, AddStatementDto addStatementDto, long depalettizationId) {

        ValidateNotification validateNotification = new ValidateNotification();
        Perform perform = null;
        validateNotification.setDepalettization(findDepalettization);
        validateNotification.setAgent(addStatementDto.getAgent());
        validateNotification.setValidationType(ValidationType.EMPTYSTATE);
        perform = performDao.getPerformByDepalettizationAndAgentRole(depalettizationId, _Role.USER_MAG_EMPTY);
        validateNotification.setPerform(perform);
        ValidateNotification savedEmptyValidateNotification = validateNotificationDao.save(validateNotification);

        if (savedEmptyValidateNotification != null) {
            simpMessagingTemplate.convertAndSend("/depalettization/validateNotification/" + perform.getPerformAgent().getAgentId(), validateNotification);
        }

        return validateNotification;
    }

    public List<Depalettization> getDepalettizationsByDateAndAgentId(long agentId, String depalettizationDate) {

        return depalettizationDao.getDepalettizationsByDateAndAgent(depalettizationDate, agentId);
    }

    public Page<Depalettization> getDepalettizationsByCenterId(Pageable page, long centerId,String param) {
        
        String[] params = param.split(",");
        String[] myParams =null;
        
        HashMap<String, String> parameters = new HashMap<>();
        if (params.length==1) {
            myParams = params[0].split("=");
            String flag = myParams[0].trim();
            switch(flag) {
                case "state":
                    myParams = params[0].split("=");
                    boolean state =Boolean.parseBoolean(myParams[1].trim());
                    return depalettizationDao.getDepalettizationsOrderByDateByCenterIdAndState(page, centerId,state);
                   
                 case "trip": 
                     myParams = params[0].split("=");
                    String trip = myParams[1];
                     return depalettizationDao.getDepalettizationsOrderByDateByCenterIdAndTrip(page, centerId,Converter.tripConverter2(trip));
                  
                case "date":
                    myParams = params[0].split("=");
                    String date = myParams[1];
                     return depalettizationDao.getDepalettizationsOrderByDateByCenterIdAndDate(page, centerId,date.trim());
                default:
                    break;
            }
        }
        
        
          if (params.length==2) {
              for (int i = 0; i < params.length; i++) {
                  String[] newParams = params[i].split(" = ");
                  parameters.put(newParams[0].trim(), newParams[1].trim());
              }
              
              if (parameters.containsKey("state")&&parameters.containsKey("date")) {
              return  depalettizationDao.getDepalettizationsOrderByDateByCenterIdAndStateAndDate(page, centerId, Boolean.parseBoolean(parameters.get("state")), parameters.get("date"));
        }
        
          
         if (parameters.containsKey("state")&&parameters.containsKey("trip")) {
              return  depalettizationDao.getDepalettizationsOrderByDateByCenterIdAndStateAndTrip(page, centerId, Boolean.parseBoolean(parameters.get("state")), Converter.tripConverter2(parameters.get("trip")));
        }
          
         
         if (parameters.containsKey("date")&&parameters.containsKey("trip")) {
             return  depalettizationDao.getDepalettizationsOrderByDateByCenterIdAndDateAndTrip(page, centerId,Converter.tripConverter2(parameters.get("trip")) ,parameters.get("date"));
        }
        }
          
          
           if (params.length==3) {
              for (int i = 0; i < params.length; i++) {
                  String[] newParams = params[i].split(" = ");
                  parameters.put(newParams[0].trim(), newParams[1].trim());
              }
              
              return  depalettizationDao.getDepalettizationsOrderByDateByCenterIdAndStateAndDateAndTrip(page, centerId, Boolean.parseBoolean(parameters.get("state")), parameters.get("date"),Converter.tripConverter2(parameters.get("trip")));
       
        }
         
         
      
        return depalettizationDao.getDepalettizationsOrderByDateByCenterId(page, centerId);
    }

    public Page<Depalettization> getValidatedDepalettizationsByCenterId(Pageable page, long centerId, String param) {
        
         String[] params = param.split(",");
        String[] myParams =null;
        
        HashMap<String, String> parameters = new HashMap<>();
        if (params.length==1) {
            myParams = params[0].split("=");
            String flag = myParams[0].trim();
            switch(flag) {
                 case "trip": 
                     myParams = params[0].split("=");
                    String trip = myParams[1];
                     return depalettizationDao.getValidatedDepalettizationsOrderByDateByCenterIdAndTrip(page, centerId,Converter.tripConverter2(trip));
                  
                case "date":
                    myParams = params[0].split("=");
                    String date = myParams[1];
                     return depalettizationDao.getValidatedDepalettizationsOrderByDateByCenterIdAndDate(page, centerId,date.trim());
                case "isVisited":
                    myParams = params[0].split("=");
                    boolean visiteState = Boolean.parseBoolean(myParams[1].trim());
                     return depalettizationDao.getValidatedDepalettizationsOrderByDateByCenterIdAndVisited(page, centerId,visiteState);
                default:
                    break;
            }
        }
        
          if (params.length==2) {
              for (int i = 0; i < params.length; i++) {
                  String[] newParams = params[i].split(" = ");
                  parameters.put(newParams[0].trim(), newParams[1].trim());
              }
                
         if (parameters.containsKey("date")&&parameters.containsKey("trip")) {
             return  depalettizationDao.getValidatedDepalettizationsOrderByDateByCenterIdAndDateAndTrip(page, centerId,Converter.tripConverter2(parameters.get("trip")) ,parameters.get("date"));
        }
         
               if (parameters.containsKey("isVisited")&&parameters.containsKey("date")) {
              return  depalettizationDao.getValidatedDepalettizationsOrderByDateByCenterIdAndDateAndVisited(page, centerId, Boolean.parseBoolean(parameters.get("isVisited")), parameters.get("date"));
        }
        
          
         if (parameters.containsKey("isVisited")&&parameters.containsKey("trip")) {
              return  depalettizationDao.getValidatedDepalettizationsOrderByDateByCenterIdAndTripAndVisited(page, centerId, Boolean.parseBoolean(parameters.get("isVisited")), Converter.tripConverter2(parameters.get("trip")));
        }
          }
          
          if (params.length==3) {
              for (int i = 0; i < params.length; i++) {
                  String[] newParams = params[i].split(" = ");
                  parameters.put(newParams[0].trim(), newParams[1].trim());
              }
              
               return  depalettizationDao.getValidatedDepalettizationsOrderByDateByCenterIdAndStateAndDateAndTrip(page, centerId, Boolean.parseBoolean(parameters.get("isVisited")), parameters.get("date"),Converter.tripConverter2(parameters.get("trip")));
          }
        return depalettizationDao.getValidatedDepalettizationsOrderByDateByCenterId(page, centerId);
    }

    public Statement saveFull(AddStatementDto addStatementDto) {

        Statement findStatement = statementDao.getStatementByTripAndDateFromCenter(addStatementDto.getStatement().getCenter().getCenterId(), addStatementDto.getStatement().getTrip(), addStatementDto.getStatement().getStatementDate());
        Statement savedStatement = null;
        if (findStatement == null) {
            savedStatement = statementDao.save(addStatementDto.getStatement());
        }
        StatementDetail statementDetail = statementDetailDao.getStatementDetailsByCenterAndTripAndStatementDateAndDriverAssigment(addStatementDto.getStatementDetailFull().getStatement().getCenter().getCenterId(), addStatementDto.getStatementDetailFull().getStatement().getTrip(),
                addStatementDto.getStatementDetailFull().getStatement().getStatementDate(), addStatementDto.getStatementDetailFull().getDriverAssignment().getDriverAssignmentId());
        if (statementDetail != null) {
            statementDetail.setNumberOfLockerFullDepalettization(addStatementDto.getStatementDetailFull().getNumberOfLockerFullDepalettization());
            statementDetailDao.save(statementDetail);
        } else {
            if (savedStatement != null) {
                addStatementDto.getStatementDetailFull().getStatement().setStatementId(savedStatement.getStatementId());
            } else {
                addStatementDto.getStatementDetailFull().getStatement().setStatementId(findStatement.getStatementId());
            }

            statementDetailDao.save(addStatementDto.getStatementDetailFull());
        }
        return savedStatement;
    }

    public Statement saveEmpty(AddStatementDto addStatementDto) {
        Statement findStatement = statementDao.getStatementByTripAndDateFromCenter(addStatementDto.getStatement().getCenter().getCenterId(), addStatementDto.getStatement().getTrip(), addStatementDto.getStatement().getStatementDate());
        Statement savedStatement = null;
        if (findStatement == null) {
            savedStatement = statementDao.save(addStatementDto.getStatement());
        }

        StatementDetail statementDetail = statementDetailDao.getStatementDetailsByCenterAndTripAndStatementDateAndDriverAssigment(addStatementDto.getStatementDetailEmpty().getStatement().getCenter().getCenterId(), addStatementDto.getStatementDetailEmpty().getStatement().getTrip(),
                addStatementDto.getStatementDetailEmpty().getStatement().getStatementDate(), addStatementDto.getStatementDetailEmpty().getDriverAssignment().getDriverAssignmentId());

        if (statementDetail != null) {
            statementDetail.setNumberOfLockerEmptyDepalettization(addStatementDto.getStatementDetailEmpty().getNumberOfLockerEmptyDepalettization());
            statementDetailDao.save(statementDetail);
        } else {
            if (savedStatement != null) {
                addStatementDto.getStatementDetailEmpty().getStatement().setStatementId(savedStatement.getStatementId());
            } else {
                addStatementDto.getStatementDetailEmpty().getStatement().setStatementId(findStatement.getStatementId());
            }
            statementDetailDao.save(addStatementDto.getStatementDetailEmpty());
        }

        return savedStatement;
    }
}
