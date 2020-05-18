package connectedplants.connectedplants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class SiteDetails {

	public void getSiteList(List<SiteList> lstSiteLists) throws IOException {
		
		String result="";
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection( "jdbc:mysql://connplantservice:3306/connplantsdb?user=root&password=VySU8WBweuVYNx3T&useSSL=false");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(
            		"SELECT distinct SITE, DESCRIPION FROM `SITE_MASTER` "
            		+ "WHERE SITE ='*' "
            		);  
            while(rs.next()){  
               
                String site = rs.getString(1);
                String desc = rs.getString(2);
                
                lstSiteLists.add(new SiteList(site, desc));
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
       
		
	}

}
