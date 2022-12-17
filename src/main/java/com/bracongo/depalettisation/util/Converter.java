/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.util;

import com.bracongo.depalettisation.enumerations.ValidationType;
import com.bracongo.depalettisation.enumerations.Shift;
import com.bracongo.depalettisation.enumerations.Trip;
import static com.bracongo.depalettisation.enumerations.Trip.CINQUIEME;
import static com.bracongo.depalettisation.enumerations.Trip.DEUXIEME;
import static com.bracongo.depalettisation.enumerations.Trip.PREMIER;
import static com.bracongo.depalettisation.enumerations.Trip.QUATRIEME;
import static com.bracongo.depalettisation.enumerations.Trip.TROISIEME;
import com.bracongo.depalettisation.enumerations._Role;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author J.LUTUNDULA
 */
public class Converter {
    
  public static String dateToString(Date date){
  
      return new SimpleDateFormat("yyyy-MM-dd").format(date);
  }
  

  public static String validationTypeToString(ValidationType type){
  
      switch(type){
          case EMPTYSTATE:
              return "EMPTYSTATE";
          case FULLSTATE:
              return "FULLSTATE";
              default:
                  return "DEPALETTIZATIONSTATE";
      
      }
  }
  
  public static ValidationType stringToValidationType(String type){
      switch(type){
          case "EMPTYSTATE":
              return ValidationType.EMPTYSTATE;
          case "FULLSTATE":
              return ValidationType.FULLSTATE;
              default:
                  return ValidationType.DEPALETTIZATIONSTATE;
      
      }
  }

     public static Trip tripConverter(String trip){
        Trip value;
        switch (trip){
            case "1":
                value = Trip.PREMIER;
                break;
            case "2":
                value = Trip.DEUXIEME;
                break;
            case "3":
                value = Trip.TROISIEME;
                break;
            case "4":
                value = Trip.QUATRIEME;
                break;
            case "5":
                value = Trip.CINQUIEME;
                break;
            default:
                value = Trip.SIXIEME;
                break;
        }
        return value;
    }
     
      public static Trip tripConverter2(String trip){
        Trip value;
        switch (trip.trim()){
            case "PREMIER":
                value = Trip.PREMIER;
                break;
            case "DEUXIEME":
                value = Trip.DEUXIEME;
                break;
            case "TROISIEME":
                value = Trip.TROISIEME;
                break;
            case "QUATRIEME":
                value = Trip.QUATRIEME;
                break;
            case "CINQUIEME":
                value = Trip.CINQUIEME;
                break;
            default:
                value = Trip.SIXIEME;
                break;
        }
        return value;
    }

    public static String  tripToString(Trip trip){

        switch (trip){
            case PREMIER:
               return "1";
            case DEUXIEME:
                return "2";
            case TROISIEME:
                return "3";
            case QUATRIEME:
                return "4";
            case CINQUIEME:
                return "5";
            default:
                return "6";
        }
    }
    
     public static  String shiftToString(Shift shift){
        switch (shift){
            case PREMIER:
                return "06 - 12";
            case  DEUXIEME:
                return "12 - 18";
            default:
                return "18 - 06";
        }
    }

    public static  Shift stringToShift(String shift){
        switch (shift){
            case "06 - 12":
                return Shift.PREMIER;
            case  "12 - 18":
                return Shift.DEUXIEME;
            default:
                return Shift.TROISIEME;
        }
    }
    
    
     public static  _Role stringToRole(String role){
        switch (role){
            case "FULL":
                return _Role.USER_MAG_FULL;
            default:
                return _Role.USER_MAG_EMPTY;  
        }
    }
}
