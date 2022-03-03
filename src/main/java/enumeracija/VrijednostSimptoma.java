package enumeracija;

import model.Simptom;

/**
 * Enumeracija za vrijednosti simptoma
 */
public enum VrijednostSimptoma {

    RIJETKO("rijetko"),
    SREDNJE("srednje"),
    CESTO("često");

    private String vrijednostSimptoma;

    /**
     * Konstruktor prima 1 parametar
     * @param vrijednostSimptoma objekt klase String
     */
    VrijednostSimptoma(String vrijednostSimptoma) {
        this.vrijednostSimptoma = vrijednostSimptoma;
    }

    public String getVrijednostSimptoma() {
        return vrijednostSimptoma;
    }
}
