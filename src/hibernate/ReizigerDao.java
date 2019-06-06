package hibernate;

import java.sql.Date;

public interface ReizigerDao {
	
	 public void listReizigers();
	 public Reiziger findReizigerById(Reiziger reiziger);
	 public Integer addReiziger(Reiziger r);
	 public void updateReiziger(Reiziger updatedReiziger);
	 public void deleteReiziger(Reiziger r);

}