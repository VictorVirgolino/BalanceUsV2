package ultrasomma;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class SecondaryController implements Initializable {

    //Afrafep
    @FXML
    private TextField quantG1;
    @FXML
    private TextField totalG1;
    @FXML
    private TextField quantL1;
    @FXML
    private TextField totalL1;
    @FXML
    private TextField quantV1;
    @FXML
    private TextField totalV1;

    //Caixa
    @FXML
    private TextField quantG2;
    @FXML
    private TextField totalG2;
    @FXML
    private TextField quantL2;
    @FXML
    private TextField totalL2;
    @FXML
    private TextField quantV2;
    @FXML
    private TextField totalV2;

    //Cassi
    @FXML
    private TextField quantG3;
    @FXML
    private TextField totalG3;
    @FXML
    private TextField quantL3;
    @FXML
    private TextField totalL3;
    @FXML
    private TextField quantV3;
    @FXML
    private TextField totalV3;

    //Embrapa
    @FXML
    private TextField quantG4;
    @FXML
    private TextField totalG4;
    @FXML
    private TextField quantL4;
    @FXML
    private TextField totalL4;
    @FXML
    private TextField quantV4;
    @FXML
    private TextField totalV4;

    //Unimed
    @FXML
    private TextField quantG5;
    @FXML
    private TextField filmeG5;
    @FXML
    private TextField totalG5;
    @FXML
    private TextField quantL5;
    @FXML
    private TextField filmeL5;
    @FXML
    private TextField totalL5;
    @FXML
    private TextField quantV5;
    @FXML
    private TextField filmeV5;
    @FXML
    private TextField totalV5;

    //Ultrasomma
    @FXML
    private TextField quantG6;
    @FXML
    private TextField totalG6;
    @FXML
    private TextField quantL6;
    @FXML
    private TextField totalL6;
    @FXML
    private TextField quantV6;
    @FXML
    private TextField totalV6;





    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");

    }

    public void insertData(List<List<String>> data)  {
        //lists
        ArrayList<String> listAfrafep = (ArrayList<String>) data.get(0);
        ArrayList<String> listCaixa = (ArrayList<String>) data.get(1);
        ArrayList<String> listCassi = (ArrayList<String>) data.get(2);
        ArrayList<String> listEmbrapa = (ArrayList<String>) data.get(3);
        ArrayList<String> listUnimed = (ArrayList<String>) data.get(4);
        ArrayList<String> listUltrasomma = (ArrayList<String>) data.get(5);


        //Afrafep
        quantG1.setText(listAfrafep.get(0));
        totalG1.setText(listAfrafep.get(1));
        quantL1.setText(listAfrafep.get(2));
        totalL1.setText(listAfrafep.get(3));
        quantV1.setText(listAfrafep.get(4));
        totalV1.setText(listAfrafep.get(5));

        //Caixa
        quantG2.setText(listCaixa.get(0));
        totalG2.setText(listCaixa.get(1));
        quantL2.setText(listCaixa.get(2));
        totalL2.setText(listCaixa.get(3));
        quantV2.setText(listCaixa.get(4));
        totalV2.setText(listCaixa.get(5));

        //Cassi
        quantG3.setText(listCassi.get(0));
        totalG3.setText(listCassi.get(1));
        quantL3.setText(listCassi.get(2));
        totalL3.setText(listCassi.get(3));
        quantV3.setText(listCassi.get(4));
        totalV3.setText(listCassi.get(5));

        //Embrapa
        quantG4.setText(listEmbrapa.get(0));
        totalG4.setText(listEmbrapa.get(1));
        quantL4.setText(listEmbrapa.get(2));
        totalL4.setText(listEmbrapa.get(3));
        quantV4.setText(listEmbrapa.get(4));
        totalV4.setText(listEmbrapa.get(5));

        //Unimed
        quantG5.setText(listUnimed.get(0));
        filmeG5.setText(listUnimed.get(1));
        totalG5.setText(listUnimed.get(2));
        quantL5.setText(listUnimed.get(3));
        filmeL5.setText(listUnimed.get(4));
        totalL5.setText(listUnimed.get(5));
        quantV5.setText(listUnimed.get(6));
        filmeV5.setText(listUnimed.get(7));
        totalV5.setText(listUnimed.get(8));

      //  Ultrasomma
        quantG6.setText(listUltrasomma.get(0));
        totalG6.setText(listUltrasomma.get(1));
        quantL6.setText(listUltrasomma.get(2));
        totalL6.setText(listUltrasomma.get(3));
        quantV6.setText(listUltrasomma.get(4));
        totalV6.setText(listUltrasomma.get(5));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}