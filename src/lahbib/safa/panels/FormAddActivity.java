package lahbib.safa.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class FormAddActivity  extends JPanel {
        JButton btnAjout, btnModifier, btnSupprimer, btnAnnuler;
        JLabel nomActivityLabel, designationLabel, descriptionLabel;
        JTextField nomActivityInput, designationInput;
        JTextArea descriptionInput;
        JPanel btnAjoutPanel, btnEditPanel;
        public FormAddActivity(ActionListener event){
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            btnAjout = new JButton("Ajouter une activite");
            this.btnAjout.addActionListener(event);
            btnModifier = new JButton("Modifier l'activite");
            this.btnModifier.addActionListener(event);
            btnSupprimer = new JButton("Supprimer");
            this.btnSupprimer.addActionListener(event);
            btnAnnuler = new JButton("Annuler");
            this.btnAnnuler.addActionListener(event);
            this.initComponent();


        }
        public void initComponent(){
            designationLabel = new JLabel("Désignation");
            descriptionLabel = new JLabel("Description");


            designationInput = new JTextField(20);
            descriptionInput = new JTextArea(20, 20);
            Font bigFont = designationInput.getFont().deriveFont(Font.PLAIN, 18f);
            designationInput.setFont(bigFont);
            descriptionInput.setFont(bigFont);

            JPanel designationActivityPanel = new JPanel();
            designationActivityPanel.add(designationLabel);
            designationActivityPanel.add(designationInput);

            btnAjoutPanel = new JPanel();
            btnAjoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            btnAjoutPanel.add(btnAjout);

            btnEditPanel = new JPanel();
            btnEditPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            btnEditPanel.add(btnAnnuler);
            btnEditPanel.add(btnSupprimer);
            btnEditPanel.add(btnModifier);
            btnEditPanel.setVisible(false);

            this.add(designationActivityPanel);
            this.add(descriptionLabel);
            this.add(descriptionInput);
            this.add(btnAjoutPanel);
            this.add(btnEditPanel);
        }
        public String getDesignation() {
            return this.designationInput.getText();
        }
        public String getDescription() {
            return this.descriptionInput.getText();
        }

        public void setDesignation(String designation) {
            this.designationInput.setText(designation);
        }
        public void setDescription(String description) {
            this.descriptionInput.setText(description);
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