package lahbib.safa.models;

public class Activite {
    private Integer idActivite;
    private String designation, description;

    public Activite(Integer idActivite, String designation, String description) {
        this.idActivite = idActivite;
        this.designation = designation;
        this.description = description;
    }

    public Integer getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Integer idActivite) {
        this.idActivite = idActivite;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return this.getDesignation();
    }
}
