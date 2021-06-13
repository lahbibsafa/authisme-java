package lahbib.safa.panels;

import lahbib.safa.dbo.EnfantDBO;
import lahbib.safa.dbo.GroupeDBO;
import lahbib.safa.dbo.NoteDBO;
import lahbib.safa.models.Enfant;
import lahbib.safa.models.Groupe;
import lahbib.safa.models.Note;
import lahbib.safa.models.Session;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MesEnfant extends JPanel {
    List<Enfant> listEnfants;
    JTable tableEnfant;
    DefaultTableModel enfantModel;
    String[] columnsEnfant = {
            "ID",
            "Nom",
            "Prenom",
            "Date Naissance",
            "Addresse",
            "Telephone"
    };
    JPanel panelNote;
    DateFormat dateFormat;

    public MesEnfant(){
        tableEnfant = new JTable();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        enfantModel = new DefaultTableModel();
        enfantModel.setColumnIdentifiers(columnsEnfant);
        listEnfants = EnfantDBO.filtrerMesEnfant(Session.getUser().getIdUtilisateur());

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

        JScrollPane scrollPaneEnfant = new JScrollPane(tableEnfant);
        this.add(scrollPaneEnfant);
        panelNote = new JPanel();
        panelNote.setLayout(new BoxLayout(panelNote, BoxLayout.PAGE_AXIS));

        panelNote.add(new JLabel("Titre"));
        JTextField titre = new JTextField(20);
        panelNote.add(titre);

        panelNote.add(new JLabel("Date Note"));
        UtilDateModel modelDate = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(modelDate);
        JDatePickerImpl dateInput = new JDatePickerImpl(datePanel);
        panelNote.add(dateInput);
        panelNote.add(new JLabel("Description"));
        JTextArea description = new JTextArea(20,20);
        panelNote.add(description);
        JButton btnAjout = new JButton("Ajouter Note");
        panelNote.add(btnAjout);
        btnAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Integer idEnfant = (Integer) tableEnfant.getValueAt(
                        tableEnfant.getSelectedRow(),
                        0
                );
                Note note = new Note(
                        null,
                        titre.getText(),
                        description.getText(),
                        modelDate.getValue(),
                        Session.getUser(),
                        EnfantDBO.selectionerEnfant(idEnfant)
                );
                note = NoteDBO.insererNote(note);
                if(note != null) {
                    JOptionPane.showMessageDialog(null,
                            "Note ajouté",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    titre.setText("");
                    description.setText("");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Erreur au cours d'ajout du note",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.add(panelNote);
    }
}
