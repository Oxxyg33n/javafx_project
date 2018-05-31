package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class RootLayoutController {

    //Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("Java project done by Aleksandr Logvinenko");
        alert.setContentText("Search, delete and add new contacts\nUpdate emails for specific contact");
        alert.show();
    }
}