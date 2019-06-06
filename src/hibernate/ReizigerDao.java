package hibernate;

import java.sql.Date;
import java.util.List;

public interface ReizigerDao {
	
	 public List<Reiziger> listReizigers();
	public List<Reiziger> findReizigerById(Reiziger reiziger);
	 public Integer addReiziger(Reiziger r);
	 public void updateReiziger(Reiziger updatedReiziger);
	 public void deleteReiziger(Reiziger r);

}