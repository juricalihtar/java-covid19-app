package controleri;

import database.BazaPodataka;
import glavna.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

public class UnosNoveBolestiController implements Initializable {

    @FXML
    private TextField nazivBolestiTextField;

    @FXML
    private ListView<Simptom> simptomiListView;

    Set<Bolest> bolesti = new HashSet<>();

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

    public void spremiNovuBolest(){
        String naziv = nazivBolestiTextField.getText();
        Set<Simptom> simptomiBolesti = new HashSet<>();
        for(Simptom simptom : simptomiListView.getSelectionModel().getSelectedItems()) {
            simptomiBolesti.add(simptom);
        }

        try {
            BazaPodataka.spremiBolest(naziv, simptomiBolesti);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje podataka u bazu");
            alert.setHeaderText("Podaci o novoj bolesti su uspješno spremljeni u bazu!");
            alert.showAndWait();
        }
        catch (SQLException | IOException ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pogreška u radu aplikacije");
            alert.setHeaderText("Pogreška u operaciji s bazom podataka");
            alert.setContentText("Došlo je do pogreške kod spremanja bolesti u bazu podataka");
            alert.showAndWait();
        }

    }

}
