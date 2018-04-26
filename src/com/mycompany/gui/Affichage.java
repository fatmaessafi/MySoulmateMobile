/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Service.ServiceMatching;
import com.mycompany.Entite.Matching;
import java.util.ArrayList;

/**
 *
 * @author sana
 */
public class Affichage {

    Form f;
    SpanLabel lb;
        private Resources theme;
Form f2;
  
    public Affichage() {
        
                theme = UIManager.initFirstTheme("/theme");
        f = new Form("Mes Matchs",BoxLayout.y());
        lb = new SpanLabel("Mes Matchs");
        f.add(lb);
        ServiceMatching serviceMatching=new ServiceMatching();
        ArrayList<Matching> Matchings=serviceMatching.getList2();
        
        for (Matching t : Matchings) {

        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        Label pourcent= new Label(t.getPourcentage()+"");
        //ImageViewer img = new  ImageViewer(theme.getImage(t.getPourcentage()+""));
        
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Label idUser1 = new Label(t.getIdUser1()+"");
         Label idUser2 = new Label(t.getIdUser2()+"");
        C2.add(idUser1);
        C2.add(idUser2);
        C1.add(pourcent);
        C1.add(C2);
        
        C1.setLeadComponent(pourcent);
        
        f.add(C1);
        
        pourcent.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
            f2 = new Form(BoxLayout.y());
            
            Toolbar tb = f2.getToolbar();
            
            tb.addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e->{
            
            f.showBack();
            
            });
       
               // ImageViewer img = new ImageViewer(theme.getImage(t.getImg()));        
                SpanLabel sp = new SpanLabel(t.getPourcentage()+"");
                
//                f2.add(img);
                f2.add(sp);
                f2.show();
  
            }
        });
          f.getToolbar().addCommandToRightBar("Back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
    }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
