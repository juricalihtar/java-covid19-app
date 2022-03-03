package database;

import model.*;
import model.Bolest;
import org.h2.command.dml.Insert;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Klasa koja nam služi za rad s bazama podataka
 */

public class BazaPodataka {

    private static final String DATABASE_FILE ="dat/bazapodataka.properties";

    /**
     * Metoda za spajanje na bazu
     * @return konekcija na bazu
     * @throws SQLException
     * @throws IOException
     */
    private static Connection spojiSeNaBazu() throws SQLException, IOException {

        Properties svojstva = new Properties();
        svojstva.load(new FileReader(DATABASE_FILE));

        String url = svojstva.getProperty("url");
        String username = svojstva.getProperty("username");
        String password = svojstva.getProperty("password");



        return DriverManager.getConnection(url,username,password);
    }

    /**
     * Funkcija za odpsajanje
     * @param vezaNaBazu baza
     * @throws SQLException
     */
    private static void zatvoriVezuNaBazu(Connection vezaNaBazu) throws SQLException {
        vezaNaBazu.close();
    }

    /**
     * Metoda za spremanje novih simptima u bazu
     * @throws SQLException
     * @throws IOException
     */
    public static void spremiSimptom(String nazivSimptoma, String vrijednostSimptoma) throws SQLException, IOException {
        Connection veza = spojiSeNaBazu();

        PreparedStatement insert = veza.prepareStatement("INSERT INTO SIMPTOM(NAZIV, VRIJEDNOST) VALUES(?, ?);");

        insert.setString(1, nazivSimptoma);
        insert.setString(2, vrijednostSimptoma);

        insert.executeUpdate();

        zatvoriVezuNaBazu(veza);
    }

    /**
     * Metoda koja učitava simptome iz baze
     * @return set sa simptimima
     * @throws SQLException
     * @throws IOException
     */
    public static List<Simptom> dohvatiSveSimptome() throws SQLException, IOException {

        List<Simptom> listaSimptoma = new ArrayList<>();
        Connection veza = spojiSeNaBazu();

        Statement upit = veza.createStatement();

        ResultSet simptomi = upit.executeQuery("SELECT * FROM SIMPTOM");
        while (simptomi.next()) {

            Long idSimptoma = simptomi.getLong("ID");
            String nazivSimptoma = simptomi.getString("NAZIV");
            String vrijednostSimptoma = simptomi.getString("VRIJEDNOST");

            Simptom simptom = new Simptom(idSimptoma, nazivSimptoma, vrijednostSimptoma);
            listaSimptoma.add(simptom);
        }


        zatvoriVezuNaBazu(veza);
        return listaSimptoma;

    }

    /**
     * Metoda za spremanje novih županija u bazu
     * @throws SQLException
     * @throws IOException
     */
    public static void spremiZupaniju(String naziv, Integer brojStanovnika, Integer brojZarazenih) throws SQLException, IOException {
        Connection veza = spojiSeNaBazu();

        PreparedStatement insert = veza.prepareStatement("INSERT INTO ZUPANIJA(NAZIV, BROJ_STANOVNIKA, BROJ_ZARAZENIH_STANOVNIKA) VALUES (?, ?, ?);");

        insert.setString(1, naziv);
        insert.setInt(2, brojStanovnika);
        insert.setInt(3, brojZarazenih);

        insert.executeUpdate();

        zatvoriVezuNaBazu(veza);
    }

