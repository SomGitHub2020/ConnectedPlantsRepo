/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package connectedplants.connectedplants;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	Production production = new Production();

	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //2019-04-26T07:00:00
	Date date = new Date();
		
	@GetMapping
	public String index() {
		return "redirect:/welcome";
	}

	@GetMapping("/welcome")
	public String welcomeForm(Model model) {


		return "welcome";
	}

	@PostMapping("/welcomeProd") 
	public String prodWelcomeForm(Model model) {
	
		Calendar c = Calendar.getInstance();
		c.setTime(date);
	    
	    c.add(Calendar.DATE, -1);
	    
	    Date prevdate = c.getTime();
		
		production.setFromdate(sdf.format(prevdate));
		production.setFromHH("00");
		production.setFromMM("00");
		production.setFromSS("00");
		
		model.addAttribute("production", production);

		return "production"; 
	}

	@PostMapping("/welcomeRes")
	public String resWelcomeForm() {

		return "resource";
	}

	@PostMapping("/production")
	public String prodSubmit(Model model, @ModelAttribute Production production) {

		List<SiteOrder> listSiteOrderAs = new ArrayList<SiteOrder>();
		List<SiteOrder> listSiteOrderBs = new ArrayList<SiteOrder>();
		
		List<OrderSFC> listOrderSfcAs = new ArrayList<OrderSFC>();
		List<OrderSFC> listOrderSfcBs = new ArrayList<OrderSFC>();

		String siteAInput = "PPME";
		String siteBInput = "EXID";
		
		String selectedOrderA = "1009505";
		String selectedOrderB = "1000486";
		
		/*
		 * String prefixShopOrderBO_A = "ShopOrderBO:"+siteAInput+","+selectedOrderA;
		 * String prefixShopOrderBO_B = "ShopOrderBO:"+siteAInput+","+selectedOrderA;
		 */

		String fromDt = production.getFromdate();
		String fromHours = production.getFromHH();
		String fromMins = production.getFromMM();
		String fromSeconds = production.getFromSS();
		
		String fromDateTime = fromDt + "T" + fromHours +":"+fromMins+":"+fromSeconds;
		
		//String fromDateTime = "2019-04-26T07:00:00";

		ShowProdAnalysisDetails showprodanalysisdata = new ShowProdAnalysisDetails();

		try {
			showprodanalysisdata.displaySiteOrder(listSiteOrderAs, siteAInput,fromDateTime);
			showprodanalysisdata.displaySiteOrder(listSiteOrderBs, siteBInput,fromDateTime);
			
			showprodanalysisdata.displayOrderSFC(listOrderSfcAs, siteAInput, fromDateTime, selectedOrderA);
			showprodanalysisdata.displayOrderSFC(listOrderSfcBs, siteBInput, fromDateTime, selectedOrderB);
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("siteorder_as", listSiteOrderAs);
		model.addAttribute("siteorder_bs", listSiteOrderBs);
		
		model.addAttribute("ordersfc_as", listOrderSfcAs);
		model.addAttribute("ordersfc_bs", listOrderSfcBs);

		return "prod_analysis";
	}

	@PostMapping("/resource")
	public String resSubmit(Model model) {

		String resource = "ResourceBO:EXID,RES1-1";
		
		String reasonCodeCSV = "";
		
		ShowResAnalysisDetails showresanalysisdata = new ShowResAnalysisDetails();

		try {
			reasonCodeCSV = showresanalysisdata.getReasonCodesDuration(resource);
			

						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String arr[] = reasonCodeCSV.split(",");
        //first, add the regional sales
        Integer northeastSales = Integer.parseInt(arr[0].split("\\.")[0]);
        Integer westSales = Integer.parseInt(arr[1].split("\\.")[0]);
        Integer midwestSales = Integer.parseInt(arr[2].split("\\.")[0]);
        Integer southSales = Integer.parseInt(arr[3].split("\\.")[0]);
        
        model.addAttribute("northeastSales", northeastSales);
        model.addAttribute("southSales", southSales);
        model.addAttribute("midwestSales", midwestSales);
        model.addAttribute("westSales", westSales);
        
        //now add sales by lure type
        Map<String,Integer> barChartData = new HashMap<>();
        
        for(int i=0; i<arr.length; i++)
        {
        	barChartData.put("Resource_"+(i+1),Integer.parseInt(arr[i].split("\\.")[0]));
        }
        
		/*
		 * barChartData.put("Samsung",5000L); barChartData.put("Iphone",10000L);
		 * barChartData.put("MI",2000L); barChartData.put("Lava",4000L);
		 * barChartData.put("Oppo",3560L); barChartData.put("HTC",5560L);
		 */
        
        model.addAttribute("barChartData",barChartData);
		
		return "res_analysis";
	}
}
