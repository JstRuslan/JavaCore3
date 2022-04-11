package gb.java2.chatapp.controllers;

import gb.java2.chatapp.ChatApp;
import gb.java2.chatapp.models.Network;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AuthController {

    @FXML
    private TextField fieldLogin;

    @FXML
    private PasswordField fieldPassword;

    private Network network;

    private ChatApp chatApp;


    @FXML
    void addUserWindow() {
        chatApp.hideAuthWindow();
        try {
            NewUserController.showNewUserWindow(network, chatApp);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        chatApp.showAuthWindow();
    }


    @FXML
    void checkAuth() {
        String login = fieldLogin.getText().trim();
        String password = fieldPassword.getText().trim();

        if (login.length() == 0 || password.length() == 0) {
            chatApp.showAlert("Ошибка ввода при аутентификации", "Поля не должны быть пустыми!");
            fieldLogin.clear();
            fieldPassword.clear();
            return;
        }

        String authErrorMsg = network.sendAuthMsg(login, password);

        if(authErrorMsg == null){
            chatApp.showChatWindow();
        } else {
            chatApp.showAlert("Ошибка аутентификации", authErrorMsg);
            fieldLogin.clear();
            fieldPassword.clear();
        }

    }


    public void setNetwork(Network network) {
        this.network = network;
    }

    public void setChatApp(ChatApp chatApp) {
        this.chatApp = chatApp;
    }

}
