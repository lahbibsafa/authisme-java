package lahbib.safa.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormEmployee extends JPanel {
    JButton btnAjout, btnModifier, btnSupprimer, btnAnnuler;
    Boolean modeEdit;
    JLabel nomLabel, prenomLabel, mailLabel, passwordLable, loginLabel;
    JTextField nomInput, prenomInput, mailInput, loginInput, passwordInput;
    JPanel btnPanelAjout, btnPanelEdit;
    public FormEmployee(ActionListener event){
        this.modeEdit = false;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.btnPanelAjout  = new JPanel();
        this.btnPanelEdit  = new JPanel();
        this.btnAjout = new JButton("Ajouter Employee");
        this.btnAjout.addActionListener(event);
        this.btnModifier = new JButton("Modifier Employee");
        this.btnModifier.addActionListener(event);
        this.btnSupprimer = new JButton("Supprimer");
        this.btnSupprimer.addActionListener(event);
        this.btnAnnuler = new JButton("Annuler");
        this.btnAnnuler.addActionListener(event);
        this.initComponent();
    }
    public void initComponent(){

        nomLabel = new JLabel("Nom");
        prenomLabel = new JLabel("Prenom");
        mailLabel = new JLabel("E-Mail");
        loginLabel = new JLabel("login");
        passwordLable = new JLabel("Mot de passe");


        nomInput = new JTextField(20);
        prenomInput = new JTextField(20);
        mailInput = new JTextField(20);
        loginInput = new JTextField(20);
        passwordInput = new JTextField(20);

        Font bigFont = nomInput.getFont().deriveFont(Font.PLAIN, 18f);
        nomInput.setFont(bigFont);
        prenomInput.setFont(bigFont);
        mailInput.setFont(bigFont);
        loginInput.setFont(bigFont);
        passwordInput.setFont(bigFont);


        JPanel nomPanel = new JPanel();
        nomPanel.add(nomLabel);
        nomPanel.add(nomInput);

        JPanel prenomPanel = new JPanel();
        prenomPanel.add(prenomLabel);
        prenomPanel.add(prenomInput);

        JPanel mailPanel = new JPanel();
        mailPanel.add(mailLabel);
        mailPanel.add(mailInput);

        JPanel loginPanel = new JPanel();
        loginPanel.add(loginLabel);
        loginPanel.add(loginInput);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLable);
        passwordPanel.add(passwordInput);



        btnPanelAjout.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnPanelAjout.add(btnAjout);

        btnPanelEdit.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnPanelEdit.add(btnAnnuler);
        btnPanelEdit.add(btnSupprimer);
        btnPanelEdit.add(btnModifier);
        btnPanelEdit.setVisible(false);

        this.add(nomPanel);
        this.add(prenomPanel);
        this.add(mailPanel);
        this.add(loginPanel);
        this.add(passwordPanel);

        this.add(btnPanelAjout);
        this.add(btnPanelEdit);

    }

    public void setModeEdit(Boolean edit) {
        this.modeEdit = edit;
        if (edit) {
            this.btnPanelAjout.setVisible(false);
            this.btnPanelEdit.setVisible(true);
        } else {
            this.btnPanelAjout.setVisible(true);
            this.btnPanelEdit.setVisible(false);
        }
    }
    public String getNom() {
        return this.nomInput.getText();
    }
    public String getPrenom() {
        return this.prenomInput.getText();
    }
    public String getMail() {
        return this.mailInput.getText();
    }
    public String getLogin() {
        return this.loginInput.getText();
    }
    public String getPassword() {
        return this.passwordInput.getText();
    }

    public void setNom(String nom) {
        this.nomInput.setText(nom);
    }
    public void setPrenom(String prenom) {
        this.prenomInput.setText(prenom);
    }
    public void setMail(String mail) {
        this.mailInput.setText(mail);
    }
    public void setLogin(String login) {
        this.loginInput.setText(login);
    }
    public void setPassword(String password) {
        this.passwordInput.setText(password);
    }
}
