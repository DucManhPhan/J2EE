package com.manhpd.hello_world;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public void btnHelloHandler(ActionEvent actionEvent) {
        System.out.println("Hello world with JavaFX");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
