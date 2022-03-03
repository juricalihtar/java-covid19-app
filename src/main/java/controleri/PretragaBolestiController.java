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
 * Sluzi za pretragu bolesti
 */
public class PretragaBolestiController extends CovidSorter implements Initializable {

    @FXML
    private TextField pretragaPoNazivuTextField;
    @FXML
    private TableView<Bolest> tablicaBolestiTableView;
    @FXML
    private TableColumn<Bolest, String> nazivBolestiTableColumn;
    @FXML
    private TableColumn<Bolest, Set<Simptom>> simptomiBolestiTableColumn;
    @FXML
    private TableColumn<Bolest, Integer> brojSimptomaTableColumn;

    public static Set<Bolest> bolesti;
    public static Set<Bolest> bolestiBezVirusa;


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
        bolestiBezVirusa = new HashSet<>();
        for(Bolest bolest : bolesti) {
            if(!(bolest instanceof Virus)){
                bolestiBezVirusa.add(bolest);
            }
        }

        nazivBolestiTableColumn
                .setCellValueFactory(new PropertyValueFactory<Bolest, String>("naziv"));
        simptomiBolestiTableColumn
                .setCellValueFactory(new PropertyValueFactory<Bolest, Set<Simptom>>("simptomi"));
        brojSimptomaTableColumn
                .setCellValueFactory(new PropertyValueFactory<Bolest,Integer>("brojSimptoma"));

        ObservableList<Bolest> observableListBolesti = FXCollections.observableArrayList(bolestiBezVirusa);
        tablicaBolestiTableView.setItems(observableListBolesti);

    }

    /**
     * Sluzi za pretragu bolesti po nazivu
     */
    public void pretraziPoNazivuBolesti() {
        String nazivBolestiZaPretragu = pretragaPoNazivuTextField.getText();
        List<Bolest> filtritanaListaBolesti =
                bolestiBezVirusa.stream().filter(b -> b.getNaziv().contains(nazivBolestiZaPretragu)).collect(Collectors.toList());
        tablicaBolestiTableView.setItems(FXCollections.observableArrayList(filtritanaListaBolesti));
    }
}
