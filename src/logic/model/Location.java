package logic.model;


public class Location {
	
	String region;
	String province;
	String city;
	
	public Location(String region, String province, String city) {
		this.region = region;
		this.province = province;
		this.city = city;
	}
	
	public Location() {
		
	}
	
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
}
