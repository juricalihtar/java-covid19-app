package controleri;

import database.BazaPodataka;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bolest;
import model.Osoba;
import model.Zupanija;
import glavna.Main;
import sortiranje.CovidSorter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Sluzi za pretragu osoba
 */
public class PretragaOsobaController extends CovidSorter implements Initializable {

    @FXML
    private TextField pretragaPoNazivuTextField;
    @FXML
    private TableView<Osoba> tablicaOsobaTableView;
    @FXML
    private TableColumn<Osoba, String> imeOsobeTableColumn;
    @FXML
    private TableColumn<Osoba, String> prezimeOsobeTableColumn;
    @FXML
    private TableColumn<Osoba, LocalDate> starostOsobeTableColumn;
    @FXML
    private TableColumn<Osoba, Zupanija> zupanijaPrebivalistaOsobeTableColumn;
    @FXML
    private TableColumn<Osoba, Bolest> zarazenBolescuTableColumn;
    @FXML
    private TableColumn<Osoba, List<Osoba>> kontaktiraneOsobeTableColumn;

    public static List<Osoba> osobe;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        osobe = new ArrayList<>();
        try {
            osobe = BazaPodataka.dohvatiSveOsobe();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        imeOsobeTableColumn
                .setCellValueFactory(new PropertyValueFactory<Osoba, String>("ime"));;
        prezimeOsobeTableColumn
                .setCellValueFactory(new PropertyValueFactory<Osoba, String>("prezime"));;
        starostOsobeTableColumn
                .setCellValueFactory(new PropertyValueFactory<Osoba, LocalDate>("datumRodjenja"));;
        zupanijaPrebivalistaOsobeTableColumn
                .setCellValueFactory(new PropertyValueFactory<Osoba, Zupanija>("zupanija"));;
        zarazenBolescuTableColumn
                .setCellValueFactory(new PropertyValueFactory<Osoba, Bolest>("zarazenBolescu"));;
        kontaktiraneOsobeTableColumn
                .setCellValueFactory(new PropertyValueFactory<Osoba, List<Osoba>>("kontaktiraneOsobe"));;


        ObservableList<Osoba> observableListOsoba = FXCollections.observableArrayList(osobe);
        tablicaOsobaTableView.setItems(observableListOsoba);

    }

    /**
     * Sluzi za pretragu osoba po imenu i prezimenu
     */
    public void pretraziPoNazivuOsobe() {
        String nazivOsobeZaPretragu = pretragaPoNazivuTextField.getText();
        List<Osoba> filtritanaListaOsoba =
                osobe.stream().filter(o -> (o.getIme()+" "+o.getPrezime()).contains(nazivOsobeZaPretragu)).collect(Collectors.toList());
        tablicaOsobaTableView.setItems(FXCollections.observableArrayList(filtritanaListaOsoba));
    }
}
