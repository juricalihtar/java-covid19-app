package model;

/**
 * Apstraktna klasa koja sluzi za unos naziva.
 */

public abstract class ImenovaniEntitet {

    public Long id;
    public String naziv;


    /**
     * Konstruktor prima 1 parametar
     * @param naziv objekt klase String u koji se pohranjuje naziv
     */
    public ImenovaniEntitet(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;

    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
