package hibernate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OVChipkaartDAOImpl extends Main implements OVChipkaartDAO {

	public String listOv() {
		List<String> Kaarten = new ArrayList<String>();
		  Session session = getFactory().openSession();
		  Transaction t = null;
		  
		  try {
			  t = session.beginTransaction();
			  List ovkaarten = session.createQuery("FROM hibernate.OVchipkaart").list();
			  for (Iterator iterator = ovkaarten.iterator(); iterator.hasNext();) {
				  OVchipkaart ovkaart = (OVchipkaart) iterator.next();
				  OVchipkaart kaart = new OVchipkaart(ovkaart.getKaartnummer(),  ovkaart.getSaldo(), ovkaart.getKlasse(), ovkaart.getGeldig(), ovkaart.getEigenaar());
				  String kaart2 = kaart.toString2();
				  Kaarten.add(kaart2);
			  }
			  t.commit();
		  } catch (HibernateException e) {
			  if(t != null) t.rollback();
			  e.printStackTrace();
		  }
		  return Kaarten + "\n";
	  }
	
	public String findOvById(OVchipkaart chip) {
		List<String> Kaarten = new ArrayList<String>();
		Session session = getFactory().openSession();
		Transaction t = null;
		  
		  try {
			  t = session.beginTransaction();
				  OVchipkaart ovkaart = (OVchipkaart) session.get(OVchipkaart.class, chip.getKaartnummer());

			  OVchipkaart kaart = new OVchipkaart(ovkaart.getKaartnummer(),  ovkaart.getSaldo(), ovkaart.getKlasse(), ovkaart.getGeldig(), ovkaart.getEigenaar());
			  String kaart2 = kaart.toString2();
			  Kaarten.add(kaart2);

			  t.commit();
		  } catch (HibernateException e) {
			  if(t != null) t.rollback();
			  e.printStackTrace();
		  }
		return Kaarten +  "\n";
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
			  
			  session.update(updatedOv);
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
