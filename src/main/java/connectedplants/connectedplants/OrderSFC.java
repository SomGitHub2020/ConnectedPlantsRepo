package connectedplants.connectedplants;

public class OrderSFC {
	
	public String site;
	public String sfc;
	public String order;
    public String qty;
    public String qtydone;
    public String qtyscrapped;
    public String status;
    public String modified_dt;
    
	public OrderSFC() {
				
	}

	public OrderSFC(String site, String sfc, String order, String qty, String qtydone, String qtyscrapped, String status,
			String modified_dt) {
		super();
		this.site = site;
		this.sfc = sfc;
		this.order = order;
		this.qty = qty;
		this.qtydone = qtydone;
		this.qtyscrapped = qtyscrapped;
		this.status = status;
		this.modified_dt = modified_dt;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSfc() {
		return sfc;
	}

	public void setSfc(String sfc) {
		this.sfc = sfc;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getQtydone() {
		return qtydone;
	}

	public void setQtydone(String qtydone) {
		this.qtydone = qtydone;
	}

	public String getQtyscrapped() {
		return qtyscrapped;
	}

	public void setQtyscrapped(String qtyscrapped) {
		this.qtyscrapped = qtyscrapped;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModified_dt() {
		return modified_dt;
	}

	public void setModified_dt(String modified_dt) {
		this.modified_dt = modified_dt;
	}
	
	
}
