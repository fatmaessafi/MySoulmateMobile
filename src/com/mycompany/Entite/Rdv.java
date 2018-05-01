/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;



/**
 *
 * @author asus
 */
public class Rdv {

    public Rdv() {
    }
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setId_lieu(int id_lieu) {
        this.id_lieu = id_lieu;
    }

    public int getId() {
        return id;
    }

    public int getId_lieu() {
        return id_lieu;
    }
    private int id1;
    private int id2;
    private String date;
    private int id_lieu;
    private int etat=0;
    

    public Rdv(int id1, int id2, String date, int id_lieu) {
        this.id1 = id1;
        this.id2 = id2;
        this.date = date;
        this.id_lieu = id_lieu;
    }

   

  
    public void setId1(int id1) {
        this.id1 = id1;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public void setDate(String date,int hh,int mm) {
        this.date =date+" "+hh+":"+mm+":23.0";
    }
    public void setDate2(String date) {
        this.date =date;
    }

    public void setLieu(int lieu) {
        this.id_lieu = lieu;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    public String getDate() {
        return date;
    }

    public int getLieu() {
        return id_lieu;
    }

    public int getEtat() {
        return etat;
    }

    @Override
    public String toString() {
        return "Rdv{" + "id1=" + id1 + ", id2=" + id2 + ", date=" + date + ", lieu=" + id_lieu + ", etat=" + etat + '}';
    }
    
    
}
