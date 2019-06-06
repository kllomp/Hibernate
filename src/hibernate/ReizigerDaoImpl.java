package hibernate;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReizigerDaoImpl extends Main implements ReizigerDao {

	public void listReizigers() {
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  
		  try {
			  t = session.beginTransaction();
			  List reizigers = session.createQuery("FROM hibernate.Reiziger").list();
			  for (Iterator iterator = reizigers.iterator(); iterator.hasNext();) {
				  Reiziger reiziger = (Reiziger) iterator.next();
				  
				  System.out.println("Reizigerid: " + reiziger.getReizigerid());
				  System.out.println("Voorletters: " + reiziger.getVoorletter());
				  System.out.println("Tussenvoegsel: " + reiziger.getTussenvoegsel());
				  System.out.println("Achternaam: " + reiziger.getAchternaam());
				  System.out.println("Geboortedatum: " + reiziger.getGbdatum());
				  System.out.println("OVkaarten: " + reiziger.getOvchipkaarten().toString() + "\n	");
				  
			  }
			  t.commit();
		  } catch (HibernateException e) {
			  if(t != null) t.rollback();
			  e.printStackTrace();
		  } finally {
			  session.close();
		  }  
	  }
	
	public Reiziger findReizigerById(Reiziger reiziger) {
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  
		  Reiziger reizigers = null;
		  
		  try {
			  t = session.beginTransaction();
				  Reiziger r = (Reiziger) session.get(Reiziger.class, reiziger.getReizigerid());
				  
				  System.out.println("Reizigerid: " + r.getReizigerid());
				  System.out.println("Voorletters: " + r.getVoorletter());
				  System.out.println("Tussenvoegsel: " + r.getTussenvoegsel());
				  System.out.println("Achternaam: " + r.getAchternaam());
				  System.out.println("Geboortedatum: " + r.getGbdatum());
				  System.out.println("OVkaarten: " + r.getOvchipkaarten().toString() + "\n	");


			  reizigers = r;
			  t.commit();
		  } catch (HibernateException e) {
			  if(t != null) t.rollback();
			  e.printStackTrace();
		  } finally {
			  session.close();
		  }
		return reizigers;
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
			  Reiziger reiziger = (Reiziger) session.get(Reiziger.class, updatedReiziger.getReizigerid());
			  reiziger.setVoorletter(updatedReiziger.getVoorletter());
			  reiziger.setTussenvoegsel(updatedReiziger.getTussenvoegsel());
			  reiziger.setAchternaam(updatedReiziger.getAchternaam());
			  reiziger.setGbdatum(updatedReiziger.getGbdatum());
			  
			  session.update(reiziger);
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
