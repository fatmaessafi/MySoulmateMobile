/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author asus
 */
public class Message {
    
    private int id_msg;
    private int id_emet;
    private int id_dest;
    private String text;
    private String date;
    private int duree;

    public Message() {
    }

    public Message(int id_emet, int id_dest, String text) {
        this.id_emet = id_emet;
        this.id_dest = id_dest;
        this.text = text;
      
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setId_msg(int id_msg) {
        this.id_msg = id_msg;
    }

    public void setId_emet(int id_emet) {
        this.id_emet = id_emet;
    }

    public void setId_dest(int id_dest) {
        this.id_dest = id_dest;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_msg() {
        return id_msg;
    }

    public int getId_emet() {
        return id_emet;
    }

    public int getId_dest() {
        return id_dest;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Message{" + "id_emet=" + id_emet + ", id_dest=" + id_dest + ", text=" + text + ", date=" + date + '}';
    }
    
    
    
}
