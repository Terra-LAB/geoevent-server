package geocollector.dao;

import java.util.List;

import geocollector.model.Layer;
import geocollector.model.Point;
import geocollector.model.User;

public interface UserDao {
	
	public User insertUser (User user);
	
	public User login (String email, String password);

	public User findByEmail (String email);
	
	public boolean exists (String email);
	
	public List<User> getAll ();
	
	public int getId (String email);
		
	public User findById(int idUser);
	
	public boolean active (User user);
	
	public boolean updatePassword (User user);

	public boolean update(User user);
	
	public void delete (User user);

}
