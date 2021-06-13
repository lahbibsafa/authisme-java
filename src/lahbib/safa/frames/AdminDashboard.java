package lahbib.safa.frames;

import keeptoo.KGradientPanel;
import lahbib.safa.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame implements ActionListener {
    JPanel sidePanel;
    public AdminDashboard(){
        this.setTitle("Welcome Mr le responsable");
        this.setLayout(new FlowLayout());
        this.initComponents();
        this.pack();
    }
    public void initComponents(){
        NavigationAdmin navigation = new NavigationAdmin(this);
        sidePanel = new JPanel();

        sidePanel.add(new HomePanel());
        KGradientPanel p = new KGradientPanel();
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        p.add(navigation);
        p.add(sidePanel);

        this.add(p);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = ((JButton) actionEvent.getSource()).getText();
        switch (action) {
            case "Home":{
                this.sidePanel.removeAll();
                this.sidePanel.add(new HomePanel());
                this.pack();
                break;
            }
            case "Employees": {
                this.sidePanel.removeAll();
                this.sidePanel.add(new AddEmployeePanel());
                this.pack();
                break;
            }
            case "Enfants": {
                this.sidePanel.removeAll();
                this.sidePanel.add(new AddEnfantPanel());
                this.pack();
                break;
            }
            case "Activites": {
                this.sidePanel.removeAll();
                this.sidePanel.add(new AddActivityPanel());
                this.pack();
                break;
            }
            case "Groupes": {
                this.sidePanel.removeAll();
                this.sidePanel.add(new AddGroupePanel());
                this.pack();
                break;
            }
            case "Planifier": {
                this.sidePanel.removeAll();
                this.sidePanel.add(new AddPlanificationPanel());
                this.pack();
                break;
            }
            case "Suivie": {
                this.sidePanel.removeAll();
                this.sidePanel.add(new SuivieResponsable());
                this.pack();
                break;
            }
            case "Deconnexion": {
                this.sidePanel.removeAll();
                this.hide();
                (new Login()).show();
                break;
            }

        }
    }
}
