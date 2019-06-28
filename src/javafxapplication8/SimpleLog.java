/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8;

/**
 *
 * @author sina
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

public final class SimpleLog{
    
	private static final SimpleLog instance = new SimpleLog();
	public String logname = "Log";
	protected String env = System.getProperty("user.dir");
	private static File logFile;

	public static SimpleLog getInstance(){
		return instance;
	}

	public static SimpleLog getInstance(String withName){
		instance.logname = withName;
		instance.createLogFile();
		return instance;
	}

	public void createLogFile(){
		//Determine if a logs directory exists or not.
		File logsFolder = new File(env + '/' + "logs");
		if(!logsFolder.exists()){
			//Create the directory 
			System.err.println("INFO: Creating new logs directory in " + env);
			logsFolder.mkdir();
			
		}

		//Get the current date and time
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   	Calendar cal = Calendar.getInstance();
	   	
	   	//Create the name of the file from the path and current time
		logname =  logname + '-' +  dateFormat.format(cal.getTime()) + ".log";
		SimpleLog.logFile = new File(logsFolder.getName(),logname);
		try{
			if(logFile.createNewFile()){
				//New file made
				System.err.println("INFO: Creating new log file");	
			}
		}catch(IOException e){
			System.err.println("ERROR: Cannot create log file");
			System.exit(1);
		}
	}

	SimpleLog(){
		if (instance != null){
			//Prevent Reflection
			throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
		}
		this.createLogFile();
	}

	public static void log(String message){
		try{
			BufferedWriter out = new BufferedWriter( new FileWriter(SimpleLog.logFile, true));
                        message = "[ "+getCurrentTime()+" ] "+message;
			out.write(message);
                        out.newLine();
			out.close();
		}catch(IOException e){
			System.err.println("ERROR: Could not write to log file");
		}
	}
        public static String getCurrentTime(){

            System.out.println("-----Current time of your time zone-----");

            LocalTime time = LocalTime.now();

            System.out.println("Current time of the day: " + time);
            return ""+time;

 }
        
        public static void history(int i){
		try{
                        String Resault = "" ;
                        if (i!=-1){
                        switch (i){
//                            case -1 : Resault = "Game Closed";
                            case 1 : Resault = "Player Win";
                            case 2 : Resault = "Enemy Win";
                        }}
                        else{
                            Resault = "Game Closed";
                        }
			BufferedWriter out = new BufferedWriter( new FileWriter("./History.history", true));
                        String message = "[ "+getCurrentTime()+" ] : " + Resault;
			out.write(message);
                        out.newLine();
			out.close();
		}catch(IOException e){
			System.err.println("ERROR : Could not write to history file");
		}
	}

}