package hibernate;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OVChipkaartDAOImpl extends Main implements OVChipkaartDAO {

	public void listOv() {
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  
		  try {
			  t = session.beginTransaction();
			  List ovkaarten = session.createQuery("FROM hibernate.OVchipkaart").list();
			  for (Iterator iterator = ovkaarten.iterator(); iterator.hasNext();) {
				  OVchipkaart ovkaart = (OVchipkaart) iterator.next();
				  System.out.println("Kaartnummer: " + ovkaart.getKaartnummer());
				  System.out.println("Geldig tot: " + ovkaart.getGeldig());
				  System.out.println("Klasse: " + ovkaart.getKlasse());
				  System.out.println("Saldo: " + ovkaart.getSaldo());
				  System.out.println(ovkaart.getEigenaar().toString() + "\n");

			  }
			  t.commit();
		  } catch (HibernateException e) {
			  if(t != null) t.rollback();
			  e.printStackTrace();
		  } finally {
			  session.close();
		  }  
	  }
	
	public OVchipkaart findOvById(OVchipkaart chip) {
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  
		  OVchipkaart ov = null;
		  
		  try {
			  t = session.beginTransaction();
				  OVchipkaart ovkaart = (OVchipkaart) session.get(OVchipkaart.class, chip.getKaartnummer());

				  System.out.println("Kaartnummer: " + ovkaart.getKaartnummer());
				  System.out.println("Geldig tot: " + ovkaart.getGeldig());
				  System.out.println("Klasse: " + ovkaart.getKlasse());
				  System.out.println("Saldo: " + ovkaart.getSaldo());
				  System.out.println(ovkaart.getEigenaar().toString() + "\n");
				  
				  ov = ovkaart;
			  t.commit();
		  } catch (HibernateException e) {
			  if(t != null) t.rollback();
			  e.printStackTrace();
		  } finally {
			  session.close();
		  }
		return ov;  
	  }
	
	 public Integer addOv(OVchipkaart chip) {
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  Integer ovid = null;
		  
		  try {
			  t = session.beginTransaction();
			  OVchipkaart ovkaart = new OVchipkaart(chip.getKaartnummer(), chip.getSaldo(), chip.getKlasse(), chip.getGeldig(), chip.getReizigerid());
			  ovid = (Integer) session.save(ovkaart);
			  t.commit();
		  } catch (HibernateException e) {
			  if (t != null) t.rollback();
			  e.printStackTrace();
		  } finally {
			  session.close();
		  }
		  return ovid;
	  }
	 
	 
	 public void updateOv(OVchipkaart updatedOv) {
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  
		  try {
			  t = session.beginTransaction();
			  OVchipkaart ovkaart = (OVchipkaart) session.get(OVchipkaart.class, updatedOv.getKaartnummer());
			  ovkaart.setSaldo(updatedOv.getSaldo());
			  ovkaart.setGeldig(updatedOv.getGeldig());
			  ovkaart.setKlasse(updatedOv.getKlasse());
			  ovkaart.setReizigerid(updatedOv.getReizigerid());
			  
			  session.update(ovkaart);
			  t.commit();
		  } catch (HibernateException e) {
			  if (t != null) t.rollback();
			  e.printStackTrace();
		  } finally {
			  session.close();
		  }
	  }
	 
	 public void deleteOv(OVchipkaart chip) {
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  
		  try {
			  t = session.beginTransaction();
			  OVchipkaart ovkaart = (OVchipkaart) session.get(OVchipkaart.class, chip.getKaartnummer());
			  
			  session.delete(ovkaart);
			  t.commit();	  
		  } catch (HibernateException e) {
			  if (t != null) t.rollback();
			  e.printStackTrace();
		  } finally {
			  session.close();
		  }
		    
	  }
}
