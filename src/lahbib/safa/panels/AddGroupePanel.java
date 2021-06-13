package lahbib.safa.panels;

import lahbib.safa.dbo.GroupeDBO;
import lahbib.safa.dbo.UtilisateurDBO;
import lahbib.safa.models.Groupe;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddGroupePanel  extends JPanel implements ActionListener {
    FormAddGroupe formAddGroupe;
    JTable tableGroupe;
    DefaultTableModel modeltable;
    List<Groupe> groupes;
    public AddGroupePanel(){
        this.setLayout(new FlowLayout());
        this.initComponents();
    }
    public void initComponents(){
        formAddGroupe = new FormAddGroupe(this);
        tableGroupe =new JTable();
        modeltable =new DefaultTableModel();
        String[] columns = {
                "ID",
                "Nom",
                "Employee"
        };
        modeltable.setColumnIdentifiers(columns);

        groupes = GroupeDBO.listerGroupes();
        for(Groupe groupe: groupes) {
            Object[] o = new Object[3];
            o[0] = groupe.getIdGroupe();
            o[1] = groupe.getNom();
            o[2] = groupe.getEmployee().toString();
            modeltable.addRow(o);
        }
        tableGroupe.setModel(modeltable);
        JScrollPane sp = new JScrollPane(tableGroupe);

        this.add(sp);
        this.add(formAddGroupe);


        tableGroupe.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                Integer index = tableGroupe.getSelectedRow();
                if (index > -1) {
                    Groupe groupe = groupes.get(index);
                    formAddGroupe.setEditMode(true);
                    formAddGroupe.setNom(groupe.getNom());
                    formAddGroupe.setEmployee(groupe.getEmployee());
                } else {
                    formAddGroupe.setEditMode(false);

                    formAddGroupe.setNom("");
                    formAddGroupe.setEmployee(null);
                }

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = ((JButton) actionEvent.getSource()).getText();
        if (action == "Ajouter groupe") {
            Groupe groupe = new Groupe(
                    null,
                    formAddGroupe.getNom(),
                    formAddGroupe.getEmployee()
            );
            groupe = GroupeDBO.insererGroupe(groupe);
            if (groupe == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur au cours du creation du groupe",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                groupes.add(groupe);
                Object[] o = new Object[3];
                o[0] = groupe.getIdGroupe();
                o[1] = groupe.getNom();
                o[2] = groupe.getEmployee().toString();
                formAddGroupe.setNom("");
                formAddGroupe.setEmployee(null);
                modeltable.addRow(o);
                modeltable.fireTableDataChanged();
                tableGroupe.revalidate();
            }
        } else if (action == "Annuler") {
            formAddGroupe.setNom("");
            formAddGroupe.setEmployee(null);
            tableGroupe.clearSelection();
        } else if (action == "Supprimer") {
            Integer index = tableGroupe.getSelectedRow();
            Integer idGroupe = (Integer)tableGroupe.getValueAt(index, 0);
            Integer resultat = GroupeDBO.supprimerGroupe(idGroupe);
            if (resultat > -1) {
                groupes.remove(index);
                modeltable.removeRow(index);
                modeltable.fireTableDataChanged();
                tableGroupe.revalidate();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur au cours du suppression du groupe",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (action == "Modifier") {
            Integer index = tableGroupe.getSelectedRow();
            Integer idGroupe = (Integer) tableGroupe.getValueAt(index, 0);
            Groupe groupe = new Groupe(
                    idGroupe,
                    formAddGroupe.getNom(),
                    formAddGroupe.getEmployee()
            );
            Integer resultat = GroupeDBO.modifierGroupe(groupe);
            if (resultat > 0) {
                groupes.remove(index);
                groupes.add(index, groupe);
                modeltable.removeRow(index);
                Object[] o = new Object[3];
                o[0] = groupe.getIdGroupe();
                o[1] = groupe.getNom();
                o[2] = groupe.getEmployee().toString();
                modeltable.insertRow(index, o);
                modeltable.fireTableDataChanged();
                tableGroupe.revalidate();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur au cours du modification du groupe",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

}
