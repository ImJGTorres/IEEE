package Control;

import Modelo.Conversiones;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Gabriel Torres & Emerson Vera
 */
/**
 * Sample Skeleton for 'IEEE.fxml' Controller Class
 */
public class IEEEController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="chPrecision"
    private ChoiceBox<String> chPrecision; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConvertirDecIEEE"
    private Button cmdConvertirDecIEEE; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConvertirIEEEDec"
    private Button cmdConvertirIEEEDec; // Value injected by FXMLLoader

    @FXML // fx:id="txtIngreseDecimal"
    private TextField txtIngreseDecimal; // Value injected by FXMLLoader

    @FXML // fx:id="txtIngreseExponente"
    private TextField txtIngreseExponente; // Value injected by FXMLLoader

    @FXML // fx:id="txtIngreseMantisa"
    private TextField txtIngreseMantisa; // Value injected by FXMLLoader

    @FXML // fx:id="txtIngreseSigno"
    private TextField txtIngreseSigno; // Value injected by FXMLLoader

    @FXML // fx:id="txtMuestreDecimal"
    private TextField txtMuestreDecimal; // Value injected by FXMLLoader

    @FXML // fx:id="txtMuestreExponente"
    private TextField txtMuestreExponente; // Value injected by FXMLLoader

    @FXML // fx:id="txtMuestreMantisa"
    private TextField txtMuestreMantisa; // Value injected by FXMLLoader

    @FXML // fx:id="txtMuestreSigno"
    private TextField txtMuestreSigno; // Value injected by FXMLLoader

    Conversiones c = new Conversiones();

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo, contenido, ButtonType.CLOSE);
        alert.setTitle(titulo);
        alert.showAndWait();
    }

    void alertaIncorrecto() {
        mostrarAlerta(Alert.AlertType.ERROR, "ENTRADA INCORRECTA", "El numero no está en formato IEEE");
    }

    void alertaNoNum() {
        mostrarAlerta(Alert.AlertType.ERROR, "ENTRADA INCORRECTA", "Tiene que ingresar un numero");
    }

    @FXML
    void convertirDecIEEE(ActionEvent event) {
        if (!c.validarNumero(txtIngreseDecimal.getText())) {
            alertaNoNum();
        } else {
            String[] IEEE;
            if (chPrecision.getSelectionModel().getSelectedItem().equals("32 bits")) {
                IEEE = c.convertirAIEEE32(Double.parseDouble(txtIngreseDecimal.getText()));
            } else {
                IEEE = c.convertirAIEEE64(Double.parseDouble(txtIngreseDecimal.getText()));
            }
            txtMuestreSigno.setText(IEEE[0]);
            txtMuestreExponente.setText(IEEE[1]);
            txtMuestreMantisa.setText(IEEE[2]);
        }
    }

    @FXML
    void convertirIEEEDec(ActionEvent event) {
        String IEEE = txtIngreseSigno.getText() + txtIngreseExponente.getText() + txtIngreseMantisa.getText();
        if (IEEE.length() != 32 && IEEE.length() != 64) {
            alertaIncorrecto();
        } else {
            String Resultado = c.convertirDeIEEE(IEEE);
            txtMuestreDecimal.setText(Resultado);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert chPrecision != null : "fx:id=\"chPrecision\" was not injected: check your FXML file 'IEEE.fxml'.";
        assert cmdConvertirDecIEEE != null : "fx:id=\"cmdConvertirDecIEEE\" was not injected: check your FXML file 'IEEE.fxml'.";
        assert cmdConvertirIEEEDec != null : "fx:id=\"cmdConvertirIEEEDec\" was not injected: check your FXML file 'IEEE.fxml'.";
        assert txtIngreseDecimal != null : "fx:id=\"txtIngreseDecimal\" was not injected: check your FXML file 'IEEE.fxml'.";
        assert txtIngreseExponente != null : "fx:id=\"txtIngreseExponente\" was not injected: check your FXML file 'IEEE.fxml'.";
        assert txtIngreseMantisa != null : "fx:id=\"txtIngreseMantisa\" was not injected: check your FXML file 'IEEE.fxml'.";
        assert txtIngreseSigno != null : "fx:id=\"txtIngreseSigno\" was not injected: check your FXML file 'IEEE.fxml'.";
        assert txtMuestreDecimal != null : "fx:id=\"txtMuestreDecimal\" was not injected: check your FXML file 'IEEE.fxml'.";
        assert txtMuestreExponente != null : "fx:id=\"txtMuestreExponente\" was not injected: check your FXML file 'IEEE.fxml'.";
        assert txtMuestreMantisa != null : "fx:id=\"txtMuestreMantisa\" was not injected: check your FXML file 'IEEE.fxml'.";
        assert txtMuestreSigno != null : "fx:id=\"txtMuestreSigno\" was not injected: check your FXML file 'IEEE.fxml'.";

        initChoiceBox();
        chPrecision.getSelectionModel().clearAndSelect(0);
    }

    private void initChoiceBox() {
        String[] precision = {"32 bits", "64 bits"};
        for (int i = 0; i < precision.length; i++) {
            chPrecision.getItems().add(precision[i]);
        }
    }
}
