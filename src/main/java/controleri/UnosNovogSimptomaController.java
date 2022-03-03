package controleri;

import database.BazaPodataka;
import glavna.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Simptom;
import model.Zupanija;
import sortiranje.CovidSorter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class UnosNovogSimptomaController implements Initializable {

    @FXML
    private TextField nazivSimptomaTextField;

    @FXML
    private ComboBox<String> vrijednostSimptomaComboBox;

    Set<Simptom> simptomi = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> vrijednostiSimptoma = new ArrayList<>();
        vrijednostiSimptoma.add("RIJETKO");
        vrijednostiSimptoma.add("SREDNJE");
        vrijednostiSimptoma.add("ČESTO");
    }

    public void spremiNoviSimptomUBazu() throws SQLException {

        String naziv = nazivSimptomaTextField.getText();
        String vrijednostSimptoma = vrijednostSimptomaComboBox.getValue();
        try {
            BazaPodataka.spremiSimptom(naziv, vrijednostSimptoma);;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje podataka u bazu");
            alert.setHeaderText("Podaci o novom simptomu su uspješno spremljeni u bazu!");
            alert.showAndWait();
        }
        catch (SQLException | IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pogreška u radu aplikacije");
            alert.setHeaderText("Pogreška u operaciji s bazom podataka");
            alert.setContentText("Došlo je do pogreške kod spremanja simptoma u bazu podataka");
            alert.showAndWait();
        }

    }

}
