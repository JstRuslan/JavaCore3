package gb.java2.chatapp.controllers;

import gb.java2.chatapp.ChatApp;
import gb.java2.chatapp.models.Network;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NewUserController {

    private static Stage newUserStage;

    @FXML
    private TextField fieldUsername;

    @FXML
    private TextField fieldLogin;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private PasswordField fieldConfirmPass;

    private String username;
    private String login;
    private String password;
    private static Network netWork;
    private static ChatApp chatapp;



    public static void showNewUserWindow(Network network, ChatApp chatApp) throws IOException {
        netWork = network;
        chatapp = chatApp;
        newUserStage = new Stage();
        FXMLLoader newUserLoader = new FXMLLoader(ChatApp.class.getResource("views/NewUser-view.fxml"));
        Scene scene = new Scene(newUserLoader.load(), 300, 300);
        newUserStage.setScene(scene);
        newUserStage.setTitle("Register user");
        newUserStage.setResizable(false);
        newUserStage.initModality(Modality.APPLICATION_MODAL);
        newUserStage.initStyle(StageStyle.UNIFIED);
        newUserStage.showAndWait();
    }

    @FXML
    void returnBack() {
        newUserStage.close();

    }

    @FXML
    void addUser() {
        if (confirmPass()) {
            clearFields();
            String regErrorMsg = netWork.sendRegMsg(login, password, username);

            if(regErrorMsg == null){
                new Alert(Alert.AlertType.INFORMATION, String.format("A new user '%s' registered!",username), ButtonType.OK).showAndWait();
                returnBack();
            } else {
                chatapp.showAlert("Error registration", regErrorMsg);

            }

        }
    }

    private boolean confirmPass() {
        password = fieldPassword.getText().trim();
        if (!checkEmpty() || password.length() == 0) {
            chatapp.showAlert("Error of filling fields", "All FIELDS must be fill");
            return false;
        }
        else if (password.equals(fieldConfirmPass.getText().trim())) {
            return true;
        }
        chatapp.showAlert("Error of the confirming password", "Fields PASSWORD must be equal");
        return false;
    }

    private boolean checkEmpty() {
        username = fieldUsername.getText().trim();
        login = fieldLogin.getText().trim();
        if (username.length() == 0 || login.length() == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    private void clearFields(){
        fieldUsername.clear();
        fieldLogin.clear();
        fieldPassword.clear();
        fieldConfirmPass.clear();
    }

    public void showRegAlert(String title, String errorMsg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(errorMsg);
        alert.show();
    }
}
