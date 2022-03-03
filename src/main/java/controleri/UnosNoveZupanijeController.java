package controleri;

import database.BazaPodataka;
import glavna.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Zupanija;
import sortiranje.CovidSorter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.sql.SQLException;
import java.util.*;

public class UnosNoveZupanijeController implements Initializable {

    @FXML
    private TextField nazivZupanijeTextField;

    @FXML
    private TextField brojStanovnikaZupanije;

    @FXML
    private TextField brojZarazenihUZupaniji;

    public static SortedSet<Zupanija> zupanije;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void spremiNovuZupaniju() {

        String naziv = nazivZupanijeTextField.getText();
        Integer brojStanovnika = Integer.parseInt(brojStanovnikaZupanije.getText());
        Integer brojZarazenih = Integer.parseInt(brojZarazenihUZupaniji.getText());
        try {
            BazaPodataka.spremiZupaniju(naziv, brojStanovnika, brojZarazenih);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje podataka u bazu");
            alert.setHeaderText("Podaci o novoj županiji su uspješno spremljeni u bazu!");
            alert.showAndWait();
        }
        catch (SQLException | IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pogreška u radu aplikacije");
            alert.setHeaderText("Pogreška u operaciji s bazom podataka");
            alert.setContentText("Došlo je do pogreške kod spremanja županije u bazu podataka");
            alert.showAndWait();
        }

    }

}
