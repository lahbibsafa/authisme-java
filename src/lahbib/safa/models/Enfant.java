package lahbib.safa.models;

import java.util.Date;

public class Enfant {
    private Integer idEnfant;
    private Date naissance;
    private String nom, prenom, contact, addresse;
    private Groupe groupe;

    public Enfant(Integer idEnfant, Date naissance, String nom, String prenom, String contact, String addresse, Groupe groupe) {
        this.idEnfant = idEnfant;
        this.naissance = naissance;
        this.nom = nom;
        this.prenom = prenom;
        this.contact = contact;
        this.addresse = addresse;
        this.groupe = groupe;
    }

    public Integer getIdEnfant() {
        return idEnfant;
    }

    public void setIdEnfant(Integer idEnfant) {
        this.idEnfant = idEnfant;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date date) {
        this.naissance = date;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    public String toString(){
        return this.nom + " " + this.prenom;
    }
}
