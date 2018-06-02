package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateJcar {
	// now			/get/data/now
	// 10:10		/get/data/date=<NOW>&index=<10:10>
	
	// 10:10-23:15	/get/history/date=<NOW>  (api corta)   route
	
	// 10:10 23/09/2018 		/get/data/date=<23/09/2018>&index=<10:10>
	// 10:10-23:15 23/09/2018	/get/history/date=<23/09/2018>&index=<10:10>   route
	
	boolean isNow;
	
	boolean isHourToday;
	boolean isHourAnotherDay;
	
	boolean isRouteAnotherDay;
	boolean isRouteToday;
	
	String dateHour;
	
	String hourInit, hourFinal;
	String hour, date;
	
	public DateJcar(String dateHour) {
		this.dateHour = dateHour;
		this.processDateHour();
	}
	
	public String getValue() {
		if (this.isNow){
			return "/get/data/now";
		}
		else if (this.isHourToday){		
			return "/get/data/date=now&index="+this.hour;
		}
		
		else if (this.isHourAnotherDay){
			return "/get/data/date="+this.date+"&index="+this.hour;
		}
		
		else if (this.isRouteToday){
			return "/route?date=now&start="+this.hourInit+"&end="+this.hourFinal;
		}
		
		else if (this.isRouteAnotherDay){

			return "/route?"
					+ "date="  +this.date
					+ "&start="+this.hourInit
					+ "&end="  +this.hourFinal;
		}		
		
		return null;

	}
	
	private void processDateHour() {
	
		String hour, hourFinal, hourInit;
		String date;
		
		if (this.dateHour.equals("now")){
			this.isNow = true;
		} 
		else if (this.dateHour.length() == 5) {

			if (this.isValidHour(this.dateHour)) {
				this.isHourToday = true;
				this.hour = this.dateHour.replaceAll(":", "");
			}
			
		} 
		else if (this.dateHour.length() == 11) {

			hourInit = this.dateHour.substring(0, 5);
			hourFinal = this.dateHour.substring(6, 11);
			
			if (this.isValidHour(hourInit) && this.isValidHour(hourFinal)) {
				this.isRouteToday = true;
				this.hourInit = hourInit.replaceAll(":", "");
				this.hourFinal = hourFinal.replaceAll(":", "");
			}
			
		}else if(this.dateHour.length() == 16) {
			hour = this.dateHour.substring(0, 5);
			date = this.dateHour.substring(6, 16);

			System.out.println(hour);
			System.out.println(date);
			
			if (this.isValidHour(hour) && this.isValidDate(date)) {
				this.isHourAnotherDay = true;
				this.hour = hour.replaceAll(":", ""); 
				this.date = this.dateToUTC(date);
			}
			
		} else if (this.dateHour.length() == 22) {
			
			hourInit = this.dateHour.substring(0, 5);
			hourFinal = this.dateHour.substring(6, 11);
			date = this.dateHour.substring(12, 22);

			if (this.isValidHour(hourInit) && this.isValidHour(hourFinal) && isValidDate(date)) {
				this.isRouteAnotherDay = true;
				
				this.hourInit = hourInit.replaceAll(":", "");
				this.hourFinal = hourFinal.replaceAll(":", "");	
				this.date = this.dateToUTC(date);
			}
		}
		
	}
		
	private String dateToUTC(String date) {
		String day = date.substring(0, 2);
		String month = date.substring(3, 5);
		String year = date.substring(6, 10);
		return year+month+day;
	}
	
	public boolean isValidHour(String hour){
		
		String[] hourParse = hour.split(":");
		
		for (char item : hourParse[0].toCharArray()) {
			if (! Character.isDigit(item)) {
				return false;
			}
		}
		
		for (char item : hourParse[1].toCharArray()) {
			if (! Character.isDigit(item)) {
				return false;
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.setLenient(false);
		try{
			sdf.parse(hour);
		}catch(ParseException e){
			return false;
		}
		return true;
	}
	
	private boolean isValidDate(String date) {
		DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
		df.setLenient (false); 
		try {
		    df.parse (date);
		} catch (ParseException ex) {
			return false;
		}
		return true;
	}
}