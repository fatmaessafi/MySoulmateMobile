/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.mycompany.Entite.Fos_user;
import com.mycompany.Entite.Place;
import com.mycompany.Service.ServiceRdv;
import com.mycompany.myapp.MyApplication;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class addrdv {

    Form frdv;
    Picker daterdv;
    Button Demander;
    SpanLabel nom;
    ImageViewer pdp, imagelieu;
    ComboBox<Place> lieu;

    public addrdv(Fos_user amis) {
        frdv = new Form("Proposer un RDV", new BoxLayout(BoxLayout.Y_AXIS));

        EncodedImage placeholder2 = EncodedImage.createFromImage(Image.createImage(100, 100), true);
        String url2 = "http://localhost/MysoulmateWebessai/web/images/" + amis.getPhoto_de_profil();
        Image icon = URLImage.createToStorage(placeholder2, "/images/" + amis.getPhoto_de_profil(), url2);
        pdp = new ImageViewer(icon);

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(60, 60), true);
        String url = "http://localhost/MysoulmateWebessai/web/images/rdv.png";
        Image icon2 = URLImage.createToStorage(placeholder, "/images/rdv.png", url);
        ImageViewer coeur1;
        coeur1 = new ImageViewer(icon2);
        ImageViewer coeur2;
        coeur2 = new ImageViewer(icon2);

        nom = new SpanLabel();
        nom.setText(amis.getPrenom() + "        ");
        Container info1 = new Container();
        info1.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        info1.add(BorderLayout.CENTER, pdp);
        info1.add(BorderLayout.EAST, coeur1);
        info1.add(BorderLayout.WEST, coeur2);
        Container info2 = new Container();
        info2.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        info2.add(BorderLayout.CENTER, nom);
        frdv.add(info1);
        frdv.add(info2);

        daterdv = new Picker();
        daterdv.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        frdv.add(daterdv);

        lieu = new ComboBox<>();
        imagelieu = new ImageViewer();
        ServiceRdv sr = new ServiceRdv();
        List<Place> lieux = new ArrayList<>(sr.getlieux());
        for (Place place : lieux) {
            lieu.addItem(place);
        }
        lieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EncodedImage placeholder2 = EncodedImage.createFromImage(Image.createImage(120, 120), true);
                String url2 = "http://localhost/MysoulmateWebessai/web/images/" + lieu.getSelectedItem().getPhoto();
                Image icon = URLImage.createToStorage(placeholder2, "/images/" + lieu.getSelectedItem().getPhoto(), url2);
                imagelieu.setImage(icon);
            }
        });
        frdv.add(lieu);
        frdv.add(imagelieu);

        Demander = new Button("Proposer");
        Demander.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(daterdv.getDate()).toString();
                sr.insert(MyApplication.currentid, amis.getId(), date, lieu.getSelectedItem().getId_place());
      //////////////////////////////////////////////////////////////
                Dialog dlg = new Dialog("Succes");
                Style dlgStyle = dlg.getDialogStyle();
                dlgStyle.setBorder(Border.createEmpty());
                dlgStyle.setBgTransparency(255);
                dlgStyle.setBgColor(0xffffff);
                Label title = dlg.getTitleComponent();
                title.getUnselectedStyle().setFgColor(0xff000);
                title.getUnselectedStyle().setAlignment(Component.LEFT);
                dlg.setLayout(BoxLayout.y());
                Label blueLabel = new Label();
                blueLabel.setShowEvenIfBlank(true);
                blueLabel.getUnselectedStyle().setBgColor(0xff000);
                blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                dlg.add(blueLabel);
                TextArea ta = new TextArea("Un Rendez_vous a été proposé a " + amis.getPrenom() + " " + amis.getNom());
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
      //////////////////////////////////////////////////////////////
            }
        });
        frdv.add(Demander);

    }

    public Form getFrdv() {
        return frdv;
    }

}
