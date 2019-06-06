package hibernate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReizigerDaoImpl extends Main implements ReizigerDao {

	public List<Reiziger> listReizigers() {
		List<Reiziger> Reizigers = new ArrayList<Reiziger>();
		  Session session = getFactory().openSession();
		  Transaction t = null;

		  try {
			  t = session.beginTransaction();
			  List reizigers = session.createQuery("FROM hibernate.Reiziger").list();
			  for (Iterator iterator = reizigers.iterator(); iterator.hasNext();) {
				  Reiziger reiziger = (Reiziger) iterator.next();

				  Reiziger Reiziger = new Reiziger(reiziger.getReizigerid(), reiziger.getVoorletter(), reiziger.getTussenvoegsel()
				  , reiziger.getAchternaam(), reiziger.getGbdatum());
				  Reiziger.setOvchipkaarten(reiziger.getOvchipkaarten());
				  Reizigers.add(Reiziger);
			  }
			  t.commit();
		  } catch (HibernateException e) {
			  if(t != null) t.rollback();
			  e.printStackTrace();
		  }
		  return Reizigers;
	  }
	
	public List<Reiziger> findReizigerById(Reiziger reiziger) {
		List<Reiziger> Reizigers = new ArrayList<Reiziger>();
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  
		  try {
			  t = session.beginTransaction();
				  Reiziger r = (Reiziger) session.get(Reiziger.class, reiziger.getReizigerid());

			  Reiziger Reiziger = new Reiziger(r.getReizigerid(), r.getVoorletter(), r.getTussenvoegsel()
					  , r.getAchternaam(), r.getGbdatum());
			  Reiziger.setOvchipkaarten(r.getOvchipkaarten());
			  Reizigers.add(Reiziger);

			  t.commit();
		  } catch (HibernateException e) {
			  if(t != null) t.rollback();
			  e.printStackTrace();
		  }
		return Reizigers;
	  }
	
	 public Integer addReiziger(Reiziger r) {
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  Integer reizigerid = null;
		  
		  try {
			  t = session.beginTransaction();
			  Reiziger reiziger = new Reiziger(r.getReizigerid(), r.getVoorletter(), r.getTussenvoegsel(), r.getAchternaam(), r.getGbdatum());
			  reizigerid = (Integer) session.save(reiziger);
			  t.commit();
		  } catch (HibernateException e) {
			  if (t != null) t.rollback();
			  e.printStackTrace();
		  } finally {
			  session.close();
		  }
		  return reizigerid;
	  }
	 
	 public void updateReiziger(Reiziger updatedReiziger) {
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  
		  try {
			  t = session.beginTransaction();

			  session.update(updatedReiziger);
			  t.commit();
		  } catch (HibernateException e) {
			  if (t != null) t.rollback();
			  e.printStackTrace();
		  } finally {
			  session.close();
		  }
	  }
	 
	 public void deleteReiziger(Reiziger r) {
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  
		  try {
			  t = session.beginTransaction();
			  Reiziger reiziger = (Reiziger) session.get(Reiziger.class, r.getReizigerid());
			  
			  session.delete(reiziger);
			  t.commit();	  
		  } catch (HibernateException e) {
			  if (t != null) t.rollback();
			  e.printStackTrace();
		  } finally {
			  session.close();
		  }
		    
	  }
	
}
