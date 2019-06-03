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

        Reiziger updatedReiziger = new Reiziger(11, "S", "", "Jovanovic", Date.valueOf("1998-02-14"));
        OVchipkaart updatedOv = new OVchipkaart(56789, 35.30, 2, Date.valueOf("2025-01-19"), 3);

        System.out.println("\nLijst van OV Chipkaarten\n");
        OVDAO.listOv();

        System.out.println("\nToevoeging van OV Chipkaart\n");
        OVDAO.addOv(56789, 13.80, 1, Date.valueOf("2023-05-1"), 3);
        OVDAO.findOvById(56789);

        System.out.println("\nUpdate OV Chipkaart\n");
        OVDAO.updateOv(56789, updatedOv);
        OVDAO.findOvById(56789);

        System.out.println("\nVerwijder OV Chipkaart\n");
        OVDAO.deleteOv(56789);
        OVDAO.listOv();

        System.out.println("\nLijst van Reizigers\n");
        DAO.listReizigers();

        System.out.println("\nToevoeging van Reiziger\n");
        DAO.addReiziger(11, "N", "", "Klomp", Date.valueOf("1999-01-19"));
        DAO.findReizigerById(11);

        System.out.println("\nUpdate van Reiziger\n");
        DAO.updateReiziger(11, updatedReiziger);
        DAO.findReizigerById(11);

        System.out.println("\nDelete van Reiziger\n");
        DAO.deleteReiziger(11);
        DAO.listReizigers();

        factory.close();
    }

    public static SessionFactory getFactory() {
        return factory;
    }

    public static void setFactory(SessionFactory factory) {
        Main.factory = factory;
    }
}
