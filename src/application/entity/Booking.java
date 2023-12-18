package application.entity;

public class Booking {
    private Integer id;
    private Integer idViaggio;
    private Integer idUtente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdViaggio() {
        return idViaggio;
    }

    public void setIdViaggio(Integer idViaggio) {
        this.idViaggio = idViaggio;
    }

    public Integer getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    @Override
    public String toString() {
        return id +"-"+ idViaggio + "-" + idUtente;
    }
}
