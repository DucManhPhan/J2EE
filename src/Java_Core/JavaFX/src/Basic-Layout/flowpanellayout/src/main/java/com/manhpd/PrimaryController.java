package com.manhpd;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    @FXML
    private Button btnShowDateTime;

    @FXML
    private TextField txtDateTime;

    @FXML
    private void showDateTime() throws IOException {
        System.out.println("show-date-time button clicked");
        LocalDateTime now = LocalDateTime.now();

        // yyyy-MM-dd HH:mm:ss
        // dd/LLLL/yyyy HH:mm:ss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeNow = formatter.format(now);

        this.txtDateTime.setText(dateTimeNow);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // it can do nothing
    }

    public void isOk(ActionEvent actionEvent) {

    }

    public void displayName(ActionEvent actionEvent) {
    }

    public void clickRdBtn(ActionEvent actionEvent) {
    }

    public void clickCheckbox(ActionEvent actionEvent) {
    }
}
