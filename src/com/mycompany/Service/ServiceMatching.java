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
import com.mycompany.Entite.Matching;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fatma
 */
public class ServiceMatching {
    
    public ArrayList<Matching> getList2() {
        ArrayList<Matching> listMatchings = new ArrayList<>();
        ArrayList<Matching> listUsers = new ArrayList<>();
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/afficherMatchs/61");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listMatchings = getListMatching(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> matchings = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("Matchings: " + matchings);
                    //System.out.println(matchings);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchings.get("root");
                    for (Map<String, Object> obj : list) {
                        Matching matching = new Matching();
                        /*float id1 = Float.parseFloat(obj.get("0").get("idUser1").toString());
                        float id2 = Float.parseFloat(obj.get("0").toString());
                        float pourcentage = Float.parseFloat(obj.get("0").toString());
                        matching.setIdUser1((int) id1);
                        matching.setIdUser2((int) id2);
                        matching.setPourcentage((int) pourcentage);
                        listMatchings.add(matching);*/
                        
                        LinkedHashMap<String, List<String>> hMap = (LinkedHashMap<String, List<String>>) obj.get("0");
                        List<List<String>> l = new ArrayList<List<String>>(hMap.values());
                        System.out.println("Pourcentage= " + l.get(2));
                        
                    }
                } catch (IOException ex) {
                }
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMatchings;
    }
}
