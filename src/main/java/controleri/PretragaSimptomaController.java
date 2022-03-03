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
import model.Simptom;
import glavna.Main;
import sortiranje.CovidSorter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Sluzi za pretragu simptoma
 */
public class PretragaSimptomaController extends CovidSorter implements Initializable {

    @FXML
    private TextField pretragaPoNazivuTextField;
    @FXML
    private TableView<Simptom> tablicaSimptomaTableView;
    @FXML
    private TableColumn<Simptom, String> nazivSimptomaTableColumn;
    @FXML
    private TableColumn<Simptom, String> vrijednostSimptomaTableColumn;

    public static List<Simptom> simptomi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        simptomi = new ArrayList<>();
        try {
            simptomi = BazaPodataka.dohvatiSveSimptome();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }


        nazivSimptomaTableColumn
                .setCellValueFactory(new PropertyValueFactory<Simptom, String>("naziv"));
        vrijednostSimptomaTableColumn
                .setCellValueFactory(new PropertyValueFactory<Simptom, String>("vrijednost"));

        ObservableList<Simptom> observableListSimptom = FXCollections.observableArrayList(simptomi);
        tablicaSimptomaTableView.setItems(observableListSimptom);

    }

    /**
     * Sluzi za pretragu simptoma po nazivu
     */
    public void pretraziPoNazivuSimptoma() {
        String nazivSimptomaZaPretragu = pretragaPoNazivuTextField.getText();
        List<Simptom> filtritanaListaSimptoma =
                simptomi.stream().filter(s -> s.getNaziv().contains(nazivSimptomaZaPretragu)).collect(Collectors.toList());
        tablicaSimptomaTableView.setItems(FXCollections.observableArrayList(filtritanaListaSimptoma));
    }
}
