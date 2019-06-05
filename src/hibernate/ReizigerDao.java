package hibernate;

import java.sql.Date;

public interface ReizigerDao {
	
	 public void listReizigers();
	 public Reiziger findReizigerById(int id);
	 public Integer addReiziger(int id, String vl, String tvoegsel, String anaam, Date gbdatum);
	 public void updateReiziger(int id, Reiziger updatedReiziger);
	 public void deleteReiziger(int id);

}