package lahbib.safa.panels;

import jdk.jshell.execution.Util;
import lahbib.safa.dbo.UtilisateurDBO;
import lahbib.safa.models.Utilisateur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class AddEmployeePanel extends JPanel implements ActionListener {
    FormEmployee formEmployee;
    JTable tableEmlpoyee;
    List<Utilisateur> employees;
    DefaultTableModel model;
    public AddEmployeePanel(){
        this.setLayout(new FlowLayout());
        this.initComponents();
    }
    public void initComponents(){
        formEmployee = new FormEmployee(this);
        tableEmlpoyee = new JTable();
        model = new DefaultTableModel();
        String[] columns = {
                "ID",
                "Nom",
                "Prenom",
                "E-mail",
                "Login"
        };
        model.setColumnIdentifiers(columns);
        employees = UtilisateurDBO.listerEmployee();
        for(Utilisateur utilisateur: employees) {
            Object[] o = new Object[5];
            o[0] = utilisateur.getIdUtilisateur();
            o[1] = utilisateur.getNom();
            o[2] = utilisateur.getPrenom();
            o[3] = utilisateur.getMail();
            o[4] = utilisateur.getLogin();
            model.addRow(o);
        }

        tableEmlpoyee.setModel(model);
        JScrollPane sp = new JScrollPane(tableEmlpoyee);

        tableEmlpoyee.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                Integer index = tableEmlpoyee.getSelectedRow();
                if (index > -1) {
                    Utilisateur utilisateur = employees.get(index);
                    formEmployee.setModeEdit(true);
                    formEmployee.setNom(utilisateur.getNom());
                    formEmployee.setPrenom(utilisateur.getPrenom());
                    formEmployee.setMail(utilisateur.getMail());
                    formEmployee.setLogin(utilisateur.getLogin());
                    formEmployee.setPassword(utilisateur.getPassword());
                } else {
                    formEmployee.setModeEdit(false);
                    formEmployee.setNom("");
                    formEmployee.setPrenom("");
                    formEmployee.setMail("");
                    formEmployee.setLogin("");
                    formEmployee.setPassword("");
                }

            }
        });


        this.add(sp);
        this.add(formEmployee);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = ((JButton) actionEvent.getSource()).getText();
        if (action == "Annuler") {
            tableEmlpoyee.clearSelection();
        } else if (action == "Ajouter Employee") {
            Utilisateur utilisateur = new Utilisateur(
                    null,
                    formEmployee.getNom(),
                    formEmployee.getPrenom(),
                    formEmployee.getMail(),
                    "employee",
                    formEmployee.getLogin(),
                    formEmployee.getPassword()
            );
            utilisateur = UtilisateurDBO.insererUtilisateur(utilisateur);
            if (utilisateur == null) {
                JOptionPane.showMessageDialog(this,
                        "Erreur au cours d'insertion d'utilisateur, veuillez ressayer encore!",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                employees.add(utilisateur);
                Object[] o = new Object[5];
                o[0] = utilisateur.getIdUtilisateur();
                o[1] = utilisateur.getNom();
                o[2] = utilisateur.getPrenom();
                o[3] = utilisateur.getMail();
                o[4] = utilisateur.getLogin();
                model.addRow(o);
                model.fireTableDataChanged();
                tableEmlpoyee.revalidate();
            }
        } else if (action == "Supprimer") {
            Integer index = tableEmlpoyee.getSelectedRow();
            Integer idUtilisateur = (Integer) tableEmlpoyee.getValueAt(index, 0);
            Integer resultat = UtilisateurDBO.supprimerUtilisateur(idUtilisateur);
            if (resultat > 0) {
                employees.remove(index);
                model.removeRow(index);
                model.fireTableDataChanged();
                tableEmlpoyee.revalidate();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur du suppression employee",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (action == "Modifier Employee") {
            Integer index = tableEmlpoyee.getSelectedRow();
            Integer idUtilisateur = (Integer) tableEmlpoyee.getValueAt(index, 0);
            Utilisateur utilisateur = new Utilisateur(
                    idUtilisateur,
                    formEmployee.getNom(),
                    formEmployee.getPrenom(),
                    formEmployee.getMail(),
                    "employee",
                    formEmployee.getLogin(),
                    formEmployee.getPassword()
            );
            Integer resultat = UtilisateurDBO.modifierUtilisateur(utilisateur);
            if (resultat > 0) {
                employees.remove(index);
                employees.add(index, utilisateur);
                model.removeRow(index);
                Object[] o = new Object[5];
                o[0] = utilisateur.getIdUtilisateur();
                o[1] = utilisateur.getNom();
                o[2] = utilisateur.getPrenom();
                o[3] = utilisateur.getMail();
                o[4] = utilisateur.getLogin();
                model.insertRow(index, o);
                model.fireTableDataChanged();
                tableEmlpoyee.revalidate();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur au cours du modification du l'employee",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
