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
import model.Zupanija;
import glavna.Main;
import sortiranje.CovidSorter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Sluzi za pretragu zupanija
 */
public class PretragaZupanijaController extends CovidSorter implements Initializable {

    @FXML
    private TextField pretragaPoNazivuTextField;
    @FXML
    private TableView<Zupanija> tablicaZupanijaTableView;
    @FXML
    private TableColumn<Zupanija, String> nazivZupanijeTableColumn;
    @FXML
    private TableColumn<Zupanija, Integer> brojStanovnikaZupanijeTableColumn;
    @FXML
    private TableColumn<Zupanija, Integer> brojZarazenihZupanijeTableColumn;

    public static SortedSet<Zupanija> zupanije;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Zupanija> listaZupanija = new ArrayList<>();
        try {
            listaZupanija = BazaPodataka.dohvatiSveZupanije();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        zupanije = new TreeSet<>(new CovidSorter());
        for(Zupanija zupanija : listaZupanija){
            zupanije.add(zupanija);
        }

        nazivZupanijeTableColumn
                .setCellValueFactory(new PropertyValueFactory<Zupanija, String>("naziv"));
        brojStanovnikaZupanijeTableColumn
                .setCellValueFactory(new PropertyValueFactory<Zupanija, Integer>("brojStanovnika"));
        brojZarazenihZupanijeTableColumn
                .setCellValueFactory(new PropertyValueFactory<Zupanija, Integer>("brojZarazenih"));

        ObservableList<Zupanija> observableListZupanija = FXCollections.observableArrayList(zupanije);
        tablicaZupanijaTableView.setItems(observableListZupanija);

    }

    /**
     * Sluzi za pretragu zupanija po nazivu
     */
    public void pretraziPoNazivuZupanije() {
        String nazivZupanijeZaPretragu = pretragaPoNazivuTextField.getText();
        List<Zupanija> filtritanaListaZupanija =
                zupanije.stream().filter(z -> z.getNaziv().contains(nazivZupanijeZaPretragu)).collect(Collectors.toList());
        tablicaZupanijaTableView.setItems(FXCollections.observableArrayList(filtritanaListaZupanija));
    }
}
