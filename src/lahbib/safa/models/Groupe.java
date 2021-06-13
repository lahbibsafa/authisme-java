package lahbib.safa.models;

public class Groupe {
    private Integer idGroupe;
    private String nom;
    private Utilisateur employee;

    public Groupe(Integer idGroupe, String nom, Utilisateur employee) {
        this.idGroupe = idGroupe;
        this.nom = nom;
        this.employee = employee;
    }

    public Integer getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Utilisateur getEmployee() {
        return employee;
    }

    public void setEmployee(Utilisateur employee) {
        this.employee = employee;
    }

    public String toString(){
        return "Groupe: "+this.nom;
    }
}
