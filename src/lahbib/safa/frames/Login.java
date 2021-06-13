package lahbib.safa.frames;

import lahbib.safa.dbo.UtilisateurDBO;
import lahbib.safa.models.Session;
import lahbib.safa.models.Utilisateur;
import lahbib.safa.panels.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    LoginPanel panel;
    AdminDashboard adminDashboard;
    EmployeeDashboard employeeDashboard;
    public Login(){
        this.setTitle("Authentication");
        this.initiateComponents();
    }
    public  void initiateComponents(){
        panel = new LoginPanel(this);
        adminDashboard = new AdminDashboard();
        employeeDashboard = new EmployeeDashboard();
        this.add(panel);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedBtn = (JButton)actionEvent.getSource();
        String text = clickedBtn.getText();
        if (text.equals("Login")) {
            String username = this.panel.getUsername();
            String password = this.panel.getPassword();
            Utilisateur utilisateur = UtilisateurDBO.authentifier(username, password);
            if (utilisateur == null) {
                JOptionPane.showMessageDialog(this,
                        "Veuillez verifier votre login/mot de passe",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            } else {
              if (utilisateur.getRole().equals("admin")) {
                  this.setVisible(false);
                  this.adminDashboard.show();
              } else {
                  this.setVisible(false);
                  Session.setUser(utilisateur);
                  this.employeeDashboard.show();
              }
            }
        } else {

        }
    }
}
