package lahbib.safa.dbo;

import lahbib.safa.models.Database;
import lahbib.safa.models.Enfant;
import lahbib.safa.models.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnfantDBO {

    public static Enfant insererEnfant(Enfant enfant) {
        String requete = "INSERT INTO enfant(nom, prenom, naissance, contact, addresse, idGroupe) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete,  Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, enfant.getNom());
            statement.setString(2, enfant.getPrenom());
            statement.setDate(3,  new java.sql.Date(enfant.getNaissance().getTime()));
            statement.setString(4, enfant.getContact());
            statement.setString(5, enfant.getAddresse());
            statement.setInt(6, enfant.getGroupe().getIdGroupe());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next())
            {
                int last_inserted_id = rs.getInt(1);
                enfant.setIdEnfant(last_inserted_id);
                return enfant;
            }
            return null;
        } catch (SQLException throwables) {
            System.out.println("Erreur  insertion ===> "+throwables.getMessage());
            return null;
        }
    }

    public static Integer modifierEnfant(Enfant enfant) {
        String requete = "UPDATE enfant SET nom = ? , prenom = ? , naissance = ?, contact = ?, addresse = ?, idGroupe = ? WHERE idEnfant = ? ";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setString(1, enfant.getNom());
            statement.setString(2, enfant.getPrenom());
            statement.setDate(3, new java.sql.Date(enfant.getNaissance().getTime()));
            statement.setString(4, enfant.getContact());
            statement.setString(5, enfant.getAddresse());
            statement.setInt(6, enfant.getGroupe().getIdGroupe());
            statement.setInt(7, enfant.getIdEnfant());
            return statement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Erreur  insertion ===> "+throwables.getMessage());
            return -1;
        }
    }

    public static Integer supprimerEnfant(Integer idEnfant) {
        String requete = "DELETE FROM enfant WHERE idEnfant = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setInt(1, idEnfant);
            Integer resultat = statement.executeUpdate();
            return resultat;
        } catch (SQLException throwables) {
            System.out.println("Erreur suppression :=====>  "+throwables.getMessage());
            return -1;
        }
    }

    public static List<Enfant> listerEnfant(){
        String request = "SELECT * FROM enfant";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(request);
            ResultSet curseur = statement.executeQuery();
            List<Enfant> enfants = new ArrayList<Enfant>();
            while(curseur.next()) {
                Enfant enfant = new Enfant(
                        curseur.getInt("idEnfant"),
                        new java.util.Date(curseur.getDate("naissance").getTime()),
                        curseur.getString("nom"),
                        curseur.getString("prenom"),
                        curseur.getString("contact"),
                        curseur.getString("addresse"),
                        GroupeDBO.selectGroupe(curseur.getInt("idGroupe"))
                );
                enfants.add(enfant);
            }
            return enfants;
        } catch (SQLException throwables) {
            System.out.println("Erreur listing enfants : =====> "+throwables.getMessage());
            return null;
        }
    }

    public static Enfant selectionerEnfant(Integer idEnfant) {
        String requete = "SELECT * FROM enfant WHERE idEnfant = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setInt(1, idEnfant);
            ResultSet curseur = statement.executeQuery();
            if(curseur.next())
            {
                Enfant enfant = new Enfant(
                        curseur.getInt("idEnfant"),
                        new java.util.Date(curseur.getDate("naissance").getTime()),
                        curseur.getString("nom"),
                        curseur.getString("prenom"),
                        curseur.getString("contact"),
                        curseur.getString("addresse"),
                        GroupeDBO.selectGroupe(curseur.getInt("idGroupe"))
                );
                return enfant;
            }
            return null;
        } catch (SQLException throwables) {
            System.out.println("Erreur  insertion ===> "+throwables.getMessage());
            return null;
        }
    }


    public static List<Enfant> filtrerParGroupe(Integer idGroupe) {
        String request = "SELECT * FROM enfant WHERE idGroupe = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(request);
            statement.setInt(1, idGroupe);
            ResultSet curseur = statement.executeQuery();
            List<Enfant> enfants = new ArrayList<Enfant>();
            while(curseur.next()) {
                Enfant enfant = new Enfant(
                        curseur.getInt("idEnfant"),
                        new java.util.Date(curseur.getDate("naissance").getTime()),
                        curseur.getString("nom"),
                        curseur.getString("prenom"),
                        curseur.getString("contact"),
                        curseur.getString("addresse"),
                        GroupeDBO.selectGroupe(curseur.getInt("idGroupe"))
                );
                enfants.add(enfant);
            }
            return enfants;
        } catch (SQLException throwables) {
            System.out.println("Erreur listing enfants : =====> "+throwables.getMessage());
            return null;
        }
    }

    public static List<Enfant> filtrerMesEnfant(Integer idUtilisateur) {
        String request = "SELECT * FROM enfant WHERE idGroupe in (SELECT DISTINCT idGroupe FROM groupe WHERE idEmployee = ?)";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(request);
            statement.setInt(1, idUtilisateur);
            ResultSet curseur = statement.executeQuery();
            List<Enfant> enfants = new ArrayList<Enfant>();
            while(curseur.next()) {
                Enfant enfant = new Enfant(
                        curseur.getInt("idEnfant"),
                        new java.util.Date(curseur.getDate("naissance").getTime()),
                        curseur.getString("nom"),
                        curseur.getString("prenom"),
                        curseur.getString("contact"),
                        curseur.getString("addresse"),
                        GroupeDBO.selectGroupe(curseur.getInt("idGroupe"))
                );
                enfants.add(enfant);
            }
            return enfants;
        } catch (SQLException throwables) {
            System.out.println("Erreur listing enfants : =====> "+throwables.getMessage());
            return null;
        }
    }
}
