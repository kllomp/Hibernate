package hibernate;

import java.sql.Date;

public interface OVChipkaartDAO {
	
	public void listOv();
	public OVchipkaart findOvById(OVchipkaart chip);
	public Integer addOv(OVchipkaart chip);
	public void updateOv(OVchipkaart updatedOv);
	public void deleteOv(OVchipkaart chip);
	
}

