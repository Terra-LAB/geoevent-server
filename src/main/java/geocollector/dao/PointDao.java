package geocollector.dao;

import java.util.List;

import geocollector.model.Point;

public interface PointDao {
	
	public Point insertPoint (Point point);
	
	public List<Point> getAll ();
	
	public Point findById(int idPoint);
	
	public boolean update (Point point);
	
	public boolean delete (Point p);
	
	public boolean exists(int id);
	
}
