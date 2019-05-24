package hu.nl.hibernate;

import java.sql.Date;
import java.util.List;

public class Reiziger {
    private int reizigerID;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date gbdatum;


    public Reiziger(int rID, String vl, String tv, String an, Date datum) {
        reizigerID = rID;
        voorletters = vl;
        tussenvoegsel = tv;
        achternaam = an;
        gbdatum = datum;
    }

    public Reiziger(int rID){
        reizigerID = rID;
    }

    public int getReizigerID() {
        return reizigerID;
    }

    public void setReizigerID(int reizigerID) {
        this.reizigerID = reizigerID;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getNaam(){
        if (tussenvoegsel != null){
            return getVoorletters() + " " + getTussenvoegsel() + " " + getAchternaam();
        } else {
            return getVoorletters() + " " + getAchternaam();
        }
    }

    public Date getGbdatum() {
        return gbdatum;
    }

    public void setGbdatum(Date gbdatum){
        this.gbdatum = gbdatum;
    }
}
