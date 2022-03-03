package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Sluzi za unos podataka o zupanijama
 * Nasljeduje klasu ImenovaniEntitet
 */

public class Zupanija extends ImenovaniEntitet implements Serializable {

    private Integer brojStanovnika;
    private Integer brojZarazenih;




    /**
     * Konstruktor koji prima 3 ulazna parametra
     * @param naziv objekt nasljeđen od klase "ImenovaniEntitet"
     * @param brojStanovnika objekt klase Integer u koji se pohranjuje broj stanovnika zupanije
     * @param brojZarazenih objekt klase Integer u koji se pohranjuje broj stanovnika zupanije
     */
    public Zupanija(Long id, String naziv, Integer brojStanovnika, Integer brojZarazenih) {
        super(id, naziv);
        this.brojStanovnika = brojStanovnika;
        this.brojZarazenih = brojZarazenih;
    }

    public Integer getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(Integer brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Integer getBrojZarazenih() {
        return brojZarazenih;
    }

    public void setBrojZarazenih(Integer brojZarazenih) {
        this.brojZarazenih = brojZarazenih;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zupanija)) return false;
        Zupanija zupanija = (Zupanija) o;
        return Objects.equals(brojStanovnika, zupanija.brojStanovnika) && Objects.equals(brojZarazenih, zupanija.brojZarazenih);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brojStanovnika, brojZarazenih);
    }

    @Override
    public String toString() {
        return naziv;
    }
}
