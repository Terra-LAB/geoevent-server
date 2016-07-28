package geocollector.dao;

import org.hibernate.Session;

import geocollector.model.AddressDatabase;
import geocollector.util.HibernateUtil;

public class AddressDatabaseDaoImpl implements AddressDatabaseDao {

	@Override
	public boolean remove(AddressDatabase addressDatabase) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(addressDatabase);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		catch (Exception e) {
			System.err.println("AddressDaoImpl.remove(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

	@Override
	public AddressDatabase find(int addressId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		AddressDatabase addressDatabase = null;
		try{
			session.beginTransaction();
			String hqlQuery = "from AddressDatabase db where db.userId = :id";
			addressDatabase = (AddressDatabase) session.createQuery(hqlQuery).setParameter("id", addressId).list().get(0);
			session.getTransaction().commit();
			session.close();
			return addressDatabase;
		}
		catch (Exception e){
			System.err.println("AddressDaoImpl.find(): " + e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return addressDatabase;
		}
	}

}
