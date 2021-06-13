package lahbib.safa.panels;

import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NavigationAdmin extends JPanel {
    ActionListener eventListener ;
    public NavigationAdmin(ActionListener eventListener){
        this.eventListener = eventListener;
        this.setLayout(new BorderLayout());
        this.initComponents();
        this.setOpaque(false);
    }

    public void initComponents(){
        Box box = Box.createVerticalBox();
        box.setOpaque(false);
        JButton btnHome = new JButton("Home");
        btnHome.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnHome.addActionListener(this.eventListener);

        JButton btnGroupes = new JButton("Groupes");
        btnGroupes.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnGroupes.addActionListener(this.eventListener);

        JButton btnActivites = new JButton("Activites");
        btnActivites.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnActivites.addActionListener(this.eventListener);

        JButton btnEnfants = new JButton("Enfants");
        btnEnfants.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEnfants.addActionListener(this.eventListener);


        JButton btnEmployee = new JButton("Employees");
        btnEmployee.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEmployee.addActionListener(this.eventListener);


        JButton btnPlanifier = new JButton("Planifier");
        btnPlanifier.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnPlanifier.addActionListener(this.eventListener);

        JButton btnSuivie = new JButton("Suivie");
        btnSuivie.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnSuivie.addActionListener(this.eventListener);
        JButton btnDex = new JButton("Deconnexion");
        btnDex.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnDex.addActionListener(this.eventListener);

        JPanel boxPanel = new JPanel();
        boxPanel.setOpaque(false);
        box.add(btnHome);
        box.add(btnEmployee);
        box.add(btnGroupes);
        box.add(btnEnfants);
        box.add(btnActivites);
        box.add(btnPlanifier);
        box.add(btnSuivie);
        box.add(btnDex);
        boxPanel.add(box);
        this.add(boxPanel);
    }
}
