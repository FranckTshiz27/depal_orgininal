/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.util;

/**
 *
 * @author J.LUTUNDULA
 */
public class DateConverter {



public static String getTimeFromDate(String date) {
int position = date.indexOf(".");
String convertedDate = date.substring(0,position);
convertedDate = convertedDate.replace("T"," ");
position = convertedDate.indexOf(" ");
return convertedDate.substring(position,convertedDate.length());
}

public static String getDate(String date) {
int position = date.indexOf("T");
String convertedDate = date.substring(0,position);
return convertDateToFrenchFormat(convertedDate);
}
public static String convertDateToFrenchFormat(String date){
String formattedDate ="";
String[] dates = date.split("-");

formattedDate = dates[2]+" "+numberToMonth(dates[1])+" "+dates[0];
return formattedDate;
}
private static String numberToMonth(String number){
String month="";
switch (number.toLowerCase()){
case"01":
month="Janvier";
break;
case"02":
month="Février";
break;
case"03":
month="Mars";
break;
case"04":
month="Avril";
break;
case"05":
month="Mai";
break;
case"06":
month="Juin";
break;
case"07":
month="Juillet";
break;
case"08":
month="Août";
break;
case"09":
month="Septembre";
break;
case"10":
month="Octobre";
break;
case"11":
month="Novembre";
break;
case"12":
month="Décembre";
break;
}


return month;
}
}
