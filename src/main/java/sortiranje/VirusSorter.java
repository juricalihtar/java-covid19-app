package sortiranje;

import model.Bolest;
import model.Virus;

import java.util.Comparator;

/**
 * Sluzi za sortiranje virusa
 */
public class VirusSorter implements Comparator<Bolest> {
    /**
     * Uspoređuje viruse prema nazivu
     * Prima 2 ulazna parametra
     * @param v1 virus1
     * @param v2 virus2
     * @return
     */
    @Override
    public int compare(Bolest v1, Bolest v2) {
        return (int) (v2.getNaziv().compareTo(v1.getNaziv()));
    }
}
