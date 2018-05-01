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


public class Place {

    public Place() {
    }
 
    private int id_place;
    private String libelle;
    private String adr;
    private String photo;
    private String type;

    public Place(int id_place, String libelle, String adr, String photo, String type) {
        this.id_place = id_place;
        this.libelle = libelle;
        this.adr = adr;
        this.photo = photo;
        this.type = type;
    }

    public Place(String libelle, String adr, String photo, String type) {
        this.libelle = libelle;
        this.adr = adr;
        this.photo = photo;
        this.type = type;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_place() {
        return id_place;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getAdr() {
        return adr;
    }

    public String getPhoto() {
        return photo;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return libelle+","+adr;
    }
    
    
}
