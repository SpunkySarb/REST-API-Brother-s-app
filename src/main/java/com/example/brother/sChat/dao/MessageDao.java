package com.example.brother.sChat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageDao {

	/**
	 * Connection object to connect to database
	 */
	    static Connection conn = null;
	    private String json;
	public String getJson() {
			return json;
		}




		public void setJson(String json) {
			this.json = json;
		}




	/**
	 * Constructor to connect to database
	 */
	    
	    public MessageDao() {
	    	
	    	 try {
		            conn = DriverManager.getConnection(
		                    "jdbc:mysql://92.249.44.105/u107689122_info?" + "user=u107689122_merisite&password=0CandyCrush0");

		            Statement read = null;
		            ResultSet rs = null;
		            

		            try {
		                read = conn.createStatement();
		                rs =   read.executeQuery(
		                        "select `json` from brothers WHERE `brothers`.`id` = 1; ");
		                while (rs.next()) {

		                    setJson(rs.getString("json"));
		                }
		                read.close();
		            } catch (SQLException ex) {
		                // handle any errors
		                System.out.println("SQLException: " + ex.getMessage());
		                System.out.println("SQLState: " + ex.getSQLState());
		                System.out.println("VendorError: " + ex.getErrorCode());
		            }

		            
		            System.out.println("Log 1 : Connected To Database");
		        } catch (SQLException ex) {
		            // handle any errors
		            System.out.println("SQLException: " + ex.getMessage());
		            System.out.println("SQLState: " + ex.getSQLState());
		            System.out.println("VendorError: " + ex.getErrorCode());
		        }
	    }
	    
	    
	    
	    
	    public MessageDao(String data) {

	        try {
	            conn = DriverManager.getConnection(
	                    "jdbc:mysql://92.249.44.105/u107689122_info?" + "user=u107689122_merisite&password=0CandyCrush0");

	            Statement update = null;

	            try {
	                update = conn.createStatement();
	                update.executeUpdate(
	                        "UPDATE `brothers` SET `json` = '"+data+"' WHERE `brothers`.`id` = 1; ");

	                update.close();
	            } catch (SQLException ex) {
	                // handle any errors
	                System.out.println("SQLException: " + ex.getMessage());
	                System.out.println("SQLState: " + ex.getSQLState());
	                System.out.println("VendorError: " + ex.getErrorCode());
	            }

	            
	            System.out.println("Log 1 : Connected To Database");
	        } catch (SQLException ex) {
	            // handle any errors
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	        }


	    }
}

