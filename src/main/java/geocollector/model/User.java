package geocollector.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	int id;

	String name;

	@Column(unique = true)
	String email;

	String password;

	boolean activated;
	boolean facebookAccount;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private AddressDatabase addressDatabase;

	@OneToMany(mappedBy = "user", targetEntity = Layer.class, cascade = CascadeType.ALL, orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Layer> layers;

	private String tokenPassword;

	private String birthday;
	private String gender;

	@Temporal(TemporalType.DATE)
	private Date registeredDate;

	public User() {
		this.layers = new LinkedList<Layer>();
		this.addressDatabase = null;
		this.registeredDate = new Date();
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isFacebookAccount() {
		return facebookAccount;
	}

	public void setFacebookAccount(boolean facebookAccount) {
		this.facebookAccount = facebookAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTokenPassword() {
		return tokenPassword;
	}

	public void setTokenPassword(String tokenPassword) {
		this.tokenPassword = tokenPassword;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public AddressDatabase getAddressDatabase() {
		return addressDatabase;
	}

	public void setAddressDatabase(AddressDatabase addressDatabase) {
		this.addressDatabase = addressDatabase;
	}

	public void addLayer(Layer l) {
		layers.add(l);
	}

	public Layer getLayerByName(String layerName) {
		Layer mLayer = null;
		for (Layer l : layers) {
			if (l.getName().equals(layerName)) {
				mLayer = l;
				break;
			}
		}
		return mLayer;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", activated="
				+ activated + ", facebookAccount=" + facebookAccount + ", role=" + role + ", addressDatabase="
				+ addressDatabase + ", layers=" + layers.toString() + ", tokenPassword=" + tokenPassword + ", birthday=" + birthday
				+ ", gender=" + gender + ", registeredDate=" + registeredDate + "]";
	}

}
