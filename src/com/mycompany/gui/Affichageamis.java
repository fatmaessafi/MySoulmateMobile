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
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Fos_user;
import com.mycompany.Service.ServiceAmis;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author asus
 */
public class Affichageamis {

    private Form f;
    private Resources theme;
    private SpanLabel lb;
    

    public Affichageamis() {
        
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Mes Amis", BoxLayout.y());
        
        ServiceAmis sa = new ServiceAmis();
        ArrayList<Fos_user> list = new ArrayList<Fos_user>();
        list = sa.getlistamis();
        
        for (int i = 0; i < list.size(); i++) {
            
            Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        lb = new SpanLabel(list.get(i).getNom()+" "+list.get(i).getPrenom());
        c.add(lb);
        c.addComponent(new ImageViewer(theme.getImage(list.get(i).getPhoto_de_profil())));
        f.add(c);
        }
      
    }

    public Form getF() {
        return f;
    }

}
