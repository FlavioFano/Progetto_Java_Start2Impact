package application.entity;

public class User {
    private Integer userId;
    private String Nome;
    private String Cognome;
    private String DataDiNascita;
    private String Indirizzo;
    private String IdDocumento;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public String getDataDiNascita() {
        return DataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        DataDiNascita = dataDiNascita;
    }

    public String getIndirizzo() {
        return Indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }

    public String getIdDocumento() {
        return IdDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        IdDocumento = idDocumento;
    }

    @Override
    public String toString() {
        return userId + "-" + Nome + "-" + Cognome + "-" + DataDiNascita + "-" + Indirizzo + "-" + IdDocumento;
    }
}
