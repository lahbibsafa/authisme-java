package lahbib.safa.panels;

import lahbib.safa.dbo.ActiviteDBO;
import lahbib.safa.dbo.GroupeDBO;
import lahbib.safa.dbo.UtilisateurDBO;
import lahbib.safa.models.Activite;
import lahbib.safa.models.Groupe;
import lahbib.safa.models.Utilisateur;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SuivieEmployeePanel extends JPanel {
    JComboBox<Utilisateur> employeeBox;
    List<Utilisateur> listEmployee;
    List<Groupe> listGroupe;
    JTable tableGroupe;
    DefaultTableModel modeltable;
    String[] columns = {
            "ID",
            "Nom"
    };
    SuivieEmployeePanel(){
        employeeBox = new JComboBox<Utilisateur>();
        listEmployee = UtilisateurDBO.listerEmployee();
        for(Utilisateur utilisateur: listEmployee) {
            employeeBox.addItem(utilisateur);
        }
        JPanel panelBox = new JPanel();
        panelBox.setLayout(new BoxLayout(panelBox, BoxLayout.Y_AXIS));
        panelBox.add(new JLabel("Employee"));
        panelBox.add(employeeBox);
        employeeBox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer index = employeeBox.getSelectedIndex();
                if (index > -1) {
                    listGroupe = GroupeDBO.filtrerParEmployee(listEmployee.get(index).getIdUtilisateur());
                    modeltable = new DefaultTableModel();
                    modeltable.setColumnIdentifiers(columns);
                    for(Groupe g: listGroupe) {
                        modeltable.addRow(new Object[]{
                                g.getIdGroupe(),
                                g.getNom()
                        });
                    }
                    tableGroupe.setModel(modeltable);
                    tableGroupe.revalidate();
                }
            }
        });
        this.add(panelBox);
        tableGroupe =new JTable();
        modeltable =new DefaultTableModel();

        modeltable.setColumnIdentifiers(columns);

        tableGroupe.setModel(modeltable);
        JScrollPane sp = new JScrollPane(tableGroupe);
        this.add(sp);
    }
}
