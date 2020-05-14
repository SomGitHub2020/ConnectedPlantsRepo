package connectedplants.connectedplants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                String item = rs.getString(3).replaceAll("ItemBO:"+siteInput+",", "").replaceAll(",A", "");
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

	public String displayOrderSFC(List<OrderSFC> listOrderSfc, String siteInput, String fromDateTime, String selectedOrder) throws IOException {
		
        String result="";
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection( "jdbc:mysql://connplantservice:3306/connplantsdb?user=root&password=VySU8WBweuVYNx3T&useSSL=false");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT * FROM `SFC` WHERE SITE ='"+siteInput+"' AND MODIFIED_DATE_TIME > '"+fromDateTime+"' AND SHOP_ORDER LIKE '%"+selectedOrder+"'");  
            while(rs.next()){  
               
                String site = rs.getString(1);
                String sfc = rs.getString(2);
                String order = rs.getString(3);
                String qty = rs.getString(4);
                String qty_done = rs.getString(5);
                String qty_scrapped = rs.getString(6);
                String status = rs.getString(7);
                String modified_dt = rs.getString(8);
                
                listOrderSfc.add(new OrderSFC(site, sfc, order, qty, qty_done, qty_scrapped, status, modified_dt));
            }
            result = "SUCCESS";
            con.close();
            //return result;
        }
        catch(Exception e){
            e.printStackTrace();
            result = "ERROR";
        }
		
		return result;
		
	}

	public String displayOrderQtyData(String siteInput, String fromDateTime) throws IOException {
		
		String result="";
		String orderTargetActualQty = "";
		String qry = "";
	
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection( "jdbc:mysql://connplantservice:3306/connplantsdb?user=root&password=VySU8WBweuVYNx3T&useSSL=false");  
            Statement stmt=con.createStatement();  
             qry = "SELECT SUM(QTY_TO_BUILD), SUM(QTY_DONE) FROM `SHOP_ORDER` "
            		+ "WHERE SITE ='"+siteInput+"' "
            		+ "AND MODIFIED_DATE_TIME > '"+fromDateTime+"' ";
            ResultSet rs=stmt.executeQuery(
            		qry
            		);  
            
            while(rs.next()){   
            orderTargetActualQty = rs.getString(1) + "," + rs.getString(2);
            }   
           
            result = "SUCCESS";
            con.close();
            //return result;
        }
        catch(Exception e){
        	System.out.println("SAP MII qry statement: "+qry);
             	
            //e.printStackTrace();
            result = "ERROR";
        }
		
		return orderTargetActualQty;
	}
	
}
