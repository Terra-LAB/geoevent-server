package geocollector.dao;

import java.util.List;

import org.hibernate.Session;

import geocollector.model.Point;
import geocollector.util.HibernateUtil;

public class PointDaoImpl implements PointDao {

	@Override
	public Point insertPoint(Point point) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(point);
			session.getTransaction().commit();
			session.close();
			return point;
		} catch (Exception e) {
			System.err.println("PointDaoImpl.insertPoint(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	@Override
	public List<Point> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Point> points = null;
		try {
			session.beginTransaction();
			points = session.createCriteria(Point.class).list();
			session.getTransaction().commit();
			session.close();
			return points;
		} catch (Exception e) {
			System.err.println("PointDaoImpl.getAll(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return points;
		}
	}

	@Override
	public Point findById(int idPoint) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Point p = null;
		try {
			session.beginTransaction();
			String hqlQuery = "from Point p where p.id = :id";
			p = (Point) session.createQuery(hqlQuery).setParameter("id", idPoint).list().get(0);
			session.getTransaction().commit();
			session.close();
			return p;
		} catch (Exception e) {
			System.err.println("PointDaoImpl.findById(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	@Override
	public boolean update(Point point) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(point);
			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			System.err.println("PointDaoImpl.update(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

	@Override
	public boolean delete(Point point) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(point);
			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			System.err.println("PointDaoImpl.delete(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

	@Override
	public boolean exists(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String hqlQuery = "from Point p where p.id = :id";
			Point point = (Point) session.createQuery(hqlQuery).setParameter("id", id).list().get(0);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			System.err.println("PointDaoImpl.exists(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

}
