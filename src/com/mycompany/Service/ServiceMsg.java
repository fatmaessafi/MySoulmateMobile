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
import com.mycompany.Entite.Message;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceMsg {

    public ServiceMsg() {
    }
    
    public ArrayList<Message> getmsg(int idamis){
         ArrayList<Message> listmsg = new ArrayList<>();

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/Affichermsg/"+MyApplication.currentid+"/"+idamis);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listMatchings = getListMatching(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {

                    Map<String, Object> matchings = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchings.get("root");
                    for (Map<String, Object> obj : list) {
                        Message msg=new Message();
                        msg.setId_msg(Integer.parseInt(obj.get("id_msg").toString()));
                        msg.setId_emet(Integer.parseInt(obj.get("id_emet").toString()));
                        msg.setId_dest(Integer.parseInt(obj.get("id_dest").toString()));
                        msg.setDuree(Integer.parseInt(obj.get("lu").toString()));
                        msg.setText(obj.get("text").toString());
                        msg.setDate(obj.get("date_envoi").toString());
                        listmsg.add(msg);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listmsg;

    }
    
    
    public void envoyermsg(int idamis,String text){
          ConnectionRequest con = new ConnectionRequest();
          con.setUrl("http://localhost/MysoulmateMobile/web/app_dev.php/Ajoutermsg/"+MyApplication.currentid+"/"+idamis+"/"+text);
          NetworkManager.getInstance().addToQueue(con);

      
    }
    
}
