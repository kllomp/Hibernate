package hibernate;

import java.sql.Date;

public interface OVChipkaartDAO {
	
	public void listOv(); // Find all method
	public OVchipkaart findOvById(int id);
	public Integer addOv(int id, double sal, int klasse, Date geldigtot, int reizigerid);
	public void updateOv(int id, OVchipkaart updatedOv);
	public void deleteOv(int id);
	
}

