package model;

import enumeracija.VrijednostSimptoma;

import java.util.Objects;

/**
 * Sluzi za unos podataka o simptomima.
 * Nasljeduje apstraktnu klasu "ImenovaniEntitet"
 */

public class Simptom extends ImenovaniEntitet {

    private String vrijednost;



    /**
     * Konstruktor koji prima 2 ulazna parametra
     * @param naziv objekt nasljeđen od klase "ImenovaniEntitet"
     * @param vrijednost objekt klase String u koji se unosi vrijednost simptoma
     */
    public Simptom(Long id, String naziv, String vrijednost) {
        super(id, naziv);
        this.vrijednost = vrijednost;
    }



    public String getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(String vrijednost) {
        this.vrijednost = vrijednost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Simptom)) return false;
        Simptom simptom = (Simptom) o;
        return Objects.equals(naziv, simptom.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }

    @Override
    public String toString() {
        return naziv + " -> " + vrijednost;
    }
}
