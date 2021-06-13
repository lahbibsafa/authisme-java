package lahbib.safa.models;

public class Session {
    private static Utilisateur utilisateurActuelle = null;

    public static void setUser(Utilisateur utilisateur){
        Session.utilisateurActuelle = utilisateur;
    }

    public static Utilisateur getUser(){
        return Session.utilisateurActuelle;
    }
}
