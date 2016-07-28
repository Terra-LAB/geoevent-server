package geocollector.dao;

import java.util.List;

import org.hibernate.Session;

import geocollector.model.Layer;
import geocollector.util.HibernateUtil;

public class LayerDaoImpl implements LayerDao {
	
	@Override
	public Layer insertLayer (Layer layer) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(layer);
			session.getTransaction().commit();
			session.close();
			return layer;
		}
		catch (Exception e) {
			System.err.println("LayerDaoImpl.insertLayer(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	@Override
	public Layer getLayer(String layerName, int userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Layer layer = null;
		try {
			session.beginTransaction();
			
			String sqlQuery = "SELECT * FROM LAYER L WHERE L.user_id = :userId and L.name = :name";			
			layer = (Layer) session.createSQLQuery(sqlQuery).addEntity(Layer.class).setParameter("userId", userId).setParameter("name", layerName).list().get(0);
			int idLayer = layer.getId();
			
			String hqlQuery = "from Layer l where l.id = :idLayer";
			layer = (Layer)session.createQuery(hqlQuery).setParameter("idLayer", idLayer).list().get(0);
			
			session.getTransaction().commit();
			session.close();
			return layer;
		} catch (Exception e){
			System.err.println("LayerDaoImpl.getLayer(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}
	
	@Override
	public boolean update(Layer layer) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			session.update(layer);
			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			System.err.println("LayerDaoImpl.update(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

	@Override
	public boolean exists(String name, int userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try {
			session.beginTransaction();
			
			String sqlQuery = "SELECT * FROM LAYER L WHERE L.user_id = :userId and L.name = :name";			
			Layer layer = (Layer) session.createSQLQuery(sqlQuery).addEntity(Layer.class).setParameter("userId", userId).setParameter("name", name).list().get(0);
			int idLayer = layer.getId();
			
			String hqlQuery = "from Layer l where l.id = :idLayer";
			layer = (Layer)session.createQuery(hqlQuery).setParameter("idLayer", idLayer).list().get(0);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e){
			System.err.println("LayerDaoImpl.exists(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

	@Override
	public boolean delete(Layer layer) {
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try
		{
			session.beginTransaction();
			session.delete(layer);
			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			System.err.println("LayerDaoImpl.delete(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

	@Override
	public List<Layer> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();		
		List<Layer> layers = null;
		try
		{
			session.beginTransaction();
			layers = session.createCriteria(Layer.class).list();
			session.getTransaction().commit();
			session.close();
			return layers;
		}
		catch(Exception e)
		{
			System.err.println("LayerDaoImpl.getAll(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return layers;
		}
	}	
}
