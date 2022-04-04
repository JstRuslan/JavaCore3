package gb.java2.chatapp.controllers;

import gb.java2.chatapp.ChatApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutController {

    public static void showAboutWindow() throws IOException {
        Stage aboutStage = new Stage();
        FXMLLoader aboutLoader = new FXMLLoader(ChatApp.class.getResource("About-view.fxml"));
        aboutStage.setScene(new Scene(aboutLoader.load(), 250, 100));
        aboutStage.setTitle("About");
        aboutStage.initModality(Modality.APPLICATION_MODAL);
//        aboutStage.initOwner(((Node)event.getSource()).getScene().getWindow());
        aboutStage.showAndWait();
    }
}
