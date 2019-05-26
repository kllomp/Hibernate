package hu.nl.hibernate;

import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Main {
  private static SessionFactory factory;
  public static void main(String[] args) {
      try {
          factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) {
          System.err.println("Failed to create sessionFactory object." + ex);
          throw new ExceptionInInitializerError(ex);
      }

      Main main = new Main();

      main.listReizigers();

      main.addReiziger(11, "test11", "test11", "test11", Date.valueOf("1999-01-19"));

      main.updateReiziger(11, "test12", "test12", "test12", Date.valueOf("1999-01-12"));

      main.deleteReiziger(11);

      factory.close();

  }
      /* Method to CREATE an reiziger in the database */
      public void addReiziger(int rId, String vNaam, String tussen, String aNaam, Date GBdatum) {
          Session session = factory.openSession();
          Transaction tx = null;
          try {
              tx = session.beginTransaction();
              Reiziger reiziger = new Reiziger(rId, vNaam, tussen, aNaam, GBdatum);
              session.save(reiziger);
              tx.commit();
              System.out.println("\nGeupdate lijst na toevoeging\n");
              listReizigers();
          } catch (HibernateException e) {
              if (tx != null) tx.rollback();
              e.printStackTrace();
          } finally {
              session.close();
          }
      }

      /* Method to  READ all the reizigers */
      public void listReizigers( ){
          Session session = factory.openSession();
          Transaction tx = null;

          try {
              tx = session.beginTransaction();
              List reizigers = session.createQuery("FROM hu.nl.hibernate.Reiziger").list();
              for (Iterator iterator = reizigers.iterator(); iterator.hasNext();){
                  Reiziger reiziger = (Reiziger) iterator.next();
                  System.out.print("Reiziger ID: " + reiziger.getReizigerID());
                  System.out.print(" Volledige naam: " + reiziger.getNaam());
                  System.out.println("  Gebortedatum: " + reiziger.getGbdatum());
              }
              tx.commit();
          } catch (HibernateException e) {
              if (tx != null) tx.rollback();
              e.printStackTrace();
          } finally {
              session.close();
          }
      }

    /* Method to UPDATE salary for an reiziger */
    public void updateReiziger(int rId, String vNaam, String tussen, String aNaam, Date GBdatum){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Reiziger reiziger = (Reiziger) session.get(Reiziger.class, rId);
            reiziger.setVoorletters( vNaam );
            reiziger.setTussenvoegsel( tussen );
            reiziger.setAchternaam( aNaam );
            reiziger.setGbdatum( GBdatum );
            session.update(reiziger);
            tx.commit();
            System.out.println("\nGeupdate lijst na updaten\n");
            listReizigers();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public void deleteReiziger(Integer rID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Reiziger reiziger = (Reiziger)session.get(Reiziger.class, rID);
            session.delete(reiziger);
            tx.commit();
            System.out.println("\nGeupdate lijst na verwijderen\n");
            listReizigers();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
  }
