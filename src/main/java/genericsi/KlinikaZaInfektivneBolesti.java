package genericsi;



import model.Virus;

import java.util.List;

/**
 * Parametrizirana klasa
 * @param <T> instanca klase Virus ili klase koja nasljeđuje klasu Virus
 * @param <S> instanca klase Osoba ili klase koja nasljeđuje klasu Osoba
 */

public class KlinikaZaInfektivneBolesti <T extends Virus, S extends Virus > {

        private List<T> listaVirusa;
        private List<S> listaOsoba;

    /**
     * Konstruktor prima 2 parametra
     * @param listaVirusa
     * @param listaOsoba
     */
        public KlinikaZaInfektivneBolesti(List<T> listaVirusa, List<S> listaOsoba) {
            this.listaVirusa = listaVirusa;
            this.listaOsoba = listaOsoba;
        }

        public List<T> getListaVirusa() {
            return listaVirusa;
        }

        public void setListaVirusa(List<T> listaVirusa) {
            this.listaVirusa = listaVirusa;
        }

        public List<S> getListaOsoba() {
            return listaOsoba;
        }

        public void setListaOsoba(List<S> listaOsoba) {
            this.listaOsoba = listaOsoba;
        }
}
