package lahbib.safa.dbo;

import lahbib.safa.models.Database;
import lahbib.safa.models.Evenement;
import lahbib.safa.models.Groupe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvenementDBO {
    public static Evenement insererEvenement(Evenement evenement) {
        String requete = "INSERT INTO evenement(date, idActivite, idGroupe) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, new java.sql.Date(evenement.getDate().getTime()));
            statement.setInt(2, evenement.getActivte().getIdActivite());
            statement.setInt(3, evenement.getGroupe().getIdGroupe());
            statement.executeUpdate();
            ResultSet res=statement.getGeneratedKeys();
            if(res.next()){
                int last_inserted_id=res.getInt(1);
                evenement.setIdEvenement(last_inserted_id);
                return evenement;
            }
            return null ;
        } catch (SQLException throwables) {
            System.out.println("Erreur au cours d'insertion D'evenement -----> "+throwables.getMessage());
            return null;
        }
    }
    public static Integer modifierEvenement(Evenement evenement) {
        String requete = "UPDATE evenement SET date = ?, idActivite = ?, idGroupe = ? WHERE idEvenement = ?";
        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(requete);
            statement.setDate(1, new java.sql.Date(evenement.getDate().getTime()));
            statement.setInt(2, evenement.getActivte().getIdActivite());
            statement.setInt(3, evenement.getGroupe().getIdGroupe());
            statement.setInt(4, evenement.getIdEvenement());
            return statement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Erreur au cours d'insertion d'evenement -----> "+throwables.getMessage());
            return -1;
        }
    }
    public static List<Evenement> listerEvenement() {
        String requete = "SELECT * FROM evenement";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            ResultSet curseur = statement.executeQuery();
            List<Evenement> evenements = new ArrayList<Evenement>();
            while(curseur.next()) {
                Evenement evenement = new Evenement(
                        curseur.getInt("idEvenement"),
                        new Date(curseur.getDate("date").getTime()),
                        ActiviteDBO.selectionerActivite(curseur.getInt("idActivite")),
                        GroupeDBO.selectGroupe(curseur.getInt("idGroupe"))
                );
                evenements.add(evenement);
            }
            return evenements;
        } catch (SQLException throwables) {
            System.out.println("ERRUR LISTING GROUPES ====> "+ throwables.getMessage());
            return null;
        }
    }

    public static Integer supprimerEvenement(Integer idEvenement) {
        String requte = "DELETE FROM evenement WHERE idEvenement = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requte);
            statement.setInt(1, idEvenement);
            return statement.executeUpdate();
        } catch (SQLException throwables){
            System.out.print("Erreur au cours du suppression du groupe -----> "+throwables.getMessage());
            return -1;
        }
    }

    public static List<Evenement> filtrerEvenementParEmployee(Integer idUtilisateur) {
        String requte = "SELECT * FROM evenement WHERE idGroupe in (SELECT DISTINCT idGroupe FROM groupe WHERE idEmployee = ?)";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requte);
            statement.setInt(1, idUtilisateur);
            ResultSet curseur = statement.executeQuery();
            List<Evenement> evenements = new ArrayList<Evenement>();
            while(curseur.next()) {
                Evenement evenement = new Evenement(
                        curseur.getInt("idEvenement"),
                        new Date(curseur.getDate("date").getTime()),
                        ActiviteDBO.selectionerActivite(curseur.getInt("idActivite")),
                        GroupeDBO.selectGroupe(curseur.getInt("idGroupe"))
                );
                evenements.add(evenement);
            }
            return evenements;
        } catch (SQLException throwables) {
            System.out.println("ERRUR LISTING GROUPES ====> "+ throwables.getMessage());
            return null;
        }
    }
    public static List<Evenement> filtrerEvenementParEmployeeEtDate(Integer idUtilisateur, Date dateDebut, Date dateFin) {
        String requte = "SELECT * FROM evenement WHERE idGroupe in (SELECT DISTINCT idGroupe FROM groupe WHERE idEmployee = ?) AND date >= ? AND date <= ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requte);
            statement.setInt(1, idUtilisateur);
            statement.setDate(2, new java.sql.Date(dateDebut.getTime()));
            statement.setDate(3, new java.sql.Date(dateFin.getTime()));
            ResultSet curseur = statement.executeQuery();
            List<Evenement> evenements = new ArrayList<Evenement>();
            while(curseur.next()) {
                Evenement evenement = new Evenement(
                        curseur.getInt("idEvenement"),
                        new Date(curseur.getDate("date").getTime()),
                        ActiviteDBO.selectionerActivite(curseur.getInt("idActivite")),
                        GroupeDBO.selectGroupe(curseur.getInt("idGroupe"))
                );
                evenements.add(evenement);
            }
            return evenements;
        } catch (SQLException throwables) {
            System.out.println("ERRUR LISTING GROUPES ====> "+ throwables.getMessage());
            return null;
        }
    }
}
