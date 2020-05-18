package connectedplants.connectedplants;

public class SiteList {
	
	private String site;
	private String desc;
	
	public SiteList(String site, String desc)
	{
		super();
		this.site = site;
		this.desc = desc;
	};
		
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
