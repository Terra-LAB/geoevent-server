package geocollector.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POINT")
public class Point implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "layer_id")
	private Layer layer;

	private int idPointDevice;

	private String macAdress;

	private String layerName;

	private String name;
	private String country;
	private String state;
	private String city;
	private String neighborhood;
	private String street;
	private String number;
	private String supplement;
	private String postalCode;
	private String type;
	private String description;
	private double latitude;
	private double longitude;
	private String color;

	private String collectDate;

	public Point() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPointDevice() {
		return idPointDevice;
	}

	public void setIdPointDevice(int idPointDevice) {
		this.idPointDevice = idPointDevice;
	}

	public String getMacAdress() {
		return macAdress;
	}

	public void setMacAdress(String macAdress) {
		this.macAdress = macAdress;
	}

	public Layer getLayer() {
		return layer;
	}

	public void setLayer(Layer layer) {
		this.layer = layer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getType() {
		return type;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}

	public String toCsv() {
		return id + "," + latitude + "," + longitude;
	}
/*	public String toCsv() {
		return id + "," + macAdress + "," + layer + "," + name + "," + street + "," + number + "," + neighborhood + ","
				+ type + "," + description + "," + latitude + "," + longitude + "," + collectDate;
	} */

	public String toKml() {
		String data = "<![CDATA[";

		data = data + "\n Layer:" + layer + "\n" + "Nome:" + name + ", Rua" + street + ", Número " + number + ", Bairro"
				+ neighborhood + "\n Descrição: " + description + "\n Latitude" + latitude + ",Longitude: " + longitude
				+ " ]]>";

		return data;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", idPointDevice=" + idPointDevice + ", macAdress=" + macAdress
				+ ", layerName=" + layerName + ", name=" + name + ", country=" + country + ", state=" + state
				+ ", city=" + city + ", neighborhood=" + neighborhood + ", street=" + street + ", number=" + number
				+ ", supplement=" + supplement + ", postalCode=" + postalCode + ", type=" + type + ", description="
				+ description + ", latitude=" + latitude + ", longitude=" + longitude + ", color=" + color
				+ ", collectDate=" + collectDate + "]";
	}
	
	

}
