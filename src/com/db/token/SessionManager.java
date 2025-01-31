package com.db.token;

import com.db.model.ModelProfile;
import java.io.*;
import java.util.Properties;

public class SessionManager {
    private static final String SESSION_FILE = "session.properties";

    private SessionManager() {}
 
    public static void saveSession(final ModelProfile profile) {
        final Properties properties = new Properties();
        properties.setProperty("ProfileID", String.valueOf(profile.getProfileID()));
        properties.setProperty("AccountID", String.valueOf(profile.getUserID()));
        properties.setProperty("Username", profile.getUserName());
        properties.setProperty("Email", profile.getEmail());
        properties.setProperty("AboutMe", profile.getAboutMe());
        properties.setProperty("ProfilePictureFileDIR", profile.getProfilePicture());
        
        try (final OutputStream output = new FileOutputStream(SESSION_FILE)) {
            properties.store(output, "User Login Session");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ModelProfile getSessionUser() {
        final File file = new File(SESSION_FILE);
        if (!file.exists()) return null;
        
        final Properties properties = new Properties();
        try (final InputStream input = new FileInputStream(SESSION_FILE)) {
            properties.load(input);
            final int profileID = Integer.parseInt(properties.getProperty("ProfileID", "-1"));
            if (profileID != -1) {
                final int accountID = Integer.parseInt(properties.getProperty("AccountID", "-1"));
                final String userName = properties.getProperty("Username", "guest");
                final String email = properties.getProperty("Email", "null@gmail.com");
                final String aboutMe = properties.getProperty("AboutMe", "");
                final String profilePicture = properties.getProperty("ProfilePictureFileDIR", "");
                final ModelProfile profile = new ModelProfile(profileID, accountID, userName, email);
                profile.setAboutMe(aboutMe);
                profile.setProfilePicture(profilePicture);
                return profile;
            }
        } catch (IOException e) {
            System.err.println("Something went wrong while reading the file -> SessionManager line 48");
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static void clearSession() {
        File file = new File(SESSION_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}

