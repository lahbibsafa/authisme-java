package lahbib.safa.panels;

import lahbib.safa.dbo.ActiviteDBO;
import lahbib.safa.dbo.EvenementDBO;
import lahbib.safa.dbo.GroupeDBO;
import lahbib.safa.dbo.UtilisateurDBO;
import lahbib.safa.models.Activite;
import lahbib.safa.models.Evenement;
import lahbib.safa.models.Groupe;
import lahbib.safa.models.Utilisateur;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class FormAddPlanification extends JPanel {
        JButton btnAjout, btnModifier, btnSupprimer, btnAnnuler;
        JLabel dateLable, activiteLabel, groupeLabel;

        JComboBox<Activite> activiteBox;
        JComboBox<Groupe> groupeBox;

        JPanel btnAjoutPanel, btnEditPanel;
        List<Evenement> listEvenement;
        List<Groupe> listGroupes;
        List<Activite> listActivites;
        UtilDateModel model;
        public FormAddPlanification(ActionListener event){
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            btnAjout = new JButton("Ajouter Planification");
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
            dateLable = new JLabel("Date Activité");
            activiteLabel = new JLabel("Activité");
            groupeLabel = new JLabel("Groupe");


            listActivites = ActiviteDBO.listerActivite();
            listGroupes = GroupeDBO.listerGroupes();
            listEvenement = EvenementDBO.listerEvenement();

            activiteBox = new JComboBox<Activite>();
            for(Activite activite: listActivites){
                activiteBox.addItem(activite);
            }

            groupeBox = new JComboBox<Groupe>();
            for(Groupe groupe: listGroupes){
                groupeBox.addItem(groupe);
            }
            model = new UtilDateModel();
            JDatePanelImpl datePanel = new JDatePanelImpl(model);
            JDatePickerImpl dateInput = new JDatePickerImpl(datePanel);


            btnAjoutPanel = new JPanel();
            btnAjoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            btnAjoutPanel.add(btnAjout);

            btnEditPanel = new JPanel();
            btnEditPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            btnEditPanel.add(btnAnnuler);
            btnEditPanel.add(btnSupprimer);
            btnEditPanel.add(btnModifier);
            btnEditPanel.setVisible(false);


            JPanel datePane = new JPanel();
            datePane.add(dateLable);
            datePane.add(dateInput);

            JPanel groupePanel  = new JPanel();
            groupePanel.add(groupeLabel);
            groupePanel.add(groupeBox);

            JPanel activitePanel = new JPanel();
            activitePanel.add(activiteLabel);
            activitePanel.add(activiteBox);

            this.add(datePane);
            this.add(activitePanel);
            this.add(groupePanel);

            this.add(btnAjoutPanel);
            this.add(btnEditPanel);
        }

    public void setDate(Date date) {
        model.setValue(date);
    }
    public Date getDate() {
        return model.getValue();
    }

    public Groupe getGroupe() {
        return listGroupes.get(groupeBox.getSelectedIndex());
    }
    public void setGroupe(Groupe groupe) {
        if (groupe == null) {
            groupeBox.setSelectedIndex(0);
        } else {
            int i = 0;
            for(Groupe grp: listGroupes){
                if (grp.getIdGroupe() == groupe.getIdGroupe()) {
                    groupeBox.setSelectedIndex(i);
                    break;
                }
                i++;
            }
        }
    }
    public Activite getActivite() {
        return listActivites.get(activiteBox.getSelectedIndex());
    }
    public void setActivite(Activite activite) {
        if (activite == null) {
            activiteBox.setSelectedIndex(0);
        } else {
            int i = 0;
            for(Activite act: listActivites){
                if (act.getIdActivite() == activite.getIdActivite()) {
                    activiteBox.setSelectedIndex(i);
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