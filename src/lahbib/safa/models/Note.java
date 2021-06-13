package lahbib.safa.models;

import java.util.Date;

public class Note {
    private Integer idNote;
    private String titre, description;
    private Date date;
    private Utilisateur employee;
    private Enfant enfant;

    public Note(Integer idNote, String titre, String description, Date date, Utilisateur employee, Enfant enfant) {
        this.idNote = idNote;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.employee = employee;
        this.enfant = enfant;
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getEmployee() {
        return employee;
    }

    public void setEmployee(Utilisateur employee) {
        this.employee = employee;
    }

    public Enfant getEnfant() {
        return enfant;
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }
}
