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
import com.mycompany.Entite.Fos_user;
import com.mycompany.Entite.Matching;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceAmis {

    public ArrayList<Fos_user> getlistamis() {
        ArrayList<Fos_user> listUsers = new ArrayList<>();

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/AfficherAmis/" + MyApplication.currentid);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listMatchings = getListMatching(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {

                    Map<String, Object> matchings = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchings.get("root");
                    for (Map<String, Object> obj : list) {
                        Fos_user fu = new Fos_user();
                        fu.setId(Integer.parseInt(obj.get("id").toString()));
                        fu.setNom(obj.get("nom").toString());
                        fu.setPrenom(obj.get("prenom").toString());
                        fu.setAge(Integer.parseInt(obj.get("age").toString()));
                        fu.setPhoto_de_profil(obj.get("photo_de_profil").toString());
                        listUsers.add(fu);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUsers;
    }

    public Fos_user getuserbyid(int id) {
        
        Fos_user fu = new Fos_user();

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/getuserbyid/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listMatchings = getListMatching(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> matchings = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchings.get("root");
                    for (Map<String, Object> obj : list) {
                        fu.setId(Integer.parseInt(obj.get("id").toString()));
                        fu.setNom(obj.get("nom").toString());
                        fu.setPrenom(obj.get("prenom").toString());
                        fu.setAge(Integer.parseInt(obj.get("age").toString()));
                        fu.setPhoto_de_profil(obj.get("photo_de_profil").toString());
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return fu;
    }
}