    /**
     * Metoda koja učitava sve župonije iz baze
     * @return set sa svim županijama
     * @throws SQLException
     * @throws IOException
     */
    public static List<Zupanija> dohvatiSveZupanije() throws SQLException, IOException {

        List<Zupanija> listaZupanija = new ArrayList<>();
        Connection veza = spojiSeNaBazu();

        Statement upit = veza.createStatement();

        ResultSet zupanije = upit.executeQuery("SELECT * FROM ZUPANIJA");
        while (zupanije.next()) {

            Long idZupanije = zupanije.getLong("ID");
            String nazivZupanije = zupanije.getString("NAZIV");
            Integer brojStanovnika = zupanije.getInt("BROJ_STANOVNIKA");
            Integer brojZarazenih = zupanije.getInt("BROJ_ZARAZENIH_STANOVNIKA");

            Zupanija zupanija = new Zupanija(idZupanije, nazivZupanije, brojStanovnika, brojZarazenih);
            listaZupanija.add(zupanija);
        }


        zatvoriVezuNaBazu(veza);
        return listaZupanija;

    }

    /**
     * Metoda za spremanje novih bolesti u bazu
     * @throws SQLException
     * @throws IOException
     */
    public static void spremiBolest(String naziv, Set<Simptom> simptomiBolesti) throws SQLException, IOException {
        Connection veza = spojiSeNaBazu();

        PreparedStatement insert = veza.prepareStatement("INSERT INTO BOLEST(NAZIV, VIRUS) VALUES (?, ?);");

        insert.setString(1, naziv);
        insert.setBoolean(2, false);

        insert.executeUpdate();

        List<Bolest> bolesti = dohvatiSveBolesti();
        Long idBolesti = null;
        for(Bolest bolest : bolesti) {
            if(bolest.getNaziv().equals(naziv)) {
                idBolesti = bolest.getId();
                break;
            }
        }

        PreparedStatement insertSimptom = veza.prepareStatement("INSERT INTO BOLEST_SIMPTOM(BOLEST_ID, SIMPTOM_ID) VALUES (?, ?);");

        for(Simptom simptom : simptomiBolesti) {
            insertSimptom.setLong(1, idBolesti);
            insertSimptom.setLong(2, simptom.getId());
            insertSimptom.executeUpdate();
        }

        zatvoriVezuNaBazu(veza);
    }

    public static void spremiVirus(String naziv, Set<Simptom> simptomiVirusa) throws SQLException, IOException {
        Connection veza = spojiSeNaBazu();

        PreparedStatement insert = veza.prepareStatement("INSERT INTO BOLEST(NAZIV, VIRUS) VALUES (?, ?);");

        insert.setString(1, naziv);
        insert.setBoolean(2, true);

        insert.executeUpdate();

        List<Bolest> bolesti = dohvatiSveBolesti();
        Long idVirusa = null;
        for(Bolest bolest : bolesti) {
            if(bolest.getNaziv().equals(naziv)) {
                idVirusa = bolest.getId();
                break;
            }
        }

        PreparedStatement insertSimptom = veza.prepareStatement("INSERT INTO BOLEST_SIMPTOM(BOLEST_ID, SIMPTOM_ID) VALUES (?, ?);");

        for(Simptom simptom : simptomiVirusa) {
            insertSimptom.setLong(1, idVirusa);
            insertSimptom.setLong(2, simptom.getId());
            insertSimptom.executeUpdate();
        }

        zatvoriVezuNaBazu(veza);
    }

    /**
     * Motoda koja vraća sve bolesti iz baze
     * @return set sa svim bolestima
     * @throws SQLException
     * @throws IOException
     */
    public static List<Bolest> dohvatiSveBolesti() throws SQLException, IOException {

        List<Bolest> listaBolesti = new ArrayList<>();
        Set<Simptom> simptomiBolesti = new HashSet<>();
        simptomiBolesti.clear();
        Connection veza = spojiSeNaBazu();

        Statement upit = veza.createStatement();

        ResultSet bolesti = upit.executeQuery("SELECT * FROM BOLEST");
        while (bolesti.next()) {

            Long idBolesti = bolesti.getLong("ID");
            String nazivBolesti = bolesti.getString("NAZIV");
            Boolean bolestIliVirus = bolesti.getBoolean("VIRUS");
            simptomiBolesti = dohvatiSimptomeBolesti(idBolesti);
            Integer brojSimptoma = simptomiBolesti.size();

            if (bolestIliVirus) {
                Virus virus = new Virus(idBolesti, nazivBolesti, simptomiBolesti, brojSimptoma);
                listaBolesti.add(virus);
            } else if (!bolestIliVirus) {
                Bolest bolest = new Bolest(idBolesti, nazivBolesti, simptomiBolesti, brojSimptoma);
                listaBolesti.add(bolest);
            }
        }

        zatvoriVezuNaBazu(veza);
        return listaBolesti;
    }

