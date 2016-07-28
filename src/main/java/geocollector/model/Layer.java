package geocollector.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "LAYER")
public class Layer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String name;

	private String color;

	@OneToMany(mappedBy = "layer", targetEntity = Point.class, cascade = CascadeType.ALL, orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Point> points;
	
	public Layer() {
		this.points = new LinkedList<Point>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public void addPoint (Point p){
		this.points.add(p);
	}

	public Point getPoint(int idPoint) {
		List<Point> userPoints = getPoints();
		Point point = null;

		for (Point p : userPoints) {
			if (p.getId() == idPoint) {
				point = p;
			}
		}
		return point;
	}

	@Override
	public String toString() {
		return "Layer [id=" + id + ", user=" + user.getName() + ", name=" + name + ", color=" + color + ", points=" + points.toString()
				+ "]";
	}
	
	

}
