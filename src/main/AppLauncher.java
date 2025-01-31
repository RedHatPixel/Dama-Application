package main;

import app.frames.MainFrame;
import com.db.connection.DatabaseConnection;
import com.db.model.ModelProfile;
import com.db.service.ServiceUser;
import com.db.token.SessionManager;
import java.sql.SQLException;

public class AppLauncher {
    
    public static void main(String[] args) {
        try {
            DatabaseConnection.getInstance().connectToDatabase();
            final ModelProfile user = SessionManager.getSessionUser();
            final ServiceUser service = new ServiceUser();
            java.awt.EventQueue.invokeLater(() -> {
                if (user != null) {
                    try {
                        if (service.checkDuplicateUser(user.getUserName())) {
                            new MainFrame(user);
                        } else {
                            new MainFrame(null);
                        }
                    } catch (SQLException ex) {
                        System.out.println("Cannot find the account.");
                        new MainFrame(null);
                    }
                } else {
                    new MainFrame(null);
                }
            });
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
            new MainFrame(null);
        }
    }
}
