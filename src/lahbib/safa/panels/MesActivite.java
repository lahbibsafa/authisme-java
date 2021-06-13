package lahbib.safa.panels;

import lahbib.safa.dbo.EvenementDBO;
import lahbib.safa.models.Evenement;
import lahbib.safa.models.Session;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MesActivite extends JPanel {
    JPanel panelTop, panelBottom;
    JScrollPane scrollPane;
    JTable table;
    UtilDateModel modelDebut, modelFin;
    List<Evenement> evenementList;
    String[] columns = {
            "ID",
            "Titre",
            "Groupe"
    };
    DefaultTableModel model;
    DateFormat dateFormat;
    public MesActivite(){
        table = new JTable();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        evenementList = EvenementDBO.filtrerEvenementParEmployee(Session.getUser().getIdUtilisateur());
        panelTop = new JPanel(new FlowLayout());
        panelTop.add(new JLabel("Date Début"));
        modelDebut = new UtilDateModel();
        JDatePanelImpl dateDebutPanel = new JDatePanelImpl(modelDebut);
        JDatePickerImpl dateDebutInput = new JDatePickerImpl(dateDebutPanel);
        panelTop.add(dateDebutInput);

        panelTop.add(new JLabel("Date Fin"));
        modelFin = new UtilDateModel();
        JDatePanelImpl dateFinPanel = new JDatePanelImpl(modelFin);
        JDatePickerImpl dateFinInput = new JDatePickerImpl(dateFinPanel);
        panelTop.add(dateFinInput);

        JButton btnFiltrer = new JButton("Filtrer");
        btnFiltrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                evenementList = EvenementDBO.filtrerEvenementParEmployeeEtDate(Session.getUser().getIdUtilisateur(), modelDebut.getValue(), modelFin.getValue());
                model = new DefaultTableModel();
                model.setColumnIdentifiers(columns);

                for(Evenement evenement : evenementList) {
                    model.addRow(new Object[]{
                            evenement.getIdEvenement(),
                            evenement.getActivte().getDesignation(),
                            dateFormat.format(evenement.getDate()),
                            evenement.getGroupe().getNom()
                    });
                }
                table.setModel(model);
                table.revalidate();
            }
        });
        panelTop.add(btnFiltrer);


        panelBottom = new JPanel();

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        for(Evenement evenement : evenementList) {
            model.addRow(new Object[]{
                    evenement.getIdEvenement(),
                    evenement.getActivte().getDesignation(),
                    dateFormat.format(evenement.getDate()),
                    evenement.getGroupe().getNom()
            });
        }
        table.setModel(model);

        scrollPane = new JScrollPane(table);
        panelBottom.add(scrollPane);

        this.add(panelTop);
        this.add(panelBottom);
    }

}
