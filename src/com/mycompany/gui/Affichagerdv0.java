/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Fos_user;
import com.mycompany.Entite.Rdv;
import com.mycompany.Service.ServiceAmis;
import com.mycompany.Service.ServiceRdv;
import com.mycompany.myapp.MyApplication;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Affichagerdv0 {

    Form frdv0;
    ServiceRdv sr;
    ServiceAmis sa;

    public Affichagerdv0() {
        frdv0 = new Form("liste des demandes", new BoxLayout(BoxLayout.Y_AXIS));
        sr = new ServiceRdv();
        sa = new ServiceAmis();
        ArrayList<Rdv> list = new ArrayList<>(sr.getallrdv());
        if (list.size() == 0) {
            Container cvide = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(120, 120), true);
            String url1 = "http://localhost/MysoulmateWebessai/web/images/alone.png";
            Image foreveralone = URLImage.createToStorage(placeholder1, "/images/alone.png", url1);
            cvide.addComponent(BorderLayout.CENTER, new ImageViewer(foreveralone));
            Container cmem = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
            cmem.addComponent(BorderLayout.CENTER, new Label("Forever Alone"));
            frdv0.add(cvide);
            frdv0.add(cmem);

        } else {

            for (Rdv rdv : list) {

                Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Fos_user fu = new Fos_user();
                fu = sa.getuserbyid(rdv.getId1());
                System.out.println(rdv.getId1());
                EncodedImage placeholder2 = EncodedImage.createFromImage(Image.createImage(60, 60), true);
                String url2 = "http://localhost/MysoulmateWebessai/web/images/" + fu.getPhoto_de_profil();
                Image icon = URLImage.createToStorage(placeholder2, "/images/" + fu.getPhoto_de_profil(), url2);
                c.addComponent(new ImageViewer(icon));
                c.add(new Label(fu.getNom() + " " + fu.getPrenom()));

                Container c4 = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                c4.add(BorderLayout.CENTER, new Label(rdv.getDate()));
                Container c5 = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                c5.add(BorderLayout.CENTER, new Label(sr.getlieubyid(rdv.getId_lieu()).getLibelle() + "," + sr.getlieubyid(rdv.getId_lieu()).getAdr()));

                Container c2 = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(100, 100), true);
                String url1 = "http://localhost/MysoulmateWebessai/web/images/" + sr.getlieubyid(rdv.getId_lieu()).getPhoto();
                Image icon1 = URLImage.createToStorage(placeholder1, "/images/" + sr.getlieubyid(rdv.getId_lieu()).getPhoto(), url1);
                c2.addComponent(BorderLayout.CENTER, new ImageViewer(icon1));

                Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Button accepter = new Button("accepter");
                Button refuser = new Button("refuser");
                refuser.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        sr.supprimerrrdv(rdv.getId1(), rdv.getId2(), rdv.getDate());
                        frdv0.removeComponent(c);
                        frdv0.removeComponent(c2);
                        frdv0.removeComponent(c5);
                        frdv0.removeComponent(c4);
                        frdv0.removeComponent(c3);
                        if (sr.getallrdv().size() == 0) {
                            Container cvide = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(120, 120), true);
                            String url1 = "http://localhost/MysoulmateWebessai/web/images/alone.png";
                            Image foreveralone = URLImage.createToStorage(placeholder1, "/images/alone.png", url1);
                            cvide.addComponent(BorderLayout.CENTER, new ImageViewer(foreveralone));
                            Container cmem = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            cmem.addComponent(BorderLayout.CENTER, new Label("Forever Alone"));
                            frdv0.add(cvide);
                            frdv0.add(cmem);

                        }
                    }
                });
                accepter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        sr.confirmerrdv(rdv.getId1(), rdv.getId2(), rdv.getDate());
                        frdv0.removeComponent(c);
                        frdv0.removeComponent(c2);
                        frdv0.removeComponent(c5);
                        frdv0.removeComponent(c4);
                        frdv0.removeComponent(c3);
                        if (sr.getallrdv().size() == 0) {
                            Container cvide = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(120, 120), true);
                            String url1 = "http://localhost/MysoulmateWebessai/web/images/alone.png";
                            Image foreveralone = URLImage.createToStorage(placeholder1, "/images/alone.png", url1);
                            cvide.addComponent(BorderLayout.CENTER, new ImageViewer(foreveralone));
                            Container cmem = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            cmem.addComponent(BorderLayout.CENTER, new Label("Forever Alone"));
                            frdv0.add(cvide);
                            frdv0.add(cmem);

                        }

                    }
                });
                c3.add(accepter);
                c3.add(refuser);

                frdv0.add(c);
                frdv0.add(c2);
                frdv0.add(c5);
                frdv0.add(c4);
                frdv0.add(c3);
            }

            System.out.println(sr.getallrdv());
        }

    }

    public Form getFrdv0() {
        return frdv0;
    }

}
