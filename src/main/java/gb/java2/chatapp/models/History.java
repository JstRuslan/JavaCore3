package gb.java2.chatapp.models;

import gb.java2.chatapp.controllers.ChatAppController;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class History {
    private File fileHistory;
    private static final int LIMIT_RECORDS = 10;
    private StringBuilder strBuilder;
    private List<String> listMsg = new ArrayList<>(LIMIT_RECORDS);

    public void initChatHistory(String username, ChatAppController chatAppController) {
        fileHistory = new File("src/main/resources/gb/java2/chatapp/history", username + ".txt");

        if (!fileHistory.exists()) {
            try {
                fileHistory.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            loadHistoryChat(chatAppController);
        }
    }

    public void loadHistoryChat(ChatAppController chatAppController) {
        String buffer = String.valueOf(readFileHistory());
        Collections.addAll(listMsg, buffer.split(">", 10));
        chatAppController.setFieldChat(buffer);
    }

    public StringBuilder readFileHistory() {
        strBuilder = new StringBuilder();

        try (FileReader fr = new FileReader(fileHistory)) {
            while (fr.ready()) {
                strBuilder.append((char) fr.read());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return strBuilder;
    }


    public void writeFileHistory(String date, String msg) {
        String record = String.format(">%s<%n%s%n", date, msg);

        if (listMsg.size() >= LIMIT_RECORDS) {
            listMsg.remove(0);
        }
        listMsg.add(record);


        try (FileWriter fw = new FileWriter(fileHistory);) {
            for (String s : listMsg) {
                fw.write(s);
            }
            fw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearHistory() {
        listMsg.clear();

        try (FileWriter fw = new FileWriter(fileHistory);) {
            fw.write("");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
