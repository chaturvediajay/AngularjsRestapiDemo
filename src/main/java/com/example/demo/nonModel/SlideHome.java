package com.example.demo.nonModel;

public class SlideHome {
	private String url;
	private String title;
	private String pkey;
	private String mrp;
	private String smrp;
	private String size;
	private String description;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	
	public String getSmrp() {
		return smrp;
	}
	public void setSmrp(String smrp) {
		this.smrp = smrp;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "mrp: "+mrp+" title:-"+title+"  pkey:-"+pkey+"  url"+url;
	}

}

