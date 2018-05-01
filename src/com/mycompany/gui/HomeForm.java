/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
    TextField tnom;
    TextField tetat;
    Button btnajout, btnaff, btnamis, btnrdv0, btnrdv1,btnamis0;

    public HomeForm() {
        f = new Form("home");

        btnaff = new Button("Affichage");
        btnamis = new Button("amis");
        btnamis0 = new Button("amis0");
        btnrdv0 = new Button("rdv0");
        btnrdv1 = new Button("rdv1");

        f.add(btnaff);
        f.add(btnamis);
        f.add(btnamis0);
        f.add(btnrdv0);
        f.add(btnrdv1);

        btnamis.addActionListener((e) -> {
            Affichageamis sa = new Affichageamis();
            sa.getF().show();
        });
        
        btnamis0.addActionListener((e) -> {
            Affichageamis0 sa = new Affichageamis0();
            sa.getF0().show();
        });

        btnaff.addActionListener((e) -> {
            Affichage a = new Affichage();
            a.getF().show();
        });

        btnrdv0.addActionListener((e) -> {
            Affichagerdv0 a = new Affichagerdv0();
            a.getFrdv0().show();
        });
        
         btnrdv1.addActionListener((e) -> {
            Affichagerdv1 a = new Affichagerdv1();
            a.getFrdv1().show();
        });
         
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

}
