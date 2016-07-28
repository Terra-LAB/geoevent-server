package geocollector.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="ADDRESS_DATABASE")
public class AddressDatabase implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="user_id", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="user"))
    private int userId;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	private String name;
	private String urlDatabase;
	
	public AddressDatabase (){
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlDatabase() {
		return urlDatabase;
	}

	public void setUrlDatabase(String urlDatabase) {
		this.urlDatabase = urlDatabase;
	}

	@Override
	public String toString() {
		return "AdressDatabase [userId=" + userId + ", user=" + user
				+ ", name=" + name + ", urlDatabase=" + urlDatabase + "]";
	}
	
	
	
}