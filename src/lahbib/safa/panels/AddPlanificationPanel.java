package lahbib.safa.panels;

import lahbib.safa.dbo.EvenementDBO;
import lahbib.safa.dbo.GroupeDBO;
import lahbib.safa.models.Evenement;
import lahbib.safa.models.Groupe;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddPlanificationPanel extends JPanel implements ActionListener {
    FormAddPlanification formAddPlanification;
    JTable tablePlanification;
    DefaultTableModel modeltable;
    List<Evenement> evenements;
    DateFormat dateFormat;

    public AddPlanificationPanel(){
        this.setLayout(new FlowLayout());
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.initComponents();
    }
    public void initComponents(){
        formAddPlanification = new FormAddPlanification(this);
        tablePlanification =new JTable();
        modeltable =new DefaultTableModel();
        String[] columns = {
                "ID",
                "Date",
                "Activite",
                "Groupe"
        };
        modeltable.setColumnIdentifiers(columns);

        evenements = EvenementDBO.listerEvenement();
        for(Evenement evenement: evenements) {
            Object[] o = new Object[4];
            o[0] = evenement.getIdEvenement();
            o[1] = dateFormat.format(evenement.getDate());
            o[2] = evenement.getActivte().getDesignation();
            o[3] = evenement.getGroupe().getNom();
            modeltable.addRow(o);
        }
        tablePlanification.setModel(modeltable);
        JScrollPane sp = new JScrollPane(tablePlanification);

        this.add(sp);
        this.add(formAddPlanification);


        tablePlanification.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                Integer index = tablePlanification.getSelectedRow();
                if (index > -1) {
                    Evenement evenement = evenements.get(index);
                    formAddPlanification.setEditMode(true);
                    formAddPlanification.setDate(evenement.getDate());
                    formAddPlanification.setActivite(evenement.getActivte());
                    formAddPlanification.setGroupe(evenement.getGroupe());
                } else {
                    formAddPlanification.setEditMode(false);

                    formAddPlanification.setDate(null);
                    formAddPlanification.setGroupe(null);
                    formAddPlanification.setActivite(null);
                }

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = ((JButton) actionEvent.getSource()).getText();
        if (action == "Ajouter Planification") {
            Evenement evenement = new Evenement(
                    null,
                    formAddPlanification.getDate(),
                    formAddPlanification.getActivite(),
                    formAddPlanification.getGroupe()
            );
            evenement = EvenementDBO.insererEvenement(evenement);
            if (evenement == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur au cours du creation d'evenement",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                evenements.add(evenement);
                Object[] o = new Object[4];
                o[0] = evenement.getIdEvenement();
                o[1] = dateFormat.format(evenement.getDate());
                o[2] = evenement.getActivte().getDesignation();
                o[3] = evenement.getGroupe().getNom();
                formAddPlanification.setDate(null);
                formAddPlanification.setGroupe(null);
                formAddPlanification.setActivite(null);
                modeltable.addRow(o);
                modeltable.fireTableDataChanged();
                tablePlanification.revalidate();
            }
        } else if (action == "Annuler") {
            formAddPlanification.setDate(null);
            formAddPlanification.setGroupe(null);
            formAddPlanification.setActivite(null);
            tablePlanification.clearSelection();
        } else if (action == "Supprimer") {
            Integer index = tablePlanification.getSelectedRow();
            Integer idEvenement = (Integer)tablePlanification.getValueAt(index, 0);
            Integer resultat = EvenementDBO.supprimerEvenement(idEvenement);
            if (resultat > -1) {
                evenements.remove(index);
                modeltable.removeRow(index);
                modeltable.fireTableDataChanged();
                tablePlanification.revalidate();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur au cours du suppression d'evenement",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (action == "Modifier") {
            Integer index = tablePlanification.getSelectedRow();
            Integer idEvenement = (Integer) tablePlanification.getValueAt(index, 0);
            Evenement evenement = new Evenement(
                    idEvenement,
                    formAddPlanification.getDate(),
                    formAddPlanification.getActivite(),
                    formAddPlanification.getGroupe()
            );
            Integer resultat = EvenementDBO.modifierEvenement(evenement);
            if (resultat > 0) {
                evenements.remove(index);
                evenements.add(index, evenement);
                modeltable.removeRow(index);
                Object[] o = new Object[4];
                o[0] = evenement.getIdEvenement();
                o[1] = dateFormat.format(evenement.getDate());
                o[2] = evenement.getActivte().getDesignation();
                o[3] = evenement.getGroupe().getNom();
                modeltable.insertRow(index, o);
                modeltable.fireTableDataChanged();
                tablePlanification.revalidate();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur au cours du modification d'evenement",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

}
