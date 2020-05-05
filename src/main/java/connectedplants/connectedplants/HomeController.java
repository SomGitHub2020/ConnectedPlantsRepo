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
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

		
	@GetMapping
    public String index() {
      return "redirect:/welcome";
    }
	
	@GetMapping("/welcome")
	  public String welcomeForm(Model model) {
	    
		return "welcome";
	  }
	
	@GetMapping("/production")
	  public String prodForm(Model model) {
	    model.addAttribute("production", new Production());
		return "production";
	  }
	
	
	
	  @PostMapping("/welcomeProd")
	  public String prodWelcomeForm() {

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
		  
		  String siteAInput = "PPME";
		  String siteBInput = "EXID";
		  
		  //String fromDt = production.getFromdate();
		  String fromDt = "2019-04-26T07:00:00";
          
          ShowProdAnalysisDetails showprodanalysisdata = new ShowProdAnalysisDetails();
          
          try {
			showprodanalysisdata.displaySiteOrder(listSiteOrderAs, siteAInput,fromDt);
			showprodanalysisdata.displaySiteOrder(listSiteOrderBs, siteBInput,fromDt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

          model.addAttribute("siteorder_as", listSiteOrderAs);
          model.addAttribute("siteorder_bs", listSiteOrderBs);
          
		  return "prod_analysis";
	  }
	  
	  @PostMapping("/resource")
	  public String resSubmit() {

		  return "res_analysis";
	  }
}
