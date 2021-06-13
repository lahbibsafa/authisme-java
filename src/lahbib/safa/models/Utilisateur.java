package lahbib.safa.models;

public class Utilisateur {
    private Integer idUtilisateur;
    private String nom, prenom, mail, role, login, password;
    public Utilisateur(Integer idUtilisateur,
                       String nom,
                       String prenom,
                       String mail,
                       String role,
                       String login,
                       String password){
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return this.nom+' '+this.prenom;
    }
    public Boolean equals(Utilisateur utilisateur){
        return this.idUtilisateur == utilisateur.getIdUtilisateur();
    }
}
