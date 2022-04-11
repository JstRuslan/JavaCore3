package gb.java2.chatapp.controllers;


import gb.java2.chatapp.ChatApp;
import gb.java2.chatapp.models.History;
import gb.java2.chatapp.models.Network;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import gb.java2.server.server.models.Command;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatAppController {
    @FXML
    private TextArea fieldChat;

    @FXML
    private TextField fieldMsg;


    @FXML
    private ListView<String> listViewUsers;

    @FXML
    private Label labelNickname;


    String strDate = new SimpleDateFormat("hh:mm:ss").format(new Date());
    private Network network;
    private History history;
    private String selectedRecipient;

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void setFieldChat(String str) {
        this.fieldChat.appendText(str);
    }

    public void setNickName(String username) {
        labelNickname.setText(username);
    }


    public void setHistory(History history) {
        this.history = history;
    }

    public void initialize() {

        listViewUsers.setCellFactory(lv -> {
            MultipleSelectionModel<String> selectionModel = listViewUsers.getSelectionModel();
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                listViewUsers.requestFocus();
                if (!cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (selectionModel.getSelectedIndices().contains(index)) {
                        selectionModel.clearSelection(index);
                        selectedRecipient = null;
                    }
                    else {
                        selectionModel.select(index);
                        selectedRecipient = cell.getItem();
                    }
                    event.consume();
                }
            });
            return cell;
        });
    }

    public synchronized void addUserFromList(String username) {
        listViewUsers.getItems().add(username);
    }

    public synchronized void removeUserFromList(String username) {
        listViewUsers.getItems().remove(username);
    }

    public synchronized void updateUserList(ArrayList<String> userList) {
        listViewUsers.getItems().addAll(userList);
    }

    @FXML
    void closeApp() {
        network.sendMsg(Command.END_CLIENT_CMD_PREFIX);
        System.exit(0);
    }

    @FXML
    void changeNick(ActionEvent event) {

    }

    @FXML
    void aboutInfo(ActionEvent event) {

        try {
            AboutController.showAboutWindow();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void sendMsg() {
        String msg = fieldMsg.getText().trim();
        fieldMsg.clear();
        if (msg.isEmpty()) {
            return;
        }
        if (selectedRecipient != null) {
            network.sendMsg(Command.PRIVATE_MSG_CMD_PREFIX + " " + selectedRecipient + " " + msg);
        }
        else {
            network.sendMsg(msg);
        }
        appendMsg(msg);
    }

    @FXML
    void pressEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendMsg();
        }
    }

    @FXML
    void clearFieldHistory(ActionEvent event) {
        clearFieldChat();
        history.clearHistory();

    }

    @FXML
    void clearFieldChat() {
        fieldChat.clear();
    }

    public synchronized void appendMsg(String msg) {
        history.writeFileHistory(strDate, msg);

        fieldChat.appendText(">" + strDate + "<");
        fieldChat.appendText(System.lineSeparator());
        fieldChat.appendText(msg);
        fieldChat.appendText(System.lineSeparator());
    }
}