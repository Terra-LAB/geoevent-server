package geocollector.model;


public class Form {

	private int idPoint;
	private int idUser;
	private String macAddress = "";
	private String layer = "";
	private String name = "";
	private String country = "";
	private String state = "";
	private String city = "";
	private String neighborhood = "";
	private String street = "";
	private String number = "";
	private String supplement;
	private String postalCode;
	private String type = "";
	private String description = "";
	private String latitude = "";
	private String longitude = "";
	private String collectDate = "";
	private String color = "";
	private int idServer = 0;

	public Form() {
	}

	public Form(int idPoint, String latitude, String longitude) {
		this.idPoint = idPoint;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Form(int idPoint, int idUser, String macAddress, String layer, String name, String country, String state,
			String city, String street, String number, String neighborhood, String supplement, String postalCode,
			String type, String description, String collectDate, String latitude, String longitude, String color,
			int idServer) {
		this.idPoint = idPoint;
		this.idUser = idUser;
		this.macAddress = macAddress;
		this.layer = layer;
		this.name = name;
		this.country = country;
		this.state = state;
		this.city = city;
		this.neighborhood = neighborhood;
		this.street = street;
		this.number = number;
		this.supplement = supplement;
		this.postalCode = postalCode;
		this.type = type;
		this.description = description;
		this.collectDate = collectDate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.color = color;
		this.idServer = idServer;
	}

	public int getIdPoint() {
		return idPoint;
	}

	public void setIdPoint(int idPoint) {
		this.idPoint = idPoint;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSupplement() {
		return supplement;
	}

	public void setSupplement(String supplement) {
		this.supplement = supplement;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getIdServer() {
		return idServer;
	}

	public void setIdServer(int idServer) {
		this.idServer = idServer;
	}

	@Override
	public String toString() {
		return "Form [idPoint=" + idPoint + ", idUser=" + idUser + ", macAddress=" + macAddress + ", layer=" + layer
				+ ", name=" + name + ", country=" + country + ", state=" + state + ", city=" + city + ", neighborhood="
				+ neighborhood + ", street=" + street + ", number=" + number + ", supplement=" + supplement
				+ ", postalCode=" + postalCode + ", type=" + type + ", description=" + description + ", latitude="
				+ latitude + ", longitude=" + longitude + ", collectDate=" + collectDate + ", color=" + color
				+ ", idServer=" + idServer + "]";
	}

	
	
}