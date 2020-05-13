package connectedplants.connectedplants;

public class ResourceLog {
	
	public String site;
	public String resource;
	public String desc;
	public String startdt;
	public String enddt;
	public String status;
	
	public ResourceLog() {
		
	}

	public ResourceLog(String site, String resource, String desc, String startdt, String enddt, String status) {
		super();
		this.site = site;
		this.resource = resource;
		this.desc = desc;
		this.startdt = startdt;
		this.enddt = enddt;
		this.status = status;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStartdt() {
		return startdt;
	}

	public void setStartdt(String startdt) {
		this.startdt = startdt;
	}

	public String getEnddt() {
		return enddt;
	}

	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
