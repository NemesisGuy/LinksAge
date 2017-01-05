/**
*By Nemesis v1.3
*Date : 1/1/2017
*Name : LinksAge.java
*Purpose : This application is intened for use with the LINKS A.I. It Calculates how old the current instilation of LINKS is.
**/
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Period;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;
import java.io.IOException;

public class LinksAge {

	public static void main (String [] args)throws IOException {
		//Create date format
		SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");

		//Get current date and install Date type
	 	Date currentDate  = new Date();
		Date creationDate = new Date(getFileCreationDate());
	//	Date creationDate = ft.parse("13.04.1989");// test case
		//Format date
		String creationDateFormat = ft.format(creationDate);
		//Convert to LocalDate type
		LocalDate currentLocalDate = fromDate(currentDate);
		LocalDate creationLocalDate = fromDate(creationDate);

		//Output
		myDays(creationLocalDate, currentLocalDate ,creationDateFormat);//Works out diff in years, months, days!
	}
	//Finds and gets file creation date
	public static long getFileCreationDate() throws IOException{
		//Builds Path and make object
		String prePath = System.getenv("APPDATA");
		Path path = Paths.get(prePath +"\\LINKS");
		BasicFileAttributes attr;
		long fileCreatedTime = 0;
		try { //try to connect to file and retrive creation time in mills
				attr = Files.readAttributes(path, BasicFileAttributes.class);
				fileCreatedTime = attr.creationTime().toMillis();
		} catch (IOException e) { // print error if fails to retive file info
		    	System.out.println("Oops error! Could not get file info. Error : "  + e.getMessage());
		}
			return 	fileCreatedTime ;
		}
	//converts date to LocalDate type
	public static LocalDate fromDate(Date date) {
		Instant instant = Instant.ofEpochMilli(date.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

	}
	//Prints neg and equal case and calls timePreiods(int,String).
	public static void myDays( LocalDate startLocalDate , LocalDate currentLocalDate, String creationDateFormat){
		Period p = Period.between(startLocalDate, currentLocalDate );
		//Neg case
		if (p.getYears()<0 || p.getMonths()<0 || p.getDays()<0){
			System.err.println("Current system time precedes the programs installation date. Please set system  date and time correctly.");
			//Equal case/Same Date
		}else if (startLocalDate.equals(currentLocalDate)){
			System.out.println("I was initialized Today");
		}else{
			System.out.print("I was initialized on " + creationDateFormat + ", so I am ");
			timePreiods(p.getYears() ," year");
			timePreiods(p.getMonths() ," month");
			timePreiods(p.getDays() ," day");
			System.out.print("old. ");
			System.out.println();
		}
	}
	//Prints int timeAmout string years/months/days
	public static void timePreiods(int periodsAmount ,String periodsName){
	/*	if (periodsAmount==1){
			System.out.print(periodsAmount + periodsName+" ");
		}else if (periodsAmount>1){
			System.out.print(periodsAmount + periodsName +"s ");
		}*/
		String amountsAndNames = (periodsAmount==1) ? periodsAmount + periodsName+" " : ( (periodsAmount>1 )? periodsAmount + periodsName +"s " : "") ;
		System.out.print(amountsAndNames);



	}



}
