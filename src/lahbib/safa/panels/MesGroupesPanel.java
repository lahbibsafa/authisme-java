package lahbib.safa.panels;

import lahbib.safa.dbo.EnfantDBO;
import lahbib.safa.dbo.GroupeDBO;
import lahbib.safa.models.Enfant;
import lahbib.safa.models.Groupe;
import lahbib.safa.models.Session;
import lahbib.safa.models.Utilisateur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MesGroupesPanel extends JPanel {
    List<Enfant> listEnfants;
    List<Groupe> listGroupes;
    JTable tableGroupes, tableEnfant;
    DefaultTableModel groupeModel, enfantModel;
    String[] columnsGroupe = {
            "ID",
            "Nom"
    };
    String[] columnsEnfant = {
            "ID",
            "Nom",
            "Prenom",
            "Date Naissance",
            "Addresse",
            "Telephone"
    };

    DateFormat dateFormat;
    public MesGroupesPanel(){
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        listGroupes = GroupeDBO.filtrerParEmployee(Session.getUser().getIdUtilisateur());
        groupeModel = new DefaultTableModel();
        groupeModel.setColumnIdentifiers(columnsGroupe);
        for(Groupe groupe: listGroupes) {
            groupeModel.addRow(new Object[]{
                    groupe.getIdGroupe(),
                    groupe.getNom()
            });
        }
        tableGroupes = new JTable();
        tableGroupes.setModel(groupeModel);
        JScrollPane scrollPaneGroupe = new JScrollPane(tableGroupes);
        this.add(scrollPaneGroupe);

        tableGroupes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                Integer index = tableGroupes.getSelectedRow();
                if (index > -1) {
                    Groupe groupe = listGroupes.get(index);
                    enfantModel = new DefaultTableModel();
                    enfantModel.setColumnIdentifiers(columnsEnfant);
                    listEnfants = EnfantDBO.filtrerParGroupe(groupe.getIdGroupe());

                    for(Enfant enfant: listEnfants) {
                        enfantModel.addRow(new Object[]{
                                enfant.getIdEnfant(),
                                enfant.getNom(),
                                enfant.getPrenom(),
                                dateFormat.format(enfant.getNaissance()),
                                enfant.getAddresse(),
                                enfant.getContact()
                        });
                    }
                    tableEnfant.setModel(enfantModel);
                    tableEnfant.revalidate();
                    tableEnfant.setVisible(true);
                } else {
                    tableEnfant.setVisible(false);
                }
            }
        });
        tableEnfant = new JTable();
        enfantModel = new DefaultTableModel();
        enfantModel.setColumnIdentifiers(columnsEnfant);
        tableEnfant.setModel(enfantModel);
        JScrollPane scrollPaneEnfant = new JScrollPane(tableEnfant);
        this.add(scrollPaneEnfant);
    }
}
