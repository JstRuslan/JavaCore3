package gb.java2.server;

import gb.java2.server.server.MyServer;

import java.io.IOException;

public class ServerApp {
    public static final int DEFAULT_PORT = 8186;

    public static void main(String[] args) {

        try {
            new MyServer(DEFAULT_PORT).start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
