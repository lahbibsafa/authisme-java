package lahbib.safa.panels;

import lahbib.safa.dbo.UtilisateurDBO;
import lahbib.safa.models.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class FormAddGroupe extends JPanel {
        JButton btnAjout, btnModifier, btnSupprimer, btnAnnuler;
        JLabel nomLabel, employeeLabel;
        JTextField nomInput;
        JComboBox<Utilisateur> employeeBox;
        JPanel btnAjoutPanel, btnEditPanel;
        List<Utilisateur> listEmployee;
        public FormAddGroupe(ActionListener event){
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            btnAjout = new JButton("Ajouter groupe");
            this.btnAjout.addActionListener(event);
            btnModifier = new JButton("Modifier");
            this.btnModifier.addActionListener(event);
            btnSupprimer = new JButton("Supprimer");
            this.btnSupprimer.addActionListener(event);
            btnAnnuler = new JButton("Annuler");
            this.btnAnnuler.addActionListener(event);
            this.initComponent();


        }
        public void initComponent(){
            nomLabel = new JLabel("Nom Groupe");
            employeeLabel = new JLabel("Employee");


            nomInput = new JTextField(20);
            Font bigFont = nomInput.getFont().deriveFont(Font.PLAIN, 18f);
            nomInput.setFont(bigFont);
            listEmployee = UtilisateurDBO.listerEmployee();
            employeeBox = new JComboBox<Utilisateur>();
            for(Utilisateur employee: listEmployee){
                employeeBox.addItem(employee);
            }

            JPanel nomPanel = new JPanel();
            nomPanel.add(nomLabel);
            nomPanel.add(nomInput);

            JPanel employeePanel = new JPanel();
            employeePanel.add(employeeLabel);
            employeePanel.add(employeeBox);


            btnAjoutPanel = new JPanel();
            btnAjoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            btnAjoutPanel.add(btnAjout);

            btnEditPanel = new JPanel();
            btnEditPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            btnEditPanel.add(btnAnnuler);
            btnEditPanel.add(btnSupprimer);
            btnEditPanel.add(btnModifier);
            btnEditPanel.setVisible(false);

            this.add(nomPanel);
            this.add(employeePanel);
            this.add(btnAjoutPanel);
            this.add(btnEditPanel);
        }
        public String getNom() {
            return this.nomInput.getText();
        }
        public Utilisateur getEmployee() {
            return listEmployee.get(employeeBox.getSelectedIndex());
        }
        public void setNom(String nom) {
            this.nomInput.setText(nom);
        }
        public void setEmployee(Utilisateur employee) {
            if (employee == null) {
                employeeBox.setSelectedIndex(0);
            } else {
                int i = 0;
                for(Utilisateur emp: listEmployee){
                    if (emp.equals(employee)) {
                        employeeBox.setSelectedIndex(i);
                        break;
                    }
                    i++;
                }
            }
        }
        public void setEditMode(Boolean edit) {
            if (edit) {
                this.btnEditPanel.setVisible(true);
                this.btnAjoutPanel.setVisible(false);
            } else {
                this.btnEditPanel.setVisible(false);
                this.btnAjoutPanel.setVisible(true);
            }
        }
}