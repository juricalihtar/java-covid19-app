package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Sluzi za unos podataka o osobama.
 */

public class Osoba implements Serializable {

    /**
     * Klasa je kreirana BuilderPatternom
     * Sadrzi 6 varijabli
     */

    public static class Builder{

        private Long id;
        private String ime;
        private String prezime;
        private LocalDate datumRodjenja;
        private Zupanija zupanija;
        private Bolest zarazenBolescu;
        private List<Osoba> kontaktiraneOsobe;

        public Builder(Long id, String ime, String prezime){
            this.id = id;
            this.ime = ime;
            this.prezime = prezime;
        }
        public Builder unesiDatumRodjenja(LocalDate datumRodjenja){
            this.datumRodjenja = datumRodjenja;

            return this;
        }
        public Builder odaberiZupaniju(Zupanija zupanija){
            this.zupanija = zupanija;

            return this;
        }
        public Builder zarazenBolescu(Bolest zarazenBolescu){
            this.zarazenBolescu = zarazenBolescu;


            return this;
        }
        public Builder kontaktiraneOsobe(List<Osoba> kontaktiraneOsobe){
            this.kontaktiraneOsobe = kontaktiraneOsobe;

            if(this.kontaktiraneOsobe != null) {
                for (Osoba x : this.kontaktiraneOsobe) {
                    if (this.zarazenBolescu instanceof Virus virus) {
                        x.setZarazenBolescu(virus);
                    }
                }
            }

            return this;
        }

        /**
         * Sadrzi parametre ime, prezime, starost, zupanija, zarazenBolescu i kontaktiraneOsobe
         * Nakon sto su uneseni podaci za sve parametre, builder vraca osobu sa popunjenim podacima
         * @return
         */
        public Osoba build() {

            Osoba osoba = new Osoba();
            osoba.id = this.id;
            osoba.ime = this.ime;
            osoba.prezime = this.prezime;
            osoba.datumRodjenja = this.datumRodjenja;
            osoba.zupanija = this.zupanija;
            osoba.zarazenBolescu = this.zarazenBolescu;
            osoba.kontaktiraneOsobe = this.kontaktiraneOsobe;

            return osoba;
        }
    }
    private Long id;
    private String ime;
    private String prezime;
    private LocalDate datumRodjenja;
    private Zupanija zupanija;
    private Bolest zarazenBolescu;
    private List<Osoba> kontaktiraneOsobe;

    public Osoba() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }


    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Zupanija getZupanija() {
        return zupanija;
    }

    public void setZupanija(Zupanija zupanija) {
        this.zupanija = zupanija;
    }

    public Bolest getZarazenBolescu() {
        return zarazenBolescu;
    }

    public void setZarazenBolescu(Bolest zarazenBolescu) {
        this.zarazenBolescu = zarazenBolescu;
    }

    public List<Osoba> getKontaktiraneOsobe() {
        return kontaktiraneOsobe;
    }

    public void setKontaktiraneOsobe(List<Osoba> kontaktiraneOsobe) {
        this.kontaktiraneOsobe = kontaktiraneOsobe;
    }

    @Override
    public String toString() {
        return  ime + " " + prezime;
    }
}