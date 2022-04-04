module gb.lesson4.chatapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.xerial.sqlitejdbc;
    requires java.sql;


    opens gb.java2.chatapp to javafx.fxml;
    exports gb.java2.chatapp;
    exports gb.java2.chatapp.controllers;
    opens gb.java2.chatapp.controllers to javafx.fxml;
}