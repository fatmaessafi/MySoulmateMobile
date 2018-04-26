/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.Service.ServiceMatching;
import com.mycompany.Entite.Matching;

/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
    TextField tnom;
    TextField tetat;
    Button btnajout,btnaff,btnamis;

    public HomeForm() {
        f = new Form("home");
        
        btnaff=new Button("Affichage");
        btnamis=new Button("amis");
      
        
        f.add(btnaff);
        f.add(btnamis);
        
        btnamis.addActionListener((e)->{
        Affichageamis sa=new Affichageamis();
        sa.getF().show();
        });
       
        btnaff.addActionListener((e)->{
        Affichage a=new Affichage();
        a.getF().show();
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
