package lahbib.safa.frames;

import keeptoo.KGradientPanel;
import lahbib.safa.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDashboard extends JFrame implements ActionListener {
    JPanel sidePanel;

    public EmployeeDashboard() {
        this.setTitle("Espace Employee");
        this.setLayout(new FlowLayout());
        this.initComponents();
        this.pack();
    }

    public void initComponents() {
        NavigationEmployee navigation = new NavigationEmployee(this);
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
            case "Home": {
                this.sidePanel.removeAll();
                this.sidePanel.add(new HomePanel());
                this.pack();
                break;
            }
            case "Mes Groupes": {
                this.sidePanel.removeAll();
                this.sidePanel.add(new MesGroupesPanel());
                this.pack();
                break;
            }
            case "Mes Activite": {
                this.sidePanel.removeAll();
                this.sidePanel.add(new MesActivite());
                this.pack();
                break;
            }
            case "Enfants": {
                this.sidePanel.removeAll();
                this.sidePanel.add(new MesEnfant());
                this.pack();
                break;
            }
            case "Notes": {
                this.sidePanel.removeAll();
                this.sidePanel.add(new MesNotes());
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
