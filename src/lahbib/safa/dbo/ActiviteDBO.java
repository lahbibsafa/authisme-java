package lahbib.safa.dbo;

import lahbib.safa.models.Activite;
import lahbib.safa.models.Database;
import lahbib.safa.models.Evenement;
import lahbib.safa.models.Groupe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActiviteDBO {
    public ActiviteDBO() {
    }
    public static Activite insereActivite(Activite activite){
        String request ="INSERT INTO activite(designation,description) VALUES(?,?)";
        try{
            PreparedStatement statement = Database.getConnection().prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,activite.getDesignation());
            statement.setString(2,activite.getDescription());
            statement.executeUpdate();
            ResultSet res=statement.getGeneratedKeys();
            if(res.next()){
                int last_inserted_id=res.getInt(1);
                activite.setIdActivite(last_inserted_id);
                return activite;
            }
            return null ;
        } catch (SQLException throwables) {
            System.out.println("erreur d insertion d activite ===>"+throwables.getMessage());
            return  null ;
        }
    }

    public static Integer modifierActivite(Activite activite){
        String request ="UPDATE activite SET designation = ?, description = ? WHERE idActivite = ?";
        try{
            PreparedStatement statement = Database.getConnection().prepareStatement(request);
            statement.setString(1,activite.getDesignation());
            statement.setString(2,activite.getDescription());
            statement.setInt(3,activite.getIdActivite());
            return statement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("erreur d insertion d activite ===>"+throwables.getMessage());
            return  null ;
        }
    }

    public static List<Activite> listerActivite(){
            String request ="SELECT * FROM activite";
            try{
                PreparedStatement statement=Database.getConnection().prepareStatement((request));
                ResultSet cursor = statement.executeQuery();
                List<Activite> activites = new ArrayList<>();
                while(cursor.next()){
                    Activite activite = new Activite(
                            cursor.getInt("idActivite"),
                            cursor.getString("designation"),
                            cursor.getString("description")
                    );
                    activites.add(activite);
                }
                return activites;
            } catch (SQLException throwables) {
                System.out.println("Erreur list activites )===> "+throwables.getMessage());
                return null;
            }
    }

    public static Integer supprimerActivite(Integer idActivite) {
        String requete = "DELETE FROM activite WHERE idActivite = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setInt(1, idActivite);
            Integer resultat = statement.executeUpdate();
            return resultat;
        } catch (SQLException throwables) {
            System.out.println("Erreur suppression :=====>  "+throwables.getMessage());
            return -1;
        }
    }

    public static Activite selectionerActivite(int idActivite) {
        String requete = "SELECT * FROM activite WHERE idActivite = ?";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(requete);
            statement.setInt(1, idActivite);
            ResultSet curseur = statement.executeQuery();
            curseur.next();
            Activite activite = new Activite(
                    curseur.getInt("idActivite"),
                    curseur.getString("designation"),
                    curseur.getString("description")
            );
            return activite;
        } catch (SQLException throwables) {
            return null;
        }
    }
}

