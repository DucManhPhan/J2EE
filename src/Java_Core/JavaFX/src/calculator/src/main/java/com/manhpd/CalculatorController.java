package com.manhpd;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController  implements Initializable {

    public void onClick(ActionEvent actionEvent) {
        System.out.println("Clicked me.");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
