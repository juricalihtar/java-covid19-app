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
import model.Simptom;
import model.Virus;
import glavna.Main;
import sortiranje.CovidSorter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Sluzi za pretragu virusa
 */
public class PretragaVirusaController extends CovidSorter implements Initializable {

    @FXML
    private TextField pretragaPoNazivuTextField;
    @FXML
    private TableView<Bolest> tablicaVirusaTableView;
    @FXML
    private TableColumn<Bolest, String> nazivVirusaTableColumn;
    @FXML
    private TableColumn<Bolest, Set<Simptom>> simptomiVirusaTableColumn;
    @FXML
    private TableColumn<Bolest, Integer> brojSimptomaTableColumn;

    public static Set<Bolest> bolesti;
    public static Set<Bolest> virusi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Bolest> listaBolesti = new ArrayList<>();
        try {
            listaBolesti = BazaPodataka.dohvatiSveBolesti();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        bolesti = new HashSet<>();
        bolesti.addAll(listaBolesti);
        virusi = new HashSet<>();
        for(Bolest bolest : bolesti) {
            if((bolest instanceof Virus)){
                virusi.add(bolest);
            }
        }

        nazivVirusaTableColumn
                .setCellValueFactory(new PropertyValueFactory<Bolest, String>("naziv"));
        simptomiVirusaTableColumn
                .setCellValueFactory(new PropertyValueFactory<Bolest, Set<Simptom>>("simptomi"));
        brojSimptomaTableColumn
                .setCellValueFactory(new PropertyValueFactory<Bolest,Integer>("brojSimptoma"));

        ObservableList<Bolest> observableListBolesti = FXCollections.observableArrayList(virusi);
        tablicaVirusaTableView.setItems(observableListBolesti);

    }

    /**
     * Sluzi za pretragu virua po nazivu
     */
    public void pretraziPoNazivuVirusa() {
        String nazivVirusaZaPretragu = pretragaPoNazivuTextField.getText();
        List<Bolest> filtritanaListaVirusa =
                virusi.stream().filter(b -> b.getNaziv().contains(nazivVirusaZaPretragu)).collect(Collectors.toList());
        tablicaVirusaTableView.setItems(FXCollections.observableArrayList(filtritanaListaVirusa));
    }
}
