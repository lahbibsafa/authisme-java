package lahbib.safa.models;

import java.util.Date;

public class Evenement {
    private Integer idEvenement;
    private Date date;
    private Activite activte;
    private Groupe groupe;

    public Evenement(Integer idEvenement, Date date, Activite activte, Groupe groupe) {
        this.idEvenement = idEvenement;
        this.date = date;
        this.activte = activte;
        this.groupe = groupe;
    }

    public Integer getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Integer idEvenement) {
        this.idEvenement = idEvenement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Activite getActivte() {
        return activte;
    }

    public void setActivte(Activite activte) {
        this.activte = activte;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
}
