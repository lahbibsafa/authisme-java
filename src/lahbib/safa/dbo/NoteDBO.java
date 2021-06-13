package lahbib.safa.dbo;

import lahbib.safa.models.Database;
import lahbib.safa.models.Groupe;
import lahbib.safa.models.Note;
import lahbib.safa.models.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NoteDBO {
    public static Note insererNote(Note note) {
        String requete = "INSERT INTO note(titre, description, date, idEmployee, idEnfant) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, note.getTitre());
            statement.setString(2, note.getDescription());
            statement.setDate(3, new java.sql.Date(note.getDate().getTime()));
            statement.setInt(4, note.getEmployee().getIdUtilisateur());
            statement.setInt(5, note.getEnfant().getIdEnfant());
            statement.executeUpdate();
            ResultSet res=statement.getGeneratedKeys();
            if(res.next()){
                int last_inserted_id=res.getInt(1);
                note.setIdNote(last_inserted_id);
                return note;
            }
            return null ;
        } catch (SQLException throwables) {
            System.out.println("Erreur au cours d'insertion du note -----> "+throwables.getMessage());
            return null;
        }
    }
    public static Integer modifierNote(Note note) {
        String requete = "UPDATE note SET titre = ?, description = ?, date = ?, idEmployee = ?, idEnfant = ? WHERE idNote = ?";
        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(requete);
            statement.setString(1, note.getTitre());
            statement.setString(2, note.getDescription());
            statement.setDate(3, new java.sql.Date(note.getDate().getTime()));
            statement.setInt(4, note.getEmployee().getIdUtilisateur());
            statement.setInt(5, note.getEnfant().getIdEnfant());
            statement.setInt(6, note.getIdNote());
            return statement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Erreur au cours d'insertion du note -----> "+throwables.getMessage());
            return -1;
        }
    }
    public static List<Note> listerNotes() {
        String requete = "SELECT * FROM note";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            ResultSet curseur = statement.executeQuery();
            List<Note> notes = new ArrayList<Note>();
            while(curseur.next()) {
                Note note = new Note(
                        curseur.getInt("idNote"),
                        curseur.getString("titre"),
                        curseur.getString("description"),
                        new java.util.Date(curseur.getDate("date").getTime()),
                        UtilisateurDBO.selectionerUtilisateur(curseur.getInt("idEmployee")),
                        EnfantDBO.selectionerEnfant(curseur.getInt("idEnfant"))
                );
                notes.add(note);
            }
            return notes;
        } catch (SQLException throwables) {
            System.out.println("ERRUR LISTING NOTES ====> "+ throwables.getMessage());
            return null;
        }
    }
    public static Integer supprimerNote(Integer idNote) {
        String requte = "DELETE FROM note WHERE idNote = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requte);
            statement.setInt(1, idNote);
            return statement.executeUpdate();
        } catch (SQLException throwables){
            System.out.print("Erreur au cours du suppression du note -----> "+throwables.getMessage());
            return -1;
        }
    }

    public static Note selectNote(int idNote) {
        String requete = "SELECT * FROM note WHERE idNote = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setInt(1, idNote);
            ResultSet curseur = statement.executeQuery();
            curseur.next();
            Note note = new Note(
                    curseur.getInt("idNote"),
                    curseur.getString("titre"),
                    curseur.getString("description"),
                    new java.util.Date(curseur.getDate("date").getTime()),
                    UtilisateurDBO.selectionerUtilisateur(curseur.getInt("idEmployee")),
                    EnfantDBO.selectionerEnfant(curseur.getInt("idEnfant"))
            );
            return note;
        } catch (SQLException throwables) {
            return null;
        }
    }

    public static List<Note> filtrerParEnfant(Integer idEnfant) {
        String requete = "SELECT * FROM note WHERE idEnfant = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setInt(1, idEnfant);
            ResultSet curseur = statement.executeQuery();
            List<Note> notes = new ArrayList<Note>();
            while(curseur.next()) {
                Note note = new Note(
                        curseur.getInt("idNote"),
                        curseur.getString("titre"),
                        curseur.getString("description"),
                        new java.util.Date(curseur.getDate("date").getTime()),
                        UtilisateurDBO.selectionerUtilisateur(curseur.getInt("idEmployee")),
                        EnfantDBO.selectionerEnfant(curseur.getInt("idEnfant"))
                );
                notes.add(note);
            }
            return notes;
        } catch (SQLException throwables) {
            System.out.println("ERRUR LISTING NOTES ====> "+ throwables.getMessage());
            return null;
        }
    }
}
