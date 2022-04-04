package gb.java2.server.server.authentication;

import java.sql.SQLException;

public interface AuthenticationService {
    String getUsernameByLoginAndPassword(String login, String password) throws SQLException;

    boolean addRecordDB(String username, String login, String password) throws SQLException;
    void startAuthentication();
    void endAuthentication();
    public void disconnectDB();
}
