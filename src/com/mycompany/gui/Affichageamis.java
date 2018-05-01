/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Fos_user;
import com.mycompany.Entite.Message;
import com.mycompany.Service.ServiceAmis;
import com.mycompany.Service.ServiceMsg;
import com.mycompany.myapp.MyApplication;
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
                    f2 = new Form(list.get(x).getPrenom(), BoxLayout.y());
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
                    Container photo = new Container();
                    photo.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                    photo.add(BorderLayout.CENTER, pdp);
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
                    msgtext.setRows(2);
                    msgtext.setColumns(50);
                    Button envoyer = new Button("Envoyer");
                    envoyer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (msgtext.getText().compareTo("") == 0) {
                                Dialog dlg = new Dialog("Erreur");
                                Style dlgStyle = dlg.getDialogStyle();
                                dlgStyle.setBorder(Border.createEmpty());
                                dlgStyle.setBgTransparency(255);
                                dlgStyle.setBgColor(0xffffff);
                                Label title = dlg.getTitleComponent();
                                title.getUnselectedStyle().setFgColor(0xff0000);
                                title.getUnselectedStyle().setAlignment(Component.LEFT);
                                dlg.setLayout(BoxLayout.y());
                                Label blueLabel = new Label();
                                blueLabel.setShowEvenIfBlank(true);
                                blueLabel.getUnselectedStyle().setBgColor(0xff000);
                                blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                                blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                                dlg.add(blueLabel);
                                TextArea ta = new TextArea("Veillez ecrire quelque chose pour l'envoyer");
                                ta.setEditable(false);
                                ta.setUIID("DialogBody");
                                ta.getAllStyles().setFgColor(0);
                                dlg.add(ta);
                                Label grayLabel = new Label();
                                grayLabel.setShowEvenIfBlank(true);
                                grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
                                grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                                grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                                dlg.add(grayLabel);
                                Button ok = new Button(new Command("OK"));
                                ok.getAllStyles().setBorder(Border.createEmpty());
                                ok.getAllStyles().setFgColor(0);
                                dlg.add(ok);
                                dlg.showDialog();
                            } else {
                                sm.envoyermsg(list.get(x).getId(), msgtext.getText());
                                line.add(new Label(msgtext.getText()));
                                msgtext.setText("");

                            }
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
            EncodedImage placeholder2 = EncodedImage.createFromImage(Image.createImage(70, 70), true);
            String url2 = "http://localhost/MysoulmateWebessai/web/images/dating.png";
            Image icon = URLImage.createToStorage(placeholder2, "/images/dating.png", url2);
            ImageViewer rdv = new ImageViewer(icon);
            rdv.addPointerReleasedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    addrdv ar = new addrdv(list.get(x));
                    Toolbar tb = ar.getFrdv().getToolbar();
                    tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
                        f.showBack();
                    });

                    ar.getFrdv().show();
                }
            });
            c.add(new Label("   "));
            c.add(rdv);
            f.add(c);

        }

    }

    public Form getF() {
        return f;
    }

}
