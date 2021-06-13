package lahbib.safa.dbo;

import lahbib.safa.models.Database;
import lahbib.safa.models.Groupe;
import lahbib.safa.models.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupeDBO {
    public static Groupe insererGroupe(Groupe groupe) {
        String requete = "INSERT INTO groupe(nom, idEmployee) VALUES (?, ?)";
        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, groupe.getNom());
            statement.setInt(2, groupe.getEmployee().getIdUtilisateur());
            statement.executeUpdate();
            ResultSet res=statement.getGeneratedKeys();
            if(res.next()){
                int last_inserted_id=res.getInt(1);
                groupe.setIdGroupe(last_inserted_id);
                return groupe;
            }
            return null ;
        } catch (SQLException throwables) {
            System.out.println("Erreur au cours d'insertion du groupe -----> "+throwables.getMessage());
            return null;
        }
    }
    public static Integer modifierGroupe(Groupe groupe) {
        String requete = "UPDATE groupe SET nom = ?, idEmployee = ? WHERE idGroupe = ?";
        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(requete);
            statement.setString(1, groupe.getNom());
            statement.setInt(2, groupe.getEmployee().getIdUtilisateur());
            statement.setInt(3, groupe.getIdGroupe());
            return statement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Erreur au cours d'insertion du groupe -----> "+throwables.getMessage());
            return -1;
        }
    }
    public static List<Groupe> listerGroupes() {
        String requete = "SELECT * FROM groupe";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            ResultSet curseur = statement.executeQuery();
            List<Groupe> groupes = new ArrayList<Groupe>();
            while(curseur.next()) {
                Groupe groupe = new Groupe(
                        curseur.getInt("idGroupe"),
                        curseur.getString("nom"),
                        UtilisateurDBO.selectionerUtilisateur(curseur.getInt("idEmployee"))
                );
                groupes.add(groupe);
            }
            return groupes;
        } catch (SQLException throwables) {
            System.out.println("ERRUR LISTING GROUPES ====> "+ throwables.getMessage());
            return null;
        }
    }
    public static Integer supprimerGroupe(Integer idGroupe) {
        String requte = "DELETE FROM groupe WHERE idGroupe = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requte);
            statement.setInt(1, idGroupe);
            return statement.executeUpdate();
        } catch (SQLException throwables){
            System.out.print("Erreur au cours du suppression du groupe -----> "+throwables.getMessage());
            return -1;
        }
    }

    public static Groupe selectGroupe(int idGroupe) {
        String requete = "SELECT * FROM groupe WHERE idGroupe = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setInt(1, idGroupe);
            ResultSet curseur = statement.executeQuery();
            curseur.next();
            Groupe groupe = new Groupe(
                    curseur.getInt("idGroupe"),
                    curseur.getString("nom"),
                    UtilisateurDBO.selectionerUtilisateur(curseur.getInt("idEmployee"))
            );
            return groupe;
        } catch (SQLException throwables) {
            return null;
        }
    }

    public static List<Groupe> filtrerParEmployee(Integer idUtilisateur) {
        String requete = "SELECT * FROM groupe WHERE idEmployee = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setInt(1, idUtilisateur);
            ResultSet curseur = statement.executeQuery();
            List<Groupe> groupes = new ArrayList<Groupe>();
            while(curseur.next()) {
                Groupe groupe = new Groupe(
                        curseur.getInt("idGroupe"),
                        curseur.getString("nom"),
                        UtilisateurDBO.selectionerUtilisateur(curseur.getInt("idEmployee"))
                );
                groupes.add(groupe);
            }
            return groupes;
        } catch (SQLException throwables) {
            System.out.println("ERRUR LISTING GROUPES ====> "+ throwables.getMessage());
            return null;
        }
    }
}
