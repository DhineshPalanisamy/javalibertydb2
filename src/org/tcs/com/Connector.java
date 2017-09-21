package org.tcs.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Connector {
	

    String url1;
    String user1;
    String password1;
	 Connection connection=null;
	 Statement statement=null;
	 Context context;
	 DataSource dataSource;
	 Statement stat=null;
	 Statement stat1=null;
	 Statement stat2=null;
	 Statement stat3=null;
	 Statement stat4=null;
	 Statement stat5=null;
	 ResultSet rs=null;
    ResultSet rc=null;
    ResultSet rd=null;
    ResultSet ra=null;
    flightbean flight =new flightbean();
    double[] prediction = null;
    
    
	 public Connector() throws Exception{
		 
	 try
	 	{
		 
		System.out.println("inside constructor");
		Class.forName("com.ibm.db2.jcc.DB2Driver");
        System.out.println("Class Loaded successfully");
        context = new InitialContext();
        dataSource = (DataSource) context.lookup("jdbc/defaultCICSDataSource");
        System.out.println("Connecting.........");
        connection = dataSource.getConnection();
        url1 = "jdbc:db2:STPDB11";
        //url1="jdbc:default:connection";
		user1 = "U827495";
		password1 = "neel1016";
		//connection = DriverManager.getConnection(url1); 
        System.out.println("Connected successfully");

/*
		  connection=DriverManager.getConnection("jdbc:db2://172.16.111.107:4007/STPDB11:user=U827495;password=neel1016;");
		  System.out.println("Connected successfully");
          */
		  
		  statement=connection.createStatement();
		  stat=connection.createStatement();
		  stat1=connection.createStatement();
		  stat2=connection.createStatement();
		  stat3=connection.createStatement();
		  stat4=connection.createStatement();
		  stat5=connection.createStatement();
	  
	  }
	 	catch(ClassNotFoundException e)
	 	{
		e.printStackTrace();
	 	}
	
	 	catch(SQLException e)
	 	{
		e.printStackTrace();
	 	}
  }
	
	
	public flightbean offerdata(String passport_number) throws Exception
	{
		 String sql="SELECT PASS_NO,PERSON_NAME,FLIGHT_NUM FROM U737966.PERSON_INFO WHERE PASS_NO='"+passport_number+"'";
		   try{
			   
			   rs=statement.executeQuery(sql);
			    while(rs.next())
			     {
			    	
			    	
				    flight.setFlight_num(rs.getString("FLIGHT_NUM"));
				    flight.setName(rs.getString("PERSON_NAME"));
				    flight.setPassenger_no(rs.getString("PASS_NO"));
				    
			       
			        
			   
				    String sql_stat="SELECT FLIGHT_DEST,FLIGHT_NAME,FLIGHT_SOURCE FROM U737966.AIRLINE_INFO WHERE FLIGHT_NUM='"+flightbean.getFlight_num()+"'";
				    System.out.println("Flight Number:"+flightbean.getFlight_num());
				    
				   
				       rc=stat2.executeQuery(sql_stat);
				    while(rc.next())
				     {
				    	flight.setDestination(rc.getString("FLIGHT_DEST"));
				    	flight.setFlight_name(rc.getString("FLIGHT_NAME"));
				    	flight.setSource(rc.getString("FLIGHT_SOURCE"));
				    	 System.out.println("\nDestination:"+flightbean.getDestination());
				      	 
					         System.out.println("\nAirways Name:"+flightbean.getFlight_name());
					         System.out.println("\nSource:"+flightbean.getSource());
				    	
				     }
			     }
			    
		  
		}
		   catch(SQLException e)
		   {
			   e.printStackTrace();
		   }
		   finally
		   {
			   statement.close();
			   stat.close();
			   stat2.close();
			   stat3.close();
			   
		   }
		     
	                return flight;
			      
			   }		
	}

