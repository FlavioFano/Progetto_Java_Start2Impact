package application.entity;

public class Trip {
    private Integer id;
    private String data;
    private String durata;
    private String partenza;
    private String arrivo;
    private String disponibile;


//    ID;Data;Durata (ore);Partenza;Arrivo;Disponibile;


    public Trip() {
    }

    public Trip(Integer id, String data, String durata, String partenza, String arrivo, String disponibile) {
        this.id = id;
        this.data = data;
        this.durata = durata;
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.disponibile = disponibile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getArrivo() {
        return arrivo;
    }

    public void setArrivo(String arrivo) {
        this.arrivo = arrivo;
    }

    public String getDisponibile() {
        return disponibile;
    }

    public void setDisponibile(String disponibile) {
        this.disponibile = disponibile;
    }

    @Override
    public String toString() {
        return  id + "-" + data + "-" + durata + "-" + partenza + "-" + arrivo + "-" + disponibile;
    }
}
