/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Fos_user;
import com.mycompany.Service.ServiceAmis;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Affichageamis0 {

    private Form f0;

    public Form getF0() {
        return f0;
    }

    public Affichageamis0() {

        f0 = new Form("Nouveaux Likes", BoxLayout.y());
        ServiceAmis sa = new ServiceAmis();
        ArrayList<Fos_user> list = new ArrayList<Fos_user>(sa.getlistamis0());
        if (list.size() == 0) {
            Container cvide = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(120, 120), true);
            String url1 = "http://localhost/MysoulmateWebessai/web/images/emptylike.png";
            Image foreveralone = URLImage.createToStorage(placeholder1, "/images/emptylike.png", url1);
            cvide.addComponent(BorderLayout.CENTER, new ImageViewer(foreveralone));
            Container cmem = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
            cmem.addComponent(BorderLayout.CENTER, new Label("Pas d'invitations"));
            f0.add(cvide);
            f0.add(cmem);

        } else {

            for (Fos_user fu : list) {
                EncodedImage placeholder2 = EncodedImage.createFromImage(Image.createImage(110, 110), true);
                String url2 = "http://localhost/MysoulmateWebessai/web/images/" + fu.getPhoto_de_profil();
                Image icon = URLImage.createToStorage(placeholder2, "/images/" + fu.getPhoto_de_profil(), url2);
                Container c1 = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                c1.add(BorderLayout.CENTER, new ImageViewer(icon));
                Container c2 = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                c2.add(BorderLayout.CENTER, new Label(fu.getNom() + " " + fu.getPrenom()));
                Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Button Like = new Button("Like");
                Button Dislike = new Button("Dislike");
                Like.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        sa.like(fu.getId());
                        f0.removeComponent(c1);
                        f0.removeComponent(c2);
                        f0.removeComponent(c3);
                        if (sa.getlistamis0().size() == 0) {
                            Container cvide = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(120, 120), true);
                            String url1 = "http://localhost/MysoulmateWebessai/web/images/emptylike.png";
                            Image foreveralone = URLImage.createToStorage(placeholder1, "/images/emptylike.png", url1);
                            cvide.addComponent(BorderLayout.CENTER, new ImageViewer(foreveralone));
                            Container cmem = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            cmem.addComponent(BorderLayout.CENTER, new Label("Pas d'invitations"));
                            f0.add(cvide);
                            f0.add(cmem);

                        }
                    }
                });
                Dislike.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        sa.dislike(fu.getId());
                        f0.removeComponent(c1);
                        f0.removeComponent(c2);
                        f0.removeComponent(c3);
                        if (sa.getlistamis0().size() == 0) {
                            Container cvide = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(120, 120), true);
                            String url1 = "http://localhost/MysoulmateWebessai/web/images/emptylike.png";
                            Image foreveralone = URLImage.createToStorage(placeholder1, "/images/emptylike.png", url1);
                            cvide.addComponent(BorderLayout.CENTER, new ImageViewer(foreveralone));
                            Container cmem = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            cmem.addComponent(BorderLayout.CENTER, new Label("Pas d'invitations"));
                            f0.add(cvide);
                            f0.add(cmem);

                        }

                    }
                });
                c3.add(Like);
                c3.add(Dislike);
                f0.add(c1);
                f0.add(c2);
                f0.add(c3);
            }
        }
    }

}
