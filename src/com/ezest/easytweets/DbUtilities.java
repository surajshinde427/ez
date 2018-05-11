package com.ezest.easytweets;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbUtilities {
	
	public  ArrayList<String> getTablesList(Connection conn) throws SQLException{
        
		 
		  ArrayList<String> listofTable = new ArrayList<String>();

		 DatabaseMetaData dbm = conn.getMetaData();
		
		  String[] types = {"TABLE"};
		  ResultSet rs = dbm.getTables(null, "talentPool","%" ,new String[] {"TABLE"} );
		 
		  while (rs.next()){
			  String table = rs.getString(1);
		  		if(table.equals("talentPool"))
		  			listofTable.add(rs.getString(3));
		  }

	        return listofTable;
	    }

}
