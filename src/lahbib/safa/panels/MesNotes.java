package lahbib.safa.panels;


import lahbib.safa.dbo.*;
import lahbib.safa.models.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MesNotes extends JPanel {
    JComboBox<Enfant> enfantBox;
    List<Enfant> listEnfant;
    List<Note> listNote;
    JTable tableNote;
    JPanel descriptionPanel;

    DefaultTableModel modeltable;
    String[] columns = {
            "ID",
            "Nom",
            "Prenom",
            "Titre",
            "Date",
            "Employee"
    };
    DateFormat dateFormat;

    public MesNotes(){
        JTextArea description = new JTextArea(20,20);
        JButton closeBtn = new JButton("Fermer");
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.PAGE_AXIS));
        enfantBox = new JComboBox<Enfant>();
        listEnfant = EnfantDBO.filtrerMesEnfant(Session.getUser().getIdUtilisateur());
        for(Enfant enfant: listEnfant) {
            enfantBox.addItem(enfant);
        }
        JPanel panelBox = new JPanel();
        panelBox.setLayout(new BoxLayout(panelBox, BoxLayout.Y_AXIS));
        panelBox.add(new JLabel("Enfant"));
        panelBox.add(enfantBox);
        enfantBox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer index = enfantBox.getSelectedIndex();
                if (index > -1) {
                    listNote = NoteDBO.filtrerParEnfant(listEnfant.get(index).getIdEnfant());
                    modeltable = new DefaultTableModel();
                    modeltable.setColumnIdentifiers(columns);
                    for(Note note: listNote) {
                        modeltable.addRow(new Object[]{
                                note.getIdNote(),
                                note.getEnfant().getNom(),
                                note.getEnfant().getPrenom(),
                                note.getTitre(),
                                dateFormat.format(note.getDate()),
                                note.getEmployee().toString()
                        });
                    }
                    tableNote.setModel(modeltable);
                    tableNote.revalidate();
                }
            }
        });
        this.add(panelBox);
        tableNote =new JTable();
        modeltable =new DefaultTableModel();

        modeltable.setColumnIdentifiers(columns);

        tableNote.setModel(modeltable);
        tableNote.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                Integer index = tableNote.getSelectedRow();
                if (index > -1) {
                    Note note = listNote.get(index);
                    descriptionPanel.setVisible(true);
                    description.setText(note.getDescription());
                }
            }
        });


        JScrollPane sp = new JScrollPane(tableNote);
        this.add(sp);

        descriptionPanel.add(new JLabel("Description"));
        descriptionPanel.add(description);
        descriptionPanel.add(closeBtn);
        descriptionPanel.setVisible(false);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                descriptionPanel.setVisible(false);
                description.setText("");
            }
        });
        this.add(descriptionPanel);
    }
}
