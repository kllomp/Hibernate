package hibernate;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static SessionFactory factory;

    public static void main(String[] args) throws SQLException, ParseException {
        try {
            setFactory(new Configuration().configure().buildSessionFactory());
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        OVChipkaartDAO OVDAO = new OVChipkaartDAOImpl();
        ReizigerDao DAO = new ReizigerDaoImpl();


        Reiziger newReiziger = new Reiziger(11, "N", "", "Klomp", Date.valueOf("1999-01-19"));

        OVchipkaart newOv = new OVchipkaart(56789, 13.80, 1, Date.valueOf("2023-05-1"), 3);

        System.out.println("\nLijst van OV Chipkaarten\n");
        System.out.println(OVDAO.listOv());

        System.out.println("\nToevoeging van OV Chipkaart\n");
        OVDAO.addOv(newOv);
        System.out.println(OVDAO.findOvById(newOv));

        System.out.println("\nUpdate OV Chipkaart\n");
        newOv.setSaldo(50);
        OVDAO.updateOv(newOv);
        System.out.println(OVDAO.findOvById(newOv));

        System.out.println("\nVerwijder OV Chipkaart\n");
        OVDAO.deleteOv(newOv);
        System.out.println(OVDAO.listOv());

        System.out.println("\nLijst van Reizigers\n");
        System.out.println(DAO.listReizigers());

        System.out.println("\nToevoeging van Reiziger\n");
        DAO.addReiziger(newReiziger);
        System.out.println(DAO.findReizigerById(newReiziger));

        System.out.println("\nUpdate van Reiziger\n");
        newReiziger.setAchternaam("Jovanovic");
        DAO.updateReiziger(newReiziger);
        System.out.println(DAO.findReizigerById(newReiziger));

        System.out.println("\nDelete van Reiziger\n");
        DAO.deleteReiziger(newReiziger);
        System.out.println(DAO.listReizigers());

        factory.close();
    }

    public static SessionFactory getFactory() {
        return factory;
    }

    public static void setFactory(SessionFactory factory) {
        Main.factory = factory;
    }
}
