package com.manhpd.hello_world;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * https://openjfx.io/openjfx-docs/
 *
 */
public class HelloWorldApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
//            BorderPane root = new BorderPane();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            Scene scene = new Scene(root, 800, 400);
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setTitle("Hello world");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        stage.setScene(new Scene(new Pane(), 800, 600));
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
