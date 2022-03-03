package iznimke;

/**
 * Iznimka u slucaju da se odabere kontaktirana osoba koja je već prethodno odabrana
 */

public class DuplikatKontaktiraneOsobeException extends Exception{

    /**
     * Ima jedan ulazni parametar
     * @param message objekt klase String
     */
    public DuplikatKontaktiraneOsobeException(String message) {
        super(message);
    }

    /**
     * Ima dva ulazna parametra
     * @param message objekt klase String
     * @param cause objekt klase Throwable
     */
    public DuplikatKontaktiraneOsobeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Ima 1 ulazni parametar
     * @param cause objekt klase Throwable
     */
    public DuplikatKontaktiraneOsobeException(Throwable cause) {
        super(cause);
    }
}
