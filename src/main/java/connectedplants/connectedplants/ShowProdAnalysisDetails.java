package connectedplants.connectedplants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ShowProdAnalysisDetails {
	
	public String displaySiteOrder(List<SiteOrder> listSiteOrder, String siteInput, String fromDt) throws IOException{
        String result="";
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection( "jdbc:mysql://connplantservice:3306/connplantsdb?user=root&password=VySU8WBweuVYNx3T&useSSL=false");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT * FROM `SHOP_ORDER` WHERE SITE ='"+siteInput+"' AND MODIFIED_DATE_TIME > '"+fromDt+"'");  
            while(rs.next()){  
               
                String site = rs.getString(1);
                String shoporder = rs.getString(2);
                String item = rs.getString(3);
                String qty_to_build = rs.getString(4);
                String qty_done = rs.getString(5);
                String qty_scrapped = rs.getString(6);
                String modified_dt = rs.getString(7);
                
                listSiteOrder.add(new SiteOrder(site, shoporder, item, qty_to_build, qty_done, qty_scrapped, modified_dt));
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
