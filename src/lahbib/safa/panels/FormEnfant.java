package lahbib.safa.panels;

import lahbib.safa.dbo.GroupeDBO;
import lahbib.safa.models.Groupe;
import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FormEnfant extends JPanel {
    JButton btnAjout, btnModifier, btnSupprimer, btnAnnuler;
    JPanel btnAjoutPanel, btnEditPanel;
    JLabel nomLabel, prenomLabel, naissanceLabel, contactLabel, addresseLabel, groupeLabel;
    JTextField nomInput, prenomInput, contactInput, addresseInput;
    JComboBox<Groupe> groupeBox;
    JDatePickerImpl naissanceInput;
    List<Groupe> groupes;
    UtilDateModel model;
    public FormEnfant(ActionListener event){
        groupes = GroupeDBO.listerGroupes();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.initComponent();
        btnAjout.addActionListener(event);
        btnModifier.addActionListener(event);
        btnSupprimer.addActionListener(event);
        btnAnnuler.addActionListener(event);

    }
    public void initComponent(){
        btnAjout = new JButton("Ajouter Enfant");
        btnModifier = new JButton("Modifier Enfant");
        btnSupprimer = new JButton("Supprimer");
        btnAnnuler = new JButton("Annuler");

        nomLabel = new JLabel("Nom");
        prenomLabel = new JLabel("Prenom");
        addresseLabel = new JLabel("Adresse");
        contactLabel = new JLabel("Telephone");
        naissanceLabel = new JLabel("Date naissance");
        groupeLabel = new JLabel("Groupe");


        nomInput = new JTextField(20);
        prenomInput = new JTextField(20);
        addresseInput = new JTextField(20);
        contactInput = new JTextField(20);

        groupeBox = new JComboBox<Groupe>();
        for(Groupe groupe : groupes) {
            groupeBox.addItem(groupe);
        }


        model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl naissanceInput = new JDatePickerImpl(datePanel);


        Font bigFont = nomInput.getFont().deriveFont(Font.PLAIN, 18f);
        nomInput.setFont(bigFont);
        prenomInput.setFont(bigFont);
        addresseInput.setFont(bigFont);
        contactInput.setFont(bigFont);


        JPanel nomPanel = new JPanel();
        nomPanel.add(nomLabel);
        nomPanel.add(nomInput);

        JPanel prenomPanel = new JPanel();
        prenomPanel.add(prenomLabel);
        prenomPanel.add(prenomInput);

        JPanel addressPanel = new JPanel();
        addressPanel.add(addresseLabel);
        addressPanel.add(addresseInput);

        JPanel phonePanel = new JPanel();
        phonePanel.add(contactLabel);
        phonePanel.add(contactInput);

        JPanel agePanel = new JPanel();
        agePanel.add(naissanceLabel);
        agePanel.add(naissanceInput);

        JPanel groupesPanel = new JPanel();
        groupesPanel.add(groupeLabel);
        groupesPanel.add(groupeBox);

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
        this.add(prenomPanel);
        this.add(agePanel);
        this.add(addressPanel);
        this.add(phonePanel);
        this.add(groupesPanel);
        this.add(btnAjoutPanel);
        this.add(btnEditPanel);
    }

    public void setModeEdit(boolean edit) {
        if (edit) {
            btnEditPanel.setVisible(true);
            btnAjoutPanel.setVisible(false);
        } else {
            btnEditPanel.setVisible(false);
            btnAjoutPanel.setVisible(true);
        }
    }

    public void setNom(String nom) {
        nomInput.setText(nom);
    }

    public void setPrenom(String prenom) {
        prenomInput.setText(prenom);
    }

    public void setContact(String contact) {
        contactInput.setText(contact);
    }

    public void setNaissance(Date naissance) {
        model.setValue(naissance);
    }

    public void setAdresse(String addresse) {
        addresseInput.setText(addresse);
    }

    public void setGroupe(Groupe groupe) {
        if (groupe != null) {
            Integer index = 0;
            for (Groupe g: groupes) {
                if (g.getIdGroupe() == groupe.getIdGroupe()) {
                    groupeBox.setSelectedIndex(index);
                    break;
                }
                index++;
            }
        } else {
            groupeBox.setSelectedIndex(0);
        }

    }

    public Date getNaissance() {
        return model.getValue();
    }

    public String getNom() {
        return nomInput.getText();
    }

    public String getPrenom() {
        return  prenomInput.getText();
    }

    public String getContact() {
        return contactInput.getText();
    }

    public String getAddresse() {
        return addresseInput.getText();
    }

    public Groupe getGroupe() {
        return groupes.get(groupeBox.getSelectedIndex());
    }
}
