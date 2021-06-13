package lahbib.safa.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NavigationEmployee extends JPanel {
    ActionListener eventListener ;
    public NavigationEmployee(ActionListener eventListener){
        this.eventListener = eventListener;
        this.setLayout(new BorderLayout());
        this.initComponents();
        this.setOpaque(false);
    }

    public void initComponents(){
        Box box = Box.createVerticalBox();
        box.setOpaque(false);
        JButton btnHome = new JButton("Mes Groupes");
        btnHome.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnHome.addActionListener(this.eventListener);

        JButton btnGroupes = new JButton("Mes Activite");
        btnGroupes.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnGroupes.addActionListener(this.eventListener);


        JButton btnEnfants = new JButton("Enfants");
        btnEnfants.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEnfants.addActionListener(this.eventListener);


        JButton btnEmployee = new JButton("Employees");
        btnEmployee.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEmployee.addActionListener(this.eventListener);


        JButton btnNotes = new JButton("Notes");
        btnNotes.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnNotes.addActionListener(this.eventListener);


        JButton btnDex = new JButton("Deconnexion");
        btnDex.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnDex.addActionListener(this.eventListener);

        JPanel boxPanel = new JPanel();
        boxPanel.setOpaque(false);
        box.add(btnHome);
        box.add(btnGroupes);
        box.add(btnEnfants);
        box.add(btnNotes);
        box.add(btnDex);
        boxPanel.add(box);
        this.add(boxPanel);
    }
}
