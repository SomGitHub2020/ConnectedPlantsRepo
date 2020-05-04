package connectedplants.connectedplants;

public class SiteOrder {
	
	public String site;
	public String order;
    public String material;
    public String buildqty;
    public String yieldqty;
    public String scrappedqty;
    public String modified_dt;
    
    public SiteOrder() {
		
	}

	public SiteOrder(String site, String order, String material, String buildqty, String yieldqty, String scrappedqty,
			String modified_dt) {
		super();
		this.site = site;
		this.order = order;
		this.material = material;
		this.buildqty = buildqty;
		this.yieldqty = yieldqty;
		this.scrappedqty = scrappedqty;
		this.modified_dt = modified_dt;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getBuildqty() {
		return buildqty;
	}

	public void setBuildqty(String buildqty) {
		this.buildqty = buildqty;
	}

	public String getYieldqty() {
		return yieldqty;
	}

	public void setYieldqty(String yieldqty) {
		this.yieldqty = yieldqty;
	}

	public String getScrappedqty() {
		return scrappedqty;
	}

	public void setScrappedqty(String scrappedqty) {
		this.scrappedqty = scrappedqty;
	}

	public String getModified_dt() {
		return modified_dt;
	}

	public void setModified_dt(String modified_dt) {
		this.modified_dt = modified_dt;
	}
    
    
	
}
