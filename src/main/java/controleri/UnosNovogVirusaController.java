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
import model.Bolest;
import model.Simptom;
import model.Virus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class UnosNovogVirusaController implements Initializable {

    @FXML
    private TextField nazivVirusaTextField;

    @FXML
    private ListView<Simptom> simptomiListView;

    Set<Bolest> bolesti = new HashSet<>();
    public static Set<Bolest> virusi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        simptomiListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Simptom> observableListaSimptoma = null;
        try {
            observableListaSimptoma = FXCollections.observableArrayList(BazaPodataka.dohvatiSveSimptome());
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        simptomiListView.setItems(observableListaSimptoma);
    }

    public void spremiNoviVirus(){
        String naziv = nazivVirusaTextField.getText();
        Set<Simptom> simptomiVirusa = new HashSet<>();
        for(Simptom simptom : simptomiListView.getSelectionModel().getSelectedItems()) {
            simptomiVirusa.add(simptom);
        }

        try {
            BazaPodataka.spremiVirus(naziv, simptomiVirusa);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje podataka u bazu");
            alert.setHeaderText("Podaci o novom virusu su uspješno spremljeni u bazu!");
            alert.showAndWait();
        }
        catch (SQLException | IOException ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pogreška u radu aplikacije");
            alert.setHeaderText("Pogreška u operaciji s bazom podataka");
            alert.setContentText("Došlo je do pogreške kod spremanja virusa u bazu podataka");
            alert.showAndWait();
        }
    }

}
