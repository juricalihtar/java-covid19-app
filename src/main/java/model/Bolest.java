package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *Sluzi za unos podataka za bolesti.
 *Klasa "Bolest" nasljeduje apstraktnu klasu "ImenovaniEntitet"
 *
 */

public class Bolest extends ImenovaniEntitet implements Serializable {

    private Set<Simptom> simptomi = new HashSet<>();
    private Integer brojSimptoma;




    /**
     *Konstruktor koji prima 2 ulazna parametra
     * @param naziv objekt nasljeđen od klase "ImenovaniEntitet"
     * @param simptomi polje u koje se pohranjuju odabrani simptomi bolesti
     * @param brojSimptoma objekt klase Integer u koji se pohranjuje brojSimptoma
     */
    public Bolest(Long id, String naziv, Set<Simptom> simptomi, Integer brojSimptoma) {
        super(id, naziv);
        this.simptomi = simptomi;
        this.brojSimptoma = brojSimptoma;
    }



    public Set<Simptom> getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(Set<Simptom> simptomi) {
        this.simptomi = simptomi;
    }

    public Integer getBrojSimptoma() {
        return brojSimptoma;
    }

    public void setBrojSimptoma(Integer brojSimptoma) {
        this.brojSimptoma = brojSimptoma;
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bolest)) return false;
        Bolest bolest = (Bolest) o;
        return Objects.equals(naziv, bolest.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simptomi, brojSimptoma);
    }

    @Override
    public String toString() {
        return  naziv;
    }
}
