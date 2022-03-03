package model;

import java.io.Serializable;
import java.util.Set;

/**
 * Sluzi za unos podataka o virusima.
 * Nasljeduje klasu Bolest.
 * Implementira sucelje Zarazno.
 */

public class Virus extends Bolest implements Zarazno, Serializable {

    /**
     * Konstruktor koji prima 2 ulazna parametra
     * @param naziv objekt nasljeđen od klase Bolest u koji se pohranjuje naziv virusa
     * @param simptomi polje nasljeđeno od klase Bolest u koje se pohranjuju odabrani simptomi virusa
     */
    public Virus(Long id, String naziv, Set<Simptom> simptomi, Integer brojSimptoma) {
        super(id, naziv, simptomi, brojSimptoma);
    }

    /**
     * Nadjacava metodu sucelja Zarazno
     * @param osoba objektu klase osoba postavlja da je zarazena tim virusom u kojem se metoda nalazi
     */
    @Override
    public void prelazakZarazeNaOsobu(Osoba osoba) {
        osoba.setZarazenBolescu(this);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return naziv;
    }
}
