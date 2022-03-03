package controleri;

import database.BazaPodataka;
import glavna.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class UnosNoveOsobeController implements Initializable {

    @FXML
    private TextField imeOsobeTextField;

    @FXML
    private TextField prezimeOsobeTextField;

    @FXML
    private TextField starostOsobeTextField;

    @FXML
    private ListView<Zupanija> zupanijeListView;

    @FXML
    private ListView<Bolest> bolestiListView;

    @FXML
    private ListView<Osoba> kontaktiraneOsobeListView;

    List<Osoba> osobe = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zupanijeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Zupanija> observableListaZupanija = null;
        try {
            observableListaZupanija = FXCollections.observableArrayList(BazaPodataka.dohvatiSveZupanije());
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        zupanijeListView.setItems(observableListaZupanija);

        bolestiListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Bolest> observableListaBolesti = null;
        try {
            observableListaBolesti = FXCollections.observableArrayList(BazaPodataka.dohvatiSveBolesti());
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        bolestiListView.setItems(observableListaBolesti);

        kontaktiraneOsobeListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Osoba> observableListaOsoba = null;
        try {
            observableListaOsoba = FXCollections.observableArrayList(BazaPodataka.dohvatiSveOsobe());
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        kontaktiraneOsobeListView.setItems(observableListaOsoba);

    }

    public void spremiNovuOsobu() {
        String ime = imeOsobeTextField.getText();
        String prezime = prezimeOsobeTextField.getText();
        LocalDate datumRodjenja = LocalDate.parse(starostOsobeTextField.getText());
        Zupanija zupanija = zupanijeListView.getSelectionModel().getSelectedItem();
        Bolest bolest = bolestiListView.getSelectionModel().getSelectedItem();
        List<Osoba> kontaktiraneOsobe = new ArrayList<>();

        for(Osoba osoba : kontaktiraneOsobeListView.getSelectionModel().getSelectedItems()) {
            kontaktiraneOsobe.add(osoba);
        }

        try {
            BazaPodataka.spremiOsobu(ime, prezime, datumRodjenja, zupanija, bolest, kontaktiraneOsobe);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje podataka u bazu");
            alert.setHeaderText("Podaci o novoj osobi su uspješno spremljeni u bazu!");
            alert.showAndWait();
        }
        catch (SQLException | IOException ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pogreška u radu aplikacije");
            alert.setHeaderText("Pogreška u operaciji s bazom podataka");
            alert.setContentText("Došlo je do pogreške kod spremanja osobe u bazu podataka");
            alert.showAndWait();
        }
    }
}

