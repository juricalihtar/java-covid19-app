package controleri;

import glavna.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class PocetniEkranController {

    public void prikaziEkranZaPretraguZupanija() throws IOException {
        Parent pretragaZupanijaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaZupanija.fxml"));
        Scene pretragaZupanijaScene = new Scene(pretragaZupanijaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaZupanijaScene);
    }

    public void prikaziEkranZaPretraguSimptoma() throws IOException {
        Parent pretragaSimptomaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaSimptoma.fxml"));
        Scene pretragaSimptomaScene = new Scene(pretragaSimptomaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaSimptomaScene);
    }

    public void prikaziEkranZaPretraguBolesti() throws IOException {
        Parent pretragaBolestiFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaBolesti.fxml"));
        Scene pretragaBolestiScene = new Scene(pretragaBolestiFrame, 600, 400);
        Main.getMainStage().setScene(pretragaBolestiScene);
    }

    public void prikaziEkranZaPretraguVirusa() throws IOException {
        Parent pretragaVirusaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaVirusa.fxml"));
        Scene pretragaVirusaScene = new Scene(pretragaVirusaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaVirusaScene);
    }

    public void prikaziEkranZaPretraguOsoba() throws IOException {
        Parent pretragaOsobaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaOsoba.fxml"));
        Scene pretragaOsobaScene = new Scene(pretragaOsobaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaOsobaScene);
    }

    public void prikaziPocetniEkran() throws IOException {
        Parent pocetniFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pocetniEkran.fxml"));
        Scene pocetniScene = new Scene(pocetniFrame, 600, 400);
        Main.getMainStage().setScene(pocetniScene);
    }

    public void prikaziEkranZaDodavanjeNoveZupanije() throws IOException {
        Parent dodavanjeNoveZupanijeFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNoveZupanije.fxml"));
        Scene dodavanjeNoveZupanijeScene = new Scene(dodavanjeNoveZupanijeFrame, 600, 400);
        Main.getMainStage().setScene(dodavanjeNoveZupanijeScene);
    }

    public void prikaziEkranZaDodavanjeNovogSimptoma() throws IOException {
        Parent dodavanjeNovogSimptomaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNovogSimptoma.fxml"));
        Scene dodavanjeNovogSimptomaScene = new Scene(dodavanjeNovogSimptomaFrame, 600, 400);
        Main.getMainStage().setScene(dodavanjeNovogSimptomaScene);
    }

    public void prikaziEkranZaDodavanjeNoveBolesti() throws IOException {
        Parent dodavanjeNoveBolestiFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNoveBolesti.fxml"));
        Scene dodavanjeNoveBolestiScene = new Scene(dodavanjeNoveBolestiFrame, 600, 400);
        Main.getMainStage().setScene(dodavanjeNoveBolestiScene);
    }

    public void prikaziEkranZaDodavanjeNovogVirusa() throws IOException {
        Parent dodavanjeNovogVirusaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNovogVirusa.fxml"));
        Scene dodavanjeNovogVirusaScene = new Scene(dodavanjeNovogVirusaFrame, 600, 400);
        Main.getMainStage().setScene(dodavanjeNovogVirusaScene);
    }

    public void prikaziEkranZaDodavanjeNoveOsobe() throws IOException {
        Parent dodavanjeNoveOsobeFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNoveOsobe.fxml"));
        Scene dodavanjeNoveOsobeScene = new Scene(dodavanjeNoveOsobeFrame, 600, 400);
        Main.getMainStage().setScene(dodavanjeNoveOsobeScene);
    }

}
