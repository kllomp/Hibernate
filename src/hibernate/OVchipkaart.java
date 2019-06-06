package hibernate;

import java.sql.Date;

public class OVchipkaart {
    private int kaartnummer;
    private double saldo;
    private int klasse;
    private Date geldig;
    private int reizigerid;
    private Reiziger eigenaar;

    public OVchipkaart(int id, double sal, int kl, Date gel, Reiziger reiziger) {
        kaartnummer = id;
        saldo = sal;
        klasse = kl;
        geldig = gel;
        eigenaar = reiziger;
    }

    public OVchipkaart(int id, double sal, int kl, Date gel, int reizigerid) {
        this.kaartnummer = id;
        this.saldo = sal;
        this.klasse = kl;
        this.geldig = gel;
        this.reizigerid = reizigerid;
    }

    public OVchipkaart(int id, double sal, int kl, Date gel) {
        this.kaartnummer = id;
        this.saldo = sal;
        this.klasse = kl;
        this.geldig = gel;
    }

    public OVchipkaart() {
    }


    public int getReizigerid() {
        return reizigerid;
    }

    public void setReizigerid(int reizigerid) {
        this.reizigerid = reizigerid;
    }

    public int getKaartnummer() {
        return kaartnummer;
    }

    public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public Reiziger getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(Reiziger reiziger) {
        eigenaar = reiziger;
    }

    public Date getGeldig() {
        return geldig;
    }

    public void setGeldig(Date geldig) {
        this.geldig = geldig;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double sal) {
        saldo = sal;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int kl) {
        klasse = kl;
    }

    public String toString(){
        return "De huidige pas met kaartnummer " + getKaartnummer() +  " is geldig tot " + getGeldig() +  " en heeft de volgende klasse "
                + getKlasse() +  " met de huidige saldo " + getSaldo() + "\n";
    }

    public String toString2() {
        return "De huidige pas met kaartnummer " + getKaartnummer() +  " is geldig tot " + getGeldig() +  " en heeft de volgende klasse "
                + getKlasse() +  " met de huidige saldo " + getSaldo() +  " en de eigenaar is " + eigenaar.getNaam() + "\n";
    }
}
