package hibernate;

import java.sql.Date;
import java.util.List;

public interface OVChipkaartDAO {
	
	public String listOv();
	public String findOvById(OVchipkaart chip);
	public Integer addOv(OVchipkaart chip);
	public void updateOv(OVchipkaart updatedOv);
	public void deleteOv(OVchipkaart chip);
	
}

