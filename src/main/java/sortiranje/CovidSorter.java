package sortiranje;

import model.Zupanija;

import java.util.Comparator;

/**
 * Sluzi za sortiranje zupanija po postotku zarazenosti
 */

public class CovidSorter implements Comparator<Zupanija> {

    private Integer postotakZarazenosti;

    /**
     * Usporeduje zupanije prema postotku zarazenosti
     * Prima 2 ulazna parametra
     * @param zup1
     * @param zup2
     * @return
     */
    @Override
    public int compare(Zupanija zup1, Zupanija zup2) {
        if(zup1.getBrojStanovnika() > zup2.getBrojStanovnika()){
            return 1;
        }
        else if(zup1.getBrojStanovnika() < zup2.getBrojStanovnika()){
            return -1;
        }
        else {
            return zup1.getNaziv().compareTo(zup2.getNaziv());
        }
    }
}

