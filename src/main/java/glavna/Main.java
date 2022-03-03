package glavna;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import sortiranje.CovidSorter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *Služi za pokretanje programa za vođenje evidencije o zaraženim osobama.
 */
public class Main extends Application {

    private static Stage mainStage;

    private static SortedSet<Zupanija> zupanije = new TreeSet<>(new CovidSorter());
    private static Set<Simptom> simptomi = new HashSet<>();
    private static Set<Bolest> bolesti = new HashSet<>();
    private static List<Osoba> osobe = new ArrayList<>();

    public static final int BROJ_LINIJA_ZA_ZUPANIJU = 4;
    public static final int BROJ_LINIJA_ZA_SIMPTOM = 3;
    public static  final int BROJ_LINIJA_ZA_BOLEST = 3;
    public static final int BROJ_LINIJA_ZA_OSOBU = 7;


    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("pocetniEkran.fxml"));
        primaryStage.setTitle("Aplikacija za evidenciju zaraženih osoba");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static Stage getMainStage(){
        return mainStage;
    }

    /**
     * Sluzi za pokretanje programa za evidenciju zarazenih osoba
     * @param args
     */
    public static void main(String[] args) {

        Scanner unos = new Scanner(System.in);

        launch(args);

    }

    public static SortedSet<Zupanija> getZupanije(){
        return zupanije;
    }
    public static Set<Simptom> getSimptomi(){
        return simptomi;
    }
    public static Set<Bolest> getBolesti(){
        return bolesti;
    }
    public static List<Osoba> getOsobe(){
        return osobe;
    }

}
