package connectedplants.connectedplants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowResAnalysisDetails {

	public String getReasonCodesDuration(String resource) throws IOException {
		
		String result="";
		String durationlist = "";
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection( "jdbc:mysql://connplantservice:3306/connplantsdb?user=root&password=VySU8WBweuVYNx3T&useSSL=false");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(
            		"SELECT RESOURCE , FLOOR(RAND()*(100)) FROM `RESOURCE_TIME_LOG` "
            		+ "WHERE RESOURCE = '"+resource+"' "
            		+ "ORDER BY START_DATE_TIME desc "
			/* + "LIMIT 4" */
            		);  
            while(rs.next()){  
               
                String duration = rs.getString(2);
      
                durationlist = durationlist + "," + duration;//+ result;
                
            }
            durationlist = durationlist.replaceFirst(",", "");
            result = "SUCCESS";
            con.close();
            //return result;
        }
        catch(Exception e){
            e.printStackTrace();
            result = "ERROR";
        }
        //System.out.println(e);
        return durationlist;
		
	}

	
}
