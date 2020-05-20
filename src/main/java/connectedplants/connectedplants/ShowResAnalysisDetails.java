package connectedplants.connectedplants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ShowResAnalysisDetails {

	public String getLinePerformance(List<WCPerformance> listWCPerf,String siteInput) throws IOException {
		
		String result="";
	
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection( "jdbc:mysql://connplantservice:3306/connplantsdb?user=root&password=VySU8WBweuVYNx3T&useSSL=false");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(
            		"SELECT DISTINCT WORKCENTER, FLOOR(RAND()*(100)) FROM `RESOURCE_MASTER` "
            		+ "WHERE SITE = '"+siteInput+"' "
            		+ "GROUP BY WORKCENTER "
            		+ "ORDER BY WORKCENTER " 
            		+ "LIMIT 5" 
            		);  
            while(rs.next()){  
               
                String wc = rs.getString(1);
            	String perf = rs.getString(2);
      
            	listWCPerf.add(new WCPerformance(wc, perf));
                
            }
            result = "SUCCESS";
            con.close();
            //return result;
        }
        catch(Exception e){
            e.printStackTrace();
            result = "ERROR";
        }
        //System.out.println(e);
        return result;
	
	}
	
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

	public String displayResourceLogData(List<ResourceLog> listResLog,String siteInput, String resource) throws IOException {
		
		 String result="";
	        try{  
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con=DriverManager.getConnection( "jdbc:mysql://connplantservice:3306/connplantsdb?user=root&password=VySU8WBweuVYNx3T&useSSL=false");  
	            Statement stmt=con.createStatement();  
	            ResultSet rs=stmt.executeQuery(
	            		"SELECT * FROM `RESOURCE_TIME_LOG` "
	            		+ "WHERE SITE ='"+siteInput+"' "
	            		+ "AND RESOURCE = '"+resource+"' "
	            		+ "ORDER BY START_DATE_TIME desc "
	            		);  
	            while(rs.next()){  
	               
	                String site = rs.getString(1);
	                String res = rs.getString(2);
	                String desc = rs.getString(3);
	                String startdate = rs.getString(4);
	                String enddate = rs.getString(5);
	                String status = rs.getString(7);
	                
	                listResLog.add(new ResourceLog(site, res, desc, startdate, enddate, status));
	            }
	            result = "SUCCESS";
	            con.close();
	            //return result;
	        }
	        catch(Exception e){
	            e.printStackTrace();
	            result = "ERROR";
	        }
	        //System.out.println(e);
	        return result;
		
	}

	
}
