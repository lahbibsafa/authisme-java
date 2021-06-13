package lahbib.safa.dbo;

import jdk.jshell.execution.Util;
import lahbib.safa.models.Database;
import lahbib.safa.models.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDBO {
    public UtilisateurDBO() {
    }

    public static Utilisateur insererUtilisateur(Utilisateur utilisateur) {
        String requete = "INSERT INTO utilisateur(nom, prenom, mail, role, login, password) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete,  Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getPrenom());
            statement.setString(3, utilisateur.getMail());
            statement.setString(4, utilisateur.getRole());
            statement.setString(5, utilisateur.getLogin());
            statement.setString(6, utilisateur.getPassword());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next())
            {
                int last_inserted_id = rs.getInt(1);
                utilisateur.setIdUtilisateur(last_inserted_id);
                return utilisateur;
            }
            return null;
        } catch (SQLException throwables) {
            System.out.println("Erreur  insertion ===> "+throwables.getMessage());
            return null;
        }
    }


    public static Integer modifierUtilisateur(Utilisateur utilisateur) {
        String requete = "UPDATE utilisateur SET nom = ? , prenom = ? , mail = ?, role = ?, login = ?, password = ? WHERE idUtilisateur = ? ";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getPrenom());
            statement.setString(3, utilisateur.getMail());
            statement.setString(4, utilisateur.getRole());
            statement.setString(5, utilisateur.getLogin());
            statement.setString(6, utilisateur.getPassword());
            statement.setInt(7, utilisateur.getIdUtilisateur());
            return statement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Erreur  insertion ===> "+throwables.getMessage());
            return -1;
        }
    }

    public static Utilisateur selectionerUtilisateur(Integer idUtilisateur) {
        String requete = "SELECT * FROM utilisateur WHERE idUtilisateur = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setInt(1, idUtilisateur);
            ResultSet curseur = statement.executeQuery();
            curseur.next();
            Utilisateur utilisateur = new Utilisateur(
                    curseur.getInt("idUtilisateur"),
                    curseur.getString("nom"),
                    curseur.getString("prenom"),
                    curseur.getString("mail"),
                    curseur.getString("role"),
                    curseur.getString("login"),
                    curseur.getString("password")
            );
            return utilisateur;
        } catch (SQLException throwables) {
            return null;
        }
    }

    public static Integer supprimerUtilisateur(Integer idUtilisateur) {
        String requete = "DELETE FROM utilisateur WHERE idUtilisateur = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setInt(1, idUtilisateur);
            Integer resultat = statement.executeUpdate();
            return resultat;
        } catch (SQLException throwables) {
            System.out.println("Erreur suppression :=====>  "+throwables.getMessage());
            return -1;
        }
    }

    public static Utilisateur authentifier(String login, String password) {
        String requete = "SELECT * FROM utilisateur WHERE login = ? AND password = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);

            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet curseur = statement.executeQuery();
            curseur.next();
            Utilisateur utilisateur = new Utilisateur(
                    curseur.getInt("idUtilisateur"),
                    curseur.getString("nom"),
                    curseur.getString("prenom"),
                    curseur.getString("mail"),
                    curseur.getString("role"),
                    curseur.getString("login"),
                    curseur.getString("password")
            );
            return utilisateur;
        } catch (SQLException throwables) {
            return null;
        }
    }

    public static List<Utilisateur> listerEmployee(){
        String request = "SELECT * FROM utilisateur WHERE role = 'employee'";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(request);
            ResultSet curseur = statement.executeQuery();
            List<Utilisateur> employees = new ArrayList<Utilisateur>();
            while(curseur.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        curseur.getInt("idUtilisateur"),
                        curseur.getString("nom"),
                        curseur.getString("prenom"),
                        curseur.getString("mail"),
                        curseur.getString("role"),
                        curseur.getString("login"),
                        curseur.getString("password")
                );
                employees.add(utilisateur);
            }
            return employees;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
