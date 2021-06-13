package lahbib.safa.panels;

import jdk.jshell.execution.Util;
import lahbib.safa.dbo.EnfantDBO;
import lahbib.safa.dbo.UtilisateurDBO;
import lahbib.safa.models.Enfant;
import lahbib.safa.models.Utilisateur;

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


public class AddEnfantPanel extends JPanel implements ActionListener {
    FormEnfant formEnfant;
    JTable tableEnfant;
    List<Enfant> enfants;
    DefaultTableModel model;
    DateFormat dateFormat;

    public AddEnfantPanel(){
        this.setLayout(new FlowLayout());
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.initComponents();
    }
    public void initComponents(){
        formEnfant = new FormEnfant(this);
        tableEnfant = new JTable();
        model = new DefaultTableModel();
        String[] columns = {
                "ID",
                "Nom",
                "Prenom",
                "Date naissance",
                "Contact",
                "Adresse",
                "Groupe"
        };
        model.setColumnIdentifiers(columns);
        enfants = EnfantDBO.listerEnfant();
        for(Enfant enfant: enfants) {
            Object[] o = new Object[7];
            o[0] = enfant.getIdEnfant();
            o[1] = enfant.getNom();
            o[2] = enfant.getPrenom();
            o[3] = dateFormat.format(enfant.getNaissance());
            o[4] = enfant.getContact();
            o[5] = enfant.getAddresse();
            o[6] = enfant.getGroupe().getNom();

            model.addRow(o);
        }

        tableEnfant.setModel(model);
        JScrollPane sp = new JScrollPane(tableEnfant);

        tableEnfant.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                Integer index = tableEnfant.getSelectedRow();
                if (index > -1) {
                    Enfant enfant = enfants.get(index);
                    formEnfant.setModeEdit(true);
                    formEnfant.setNom(enfant.getNom());
                    formEnfant.setPrenom(enfant.getPrenom());
                    formEnfant.setContact(enfant.getContact());
                    formEnfant.setNaissance(enfant.getNaissance());
                    formEnfant.setAdresse(enfant.getAddresse());
                    formEnfant.setGroupe(enfant.getGroupe());
                } else {
                    formEnfant.setModeEdit(false);
                    formEnfant.setNom("");
                    formEnfant.setPrenom("");
                    formEnfant.setContact("");
                    formEnfant.setNaissance(null);
                    formEnfant.setAdresse("");
                    formEnfant.setGroupe(null);
                }

            }
        });


        this.add(sp);
        this.add(formEnfant);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = ((JButton) actionEvent.getSource()).getText();
        if (action == "Annuler") {
            tableEnfant.clearSelection();
        } else if (action == "Ajouter Enfant") {
            Enfant enfant = new Enfant(
                    null,
                    formEnfant.getNaissance(),
                    formEnfant.getNom(),
                    formEnfant.getPrenom(),
                    formEnfant.getContact(),
                    formEnfant.getAddresse(),
                    formEnfant.getGroupe()
            );
            enfant = EnfantDBO.insererEnfant(enfant);
            if (enfant == null) {
                JOptionPane.showMessageDialog(this,
                        "Erreur au cours d'insertion d'enfant, veuillez ressayer encore!",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                enfants.add(enfant);
                Object[] o = new Object[7];
                o[0] = enfant.getIdEnfant();
                o[1] = enfant.getNom();
                o[2] = enfant.getPrenom();
                o[3] = dateFormat.format(enfant.getNaissance());
                o[4] = enfant.getContact();
                o[5] = enfant.getAddresse();
                o[6] = enfant.getGroupe().getNom();
                model.addRow(o);
                model.fireTableDataChanged();
                tableEnfant.revalidate();
            }
        } else if (action == "Supprimer") {
            Integer index = tableEnfant.getSelectedRow();
            Integer idEnfant = (Integer) tableEnfant.getValueAt(index, 0);
            Integer resultat = EnfantDBO.supprimerEnfant(idEnfant);
            if (resultat > 0) {
                enfants.remove(index);
                model.removeRow(index);
                model.fireTableDataChanged();
                tableEnfant.revalidate();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur du suppression d'enfant",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (action == "Modifier Enfant") {
            Integer index = tableEnfant.getSelectedRow();
            Integer idEnfant = (Integer) tableEnfant.getValueAt(index, 0);
            Enfant enfant = new Enfant(
                    idEnfant,
                    formEnfant.getNaissance(),
                    formEnfant.getNom(),
                    formEnfant.getPrenom(),
                    formEnfant.getContact(),
                    formEnfant.getAddresse(),
                    formEnfant.getGroupe()
            );
            Integer resultat = EnfantDBO.modifierEnfant(enfant);
            if (resultat > 0) {
                enfants.remove(index);
                enfants.add(index, enfant);
                model.removeRow(index);
                Object[] o = new Object[7];
                o[0] = enfant.getIdEnfant();
                o[1] = enfant.getNom();
                o[2] = enfant.getPrenom();
                o[3] = dateFormat.format(enfant.getNaissance());
                o[4] = enfant.getContact();
                o[5] = enfant.getAddresse();
                o[6] = enfant.getGroupe().getNom();
                model.insertRow(index, o);
                model.fireTableDataChanged();
                tableEnfant.revalidate();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur au cours du modification du l'enfants",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