    private static Set<Simptom> dohvatiSimptomeBolesti(Long idBolesti) throws SQLException, IOException {
        Connection veza = spojiSeNaBazu();
        List<Simptom> listaSimptoma = dohvatiSveSimptome();
        Set<Simptom> simptomiBolesti = new HashSet<>();
        simptomiBolesti.clear();

        PreparedStatement upit = veza.prepareStatement("SELECT ID, NAZIV, VRIJEDNOST \n" +
                "FROM SIMPTOM JOIN BOLEST_SIMPTOM ON SIMPTOM.ID = BOLEST_SIMPTOM.SIMPTOM_ID\n" +
                "WHERE BOLEST_ID = ?");
        upit.setLong(1, idBolesti);
        ResultSet rezultati = upit.executeQuery();
        while (rezultati.next()){
            Long idS = rezultati.getLong("ID");
            String  nazivSimptoma = rezultati.getString("NAZIV");
            String vrijednostSimptoma = rezultati.getString("VRIJEDNOST");
            simptomiBolesti.add(new Simptom(idS, nazivSimptoma, vrijednostSimptoma));
        }
        zatvoriVezuNaBazu(veza);
        return simptomiBolesti;
    }

    /**
     * Metoda koja vraća sve osobe iz baze
     * @return Lista sa svim osobama
     * @throws SQLException
     * @throws IOException
     */
    public static List<Osoba> dohvatiSveOsobe() throws SQLException, IOException {

        List<Osoba> listaOsoba = new ArrayList<>();
        Connection veza = spojiSeNaBazu();

        Statement upit = veza.createStatement();

        ResultSet osobe = upit.executeQuery("SELECT * FROM OSOBA ");
        while (osobe.next()) {
            Long id = osobe.getLong("ID");
            String ime = osobe.getString("IME");
            String prezime = osobe.getString("PREZIME");
            LocalDate datumRodjenja = osobe.getDate("DATUM_RODJENJA").toLocalDate();
            Long idZupanije = osobe.getLong("ZUPANIJA_ID");
            Zupanija zupanijaOsobe = null;
            for(Zupanija zupanija : dohvatiSveZupanije()) {
                if(zupanija.id.equals(idZupanije)) {
                    zupanijaOsobe = zupanija;
                    break;
                }
            }

            Long idBolesti = osobe.getLong("BOLEST_ID");
            Bolest bolestOsobe = null;
            for(Bolest bolest : dohvatiSveBolesti()) {
                if(bolest.getId().equals(idBolesti)) {
                    bolestOsobe = bolest;
                    break;
                }
            }
            List<Osoba> kontakritaneOsobe = dohvatiKontaktiraneOsobe(id);
            Osoba novaOsoba = new Osoba.Builder(id, ime, prezime)
                    .unesiDatumRodjenja(datumRodjenja)
                    .odaberiZupaniju(zupanijaOsobe)
                    .zarazenBolescu(bolestOsobe)
                    .kontaktiraneOsobe(kontakritaneOsobe)
                    .build();
            listaOsoba.add(novaOsoba);
        }

        zatvoriVezuNaBazu(veza);
        return listaOsoba;
    }

