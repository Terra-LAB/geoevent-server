package geocollector.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import geocollector.model.User;
import geocollector.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	public User insertUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			session.beginTransaction();
			session.persist(user);
			session.getTransaction().commit();
			session.close();
			return user;

		} catch (Exception e) {
			System.err.println("UserDaoImpl.insertUser(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	@Override
	public boolean exists(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String hqlQuery = "from User u where u.email = :email";
			User user = (User) session.createQuery(hqlQuery).setParameter("email", email).list().get(0);
			session.getTransaction().commit();
			session.close();

			return true;

		} catch (Exception e) {
			System.err.println("UserDaoImpl.exists(): nao encontrou email. : " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}

	}

	@Override
	public User login(String email, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = null;
		try {
			session.beginTransaction();
			String hqlQuery = "from User u where u.email = :email and u.password = :senha";
			user = (User) session.createQuery(hqlQuery).setParameter("email", email).setParameter("senha", password)
					.list().get(0);
			session.getTransaction().commit();
			session.close();
			return user;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = null;

		try {
			session.beginTransaction();
			String hqlQuery = "from User u where u.email = :email";
			user = (User) session.createQuery(hqlQuery).setParameter("email", email).list().get(0);
			session.getTransaction().commit();
			session.close();
			return user;
		} catch (Exception e) {
			System.err.println("UserDaoImpl.findByEmail(): nao encontrou email. : " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	@Override
	public List<User> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> list = null;

		try {
			session.beginTransaction();
			list = session.createCriteria(User.class).list();
			session.getTransaction().commit();
			session.close();
			return list;

		} catch (ConstraintViolationException e) {
			System.err.println("UserDaoImpl.getAll(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return list;
		}

	}

	@Override
	public int getId(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = null;

		try {
			session.beginTransaction();
			String hqlQuery = "from User u where u.email = :email";
			user = (User) session.createQuery(hqlQuery).setParameter("email", email).list().get(0);
			session.getTransaction().commit();
			session.close();
			return user.getId();

		} catch (Exception e) {
			System.err.println("UserDaoImpl.getId(): nao encontrou email. : " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return -1;
		}
	}

	@Override
	public User findById(int idUser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = null;
		try {
			session.beginTransaction();
			String hqlQuery = "from User u where u.id = :id";
			user = (User) session.createQuery(hqlQuery).setParameter("id", idUser).list().get(0);
			session.getTransaction().commit();
			session.close();
			return user;
		} catch (Exception e) {
			System.err.println("UserDaoImpl.findById(): nao encontrou ID. : " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	@Override
	public boolean active(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			User u = (User) session.load(User.class, user.getId());

			u.setActivated(true);

			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			System.err.println("UserDaoImpl.active(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

	@Override
	public boolean updatePassword(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			User u = (User) session.load(User.class, user.getId());
			u.setPassword(user.getPassword());
			session.update(u);
			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			System.err.println("UserDaoImpl.updatePassword(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

	@Override
	public boolean update(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(user);
			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			System.err.println("UserDaoImpl.update(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

	@Override
	public void delete(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(user);
			session.flush();
			session.getTransaction().commit();
			session.close();

		} catch (HibernateException e) {
			System.err.println("UserDaoImpl.delete(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
		}
	}
}
