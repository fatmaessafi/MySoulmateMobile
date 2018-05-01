/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Message;
import com.mycompany.Entite.Place;
import com.mycompany.Entite.Rdv;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceRdv {

    public List<Place> getlieux() {
        List<Place> lieux = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/getlieux");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {

                    Map<String, Object> matchings = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchings.get("root");
                    for (Map<String, Object> obj : list) {
                        Place p = new Place();
                        p.setId_place(Integer.parseInt(obj.get("id_place").toString()));
                        p.setLibelle(obj.get("libelle").toString());
                        p.setAdr(obj.get("adr").toString());
                        p.setPhoto(obj.get("photo").toString());
                        p.setType(obj.get("type").toString());
                        lieux.add(p);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lieux;

    }

    public void insert(int id1, int id2, String date, int idlieu) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/Ajouterevent/" + id1 + "/" + id2 + "/" + date + "/" + idlieu);
        NetworkManager.getInstance().addToQueue(con);
    }

    public ArrayList<Rdv> getallrdv() {
        ArrayList<Rdv> rdvs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/Afficherevent/" + MyApplication.currentid);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {

                    Map<String, Object> matchings = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchings.get("root");
                    for (Map<String, Object> obj : list) {
                        Rdv r = new Rdv();
                        r.setId1(Integer.parseInt(obj.get("id_1").toString()));
                        r.setId2(Integer.parseInt(obj.get("id_2").toString()));
                        r.setId(Integer.parseInt(obj.get("id").toString()));
                        r.setId_lieu(Integer.parseInt(obj.get("id_lieu").toString()));
                        r.setEtat(Integer.parseInt(obj.get("etat").toString()));
                        r.setDate2(obj.get("date").toString());
                        rdvs.add(r);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return rdvs;

    }

    
    public ArrayList<Rdv> getallrdv1() {
        ArrayList<Rdv> rdvs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/Afficherevent1/" + MyApplication.currentid);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {

                    Map<String, Object> matchings = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchings.get("root");
                    for (Map<String, Object> obj : list) {
                        Rdv r = new Rdv();
                        r.setId1(Integer.parseInt(obj.get("id_1").toString()));
                        r.setId2(Integer.parseInt(obj.get("id_2").toString()));
                        r.setId(Integer.parseInt(obj.get("id").toString()));
                        r.setId_lieu(Integer.parseInt(obj.get("id_lieu").toString()));
                        r.setEtat(Integer.parseInt(obj.get("etat").toString()));
                        r.setDate2(obj.get("date").toString());
                        rdvs.add(r);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return rdvs;

    }

    
    public Place getlieubyid(int id) {

        Place p = new Place();

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/getlieubyid/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {

                    Map<String, Object> matchings = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchings.get("root");
                    for (Map<String, Object> obj : list) {
                        p.setId_place(Integer.parseInt(obj.get("id_place").toString()));
                        p.setLibelle(obj.get("libelle").toString());
                        p.setAdr(obj.get("adr").toString());
                        p.setPhoto(obj.get("photo").toString());
                        p.setType(obj.get("type").toString());
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return p;

    }

    public void confirmerrdv(int id_1, int id_2, String date) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/Confirmerevent/"+id_1+"/"+id_2+"/"+date);
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
     public void supprimerrrdv(int id_1, int id_2, String date) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/effacerevent/"+id_1+"/"+id_2+"/"+date);
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

}
