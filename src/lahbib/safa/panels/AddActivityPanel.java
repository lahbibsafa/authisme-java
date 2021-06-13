package lahbib.safa.panels;

import lahbib.safa.dbo.ActiviteDBO;
import lahbib.safa.dbo.UtilisateurDBO;
import lahbib.safa.models.Activite;
import lahbib.safa.models.Utilisateur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class AddActivityPanel extends JPanel implements ActionListener{
    FormAddActivity formAddActivity;
    JTable tableActivite;
    DefaultTableModel modeltable;
    List<Activite> activities;
    public AddActivityPanel(){
        this.setLayout(new FlowLayout());
        this.initComponents();
    }
    public void initComponents(){
        formAddActivity = new FormAddActivity(this);
        tableActivite =new JTable();
        modeltable =new DefaultTableModel();
        String[] columns = {
                "ID",
                "Designation"
        };
        modeltable.setColumnIdentifiers(columns);

        activities = ActiviteDBO.listerActivite();
        for(Activite activite: activities) {
            Object[] o = new Object[2];
            o[0] = activite.getIdActivite();
            o[1] = activite.getDesignation();
            modeltable.addRow(o);
        }
        tableActivite.setModel(modeltable);
        JScrollPane sp = new JScrollPane(tableActivite);

        this.add(sp);
        this.add(formAddActivity);


        tableActivite.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                Integer index = tableActivite.getSelectedRow();
                if (index > -1) {
                    Activite activite = activities.get(index);
                    formAddActivity.setEditMode(true);
                    formAddActivity.setDesignation(activite.getDesignation());
                    formAddActivity.setDescription(activite.getDescription());

                } else {
                    formAddActivity.setEditMode(false);

                    formAddActivity.setDesignation("");
                    formAddActivity.setDescription("");
                }

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = ((JButton) actionEvent.getSource()).getText();
        if (action == "Ajouter une activite") {
            Activite activite = new Activite(
                    null,
                    formAddActivity.getDesignation(),
                    formAddActivity.getDescription()
            );
            activite = ActiviteDBO.insereActivite(activite);
            if (activite == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur au cours du creation d'une activite",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                activities.add(activite);
                Object[] o = new Object[2];
                o[0] = activite.getIdActivite();
                o[1] = activite.getDesignation();
                formAddActivity.setDesignation("");
                formAddActivity.setDescription("");
                modeltable.addRow(o);
                modeltable.fireTableDataChanged();
                tableActivite.revalidate();
            }
        } else if (action == "Annuler") {
            formAddActivity.setDesignation("");
            formAddActivity.setDescription("");
            tableActivite.clearSelection();
        } else if (action == "Supprimer") {
            Integer index = tableActivite.getSelectedRow();
            Integer idActivite = (Integer)tableActivite.getValueAt(index, 0);
            Integer resultat = ActiviteDBO.supprimerActivite(idActivite);
            if (resultat > -1) {
                activities.remove(index);
                modeltable.removeRow(index);
                modeltable.fireTableDataChanged();
                tableActivite.revalidate();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur au cours du suppression d'une activite",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (action == "Modifier l'activite") {
            Integer index = tableActivite.getSelectedRow();
            Integer idActivite = (Integer) tableActivite.getValueAt(index, 0);
            Activite activite = new Activite(
                    idActivite,
                    formAddActivity.getDesignation(),
                    formAddActivity.getDescription()
            );
            Integer resultat = ActiviteDBO.modifierActivite(activite);
            if (resultat > 0) {
                activities.remove(index);
                activities.add(index, activite);
                modeltable.removeRow(index);
                Object[] o = new Object[2];
                o[0] = activite.getIdActivite();
                o[1] = activite.getDesignation();
                modeltable.insertRow(index, o);
                modeltable.fireTableDataChanged();
                tableActivite.revalidate();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur au cours du modification du l'activite",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

}
