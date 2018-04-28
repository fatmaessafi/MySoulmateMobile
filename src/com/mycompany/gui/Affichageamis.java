/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Fos_user;
import com.mycompany.Entite.Message;
import com.mycompany.Service.ServiceAmis;
import com.mycompany.Service.ServiceMsg;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Affichageamis {

    private Form f, f2;
    private Resources theme;
    private SpanLabel lb;

    public Affichageamis() {

        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Mes Amis", BoxLayout.y());

        ServiceAmis sa = new ServiceAmis();
        final ArrayList<Fos_user> list = new ArrayList<Fos_user>(sa.getlistamis());

        for (int i = 0; i < list.size(); i++) {

            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(70, 70), true);
            String url = "http://localhost/MysoulmateWebessai/web/images/" + list.get(i).getPhoto_de_profil().toString();
            Image imgHome = URLImage.createToStorage(placeholder, "uploads/images/" + list.get(i).getPhoto_de_profil().toString(), url);
            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            ImageViewer im = new ImageViewer(imgHome);
            final int x = i;

            im.addPointerReleasedListener(new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent evt) {
                    f2 = new Form(list.get(x).getPrenom(),BoxLayout.y());
                    Toolbar tb = f2.getToolbar();
                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
                        f.showBack();
                    });

                    ServiceMsg sm = new ServiceMsg();
                    ArrayList<Message> listmsg = new ArrayList<Message>();
                    listmsg = sm.getmsg(list.get(x).getId());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(70, 70), true);
                    String url = "http://localhost/MysoulmateWebessai/web/images/" + list.get(x).getPhoto_de_profil().toString();
                    Image imgHome = URLImage.createToStorage(placeholder, "uploads/images/" + list.get(x).getPhoto_de_profil().toString(), url);
                    ImageViewer pdp = new ImageViewer(imgHome);
                    Container photo=new Container();
                    photo.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                    photo.add(BorderLayout.CENTER,pdp);
                    f2.add(photo);
                    for (int j = 0; j < listmsg.size() - 1; j++) {
                        if (listmsg.get(j).getId_emet() != MyApplication.currentid) {
                            Container line1 = new Container();
                            Label lb = new Label();
                            lb.setText("               " + listmsg.get(j).getText());
                            lb.getAllStyles().setFgColor(0xff000);
                            line1.addComponent(lb);
                            f2.add(line1);
                        } else {
                            Container line1 = new Container();
                            line1.add(new Label(listmsg.get(j).getText()));
                            f2.add(line1);
                        }

                    }
                    ////////////////////////////////////////////////////////
                    Container line = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    if (listmsg.size() != 0) {
                        if (listmsg.get(listmsg.size() - 1).getId_emet() != MyApplication.currentid) {
                            Label lb = new Label();
                            lb.setText("               " + listmsg.get(listmsg.size() - 1).getText());
                            lb.getAllStyles().setFgColor(0xff000);
                            line.addComponent(lb);
                            f2.add(line);
                        } else {
                            line.add(new Label(listmsg.get(listmsg.size() - 1).getText()));
                            f2.add(line);
                        }
                    } else {
                        f2.add(line);
                    }
                    /////////////////////////////////////////////////////////////

                    TextField msgtext = new TextField();
                    Button envoyer = new Button("Envoyer");
                    envoyer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                            sm.envoyermsg(list.get(x).getId(), msgtext.getText());
                            line.add(new Label(msgtext.getText()));
                            msgtext.setText("");
                        }
                    });
                    f2.addComponent(msgtext);
                    f2.addComponent(envoyer);
                    f2.show();

                }
            });

            //////////////////////////////////////////////////////////////////////
            c.addComponent(im);
            lb = new SpanLabel(list.get(i).getNom() + " " + list.get(i).getPrenom());
            c.addComponent(lb);
            f.add(c);

        }

    }

    public Form getF() {
        return f;
    }

}
