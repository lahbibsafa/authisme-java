package lahbib.safa.panels;

import keeptoo.KGradientPanel;
import mdlaf.utils.MaterialColors;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginPanel extends KGradientPanel {
    JLabel title, usernameLabel, passwordLabel;
    JTextField passwordInput, usernameInput;
    JButton loginBtn, resetBtn;
    public LoginPanel(ActionListener eventListner){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
         title = new JLabel("Authentication");
         title.setOpaque(false);
         title.setForeground(MaterialColors.WHITE);
         usernameLabel = new JLabel("Username");
        usernameLabel.setOpaque(false);
        usernameLabel.setForeground(MaterialColors.WHITE);
         passwordLabel = new JLabel("Password");
        passwordLabel.setOpaque(false);
        passwordLabel.setForeground(MaterialColors.WHITE);

         usernameInput = new JTextField(20);
         passwordInput = new JTextField(20);
         Font bigFont = usernameInput.getFont().deriveFont(Font.PLAIN, 18f);
         usernameInput.setFont(bigFont);
         usernameInput.setOpaque(false);
         usernameInput.setForeground(MaterialColors.WHITE);
         passwordInput.setFont(bigFont);
         passwordInput.setOpaque(false);
         passwordInput.setForeground(MaterialColors.WHITE);

         loginBtn = new JButton("Login");
         resetBtn = new JButton("Reset");
         loginBtn.addActionListener(eventListner);
         resetBtn.addActionListener(eventListner);
         JPanel titlePanel = new JPanel();
         titlePanel.add(title);
         titlePanel.setOpaque(false);
         JPanel formPanel = new JPanel();
         formPanel.setOpaque(false);
         JPanel usernamePanel = new JPanel();
         usernamePanel.setOpaque(false);
         usernamePanel.add(usernameLabel);
         usernamePanel.add(usernameInput);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setOpaque(false);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordInput);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));
        formPanel.add(usernamePanel);
        formPanel.add(passwordPanel);

        JPanel loginPanel = new JPanel();
        loginPanel.setOpaque(false);
        loginPanel.add(loginBtn);
        loginPanel.add(resetBtn);
         this.add(titlePanel);
         this.add(formPanel);
         this.add(loginPanel);
         this.setOpaque(false);
    }
    public String getUsername(){
        return this.usernameInput.getText();
    }
    public String getPassword(){
        return this.passwordInput.getText();
    }
}