    private static List<Osoba> dohvatiKontaktiraneOsobe(Long idOsobe) throws SQLException, IOException {
        List<Osoba> listaOsoba = new ArrayList<>();
        Connection veza = spojiSeNaBazu();

        PreparedStatement upit = veza.prepareStatement("SELECT ID, IME, PREZIME, DATUM_RODJENJA, ZUPANIJA_ID, BOLEST_ID FROM\n" +
                "OSOBA JOIN KONTAKTIRANE_OSOBE ON OSOBA.ID = KONTAKTIRANE_OSOBE.KONTAKTIRANA_OSOBA_ID\n" +
                "WHERE KONTAKTIRANE_OSOBE.OSOBA_ID = ?");

        upit.setLong(1, idOsobe);
        ResultSet osobe = upit.executeQuery();
        while (osobe.next()) {
            Long id = osobe.getLong("ID");
            String ime = osobe.getString("IME");
            String prezime = osobe.getString("PREZIME");
            LocalDate datumRodjenja = osobe.getDate("DATUM_RODJENJA").toLocalDate();
            Long idZupanije = osobe.getLong("ZUPANIJA_ID");
            Zupanija zupanijaOsobe = null;
            for(Zupanija zupanija : dohvatiSveZupanije()) {
                if(zupanija.id.equals(idZupanije)) {
                    zupanijaOsobe = zupanija;
                    break;
                }
            }

            Long idBolesti = osobe.getLong("BOLEST_ID");
            Bolest bolestOsobe = null;
            for(Bolest bolest : dohvatiSveBolesti()) {
                if(bolest.getId().equals(idBolesti)) {
                    bolestOsobe = bolest;
                    break;
                }
            }

            List<Osoba> kontakritaneOsobe = dohvatiKontaktiraneOsobe(id);

            Osoba novaOsoba = new Osoba.Builder(id, ime, prezime)
                    .unesiDatumRodjenja(datumRodjenja)
                    .odaberiZupaniju(zupanijaOsobe)
                    .zarazenBolescu(bolestOsobe)
                    .kontaktiraneOsobe(kontakritaneOsobe)
                    .build();
            listaOsoba.add(novaOsoba);
        }

        zatvoriVezuNaBazu(veza);
        return listaOsoba;
    }

    /**
     * Metora za spremanje osoba u bazu
     * @throws SQLException
     * @throws IOException
     */
    public static void spremiOsobu(String ime, String prezime, LocalDate datumRodjenja,
                                   Zupanija zupanija, Bolest bolest, List<Osoba> kontaktiraneOsobe) throws SQLException, IOException {

        Connection veza = spojiSeNaBazu();

        PreparedStatement insert = veza.prepareStatement(
                "INSERT INTO OSOBA(IME, PREZIME, DATUM_RODJENJA, ZUPANIJA_ID, BOLEST_ID) VALUES (?, ?, ?, ?, ?);");

        insert.setString(1, ime);
        insert.setString(2, prezime);
        insert.setDate(3, Date.valueOf(datumRodjenja));
        insert.setLong(4, zupanija.getId());
        insert.setLong(5, bolest.getId());

        insert.executeUpdate();

        List<Osoba> osobe = dohvatiSveOsobe();
        Long idOsobe = null;
        for(Osoba osoba : osobe) {
            if(osoba.getIme().equals(ime) && osoba.getPrezime().equals(prezime) && osoba.getDatumRodjenja().equals(datumRodjenja)) {
                idOsobe = osoba.getId();
                break;
            }
        }

        PreparedStatement insertKontaktiraneOsobe = veza.prepareStatement("" +
                "INSERT INTO KONTAKTIRANE_OSOBE(OSOBA_ID, KONTAKTIRANA_OSOBA_ID) VALUES (?, ?);");

        for(Osoba osoba : kontaktiraneOsobe) {
            insertKontaktiraneOsobe.setLong(1, idOsobe);
            insertKontaktiraneOsobe.setLong(2, osoba.getId());
            insertKontaktiraneOsobe.executeUpdate();
        }

        zatvoriVezuNaBazu(veza);

    }
}