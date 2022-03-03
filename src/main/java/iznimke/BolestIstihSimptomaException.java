package iznimke;
/**
 * Iznimka u slucaju da su odabrani identični simptomi kao za neku postojecu bolest
 */
public class BolestIstihSimptomaException extends RuntimeException{
    /**
     * Prima 1 ulazni parametar
     * @param message objekt klase String
     */
    public BolestIstihSimptomaException(String message) {
        super(message);
    }

    /**
     * Prima 2 ulazna parametra
     * @param message objekt klase String
     * @param cause objekt klase Throwable
     */
    public BolestIstihSimptomaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Prima 1 ulazni parametar
     * @param cause objekt klase Throwable
     */
    public BolestIstihSimptomaException(Throwable cause) {
        super(cause);
    }
}
