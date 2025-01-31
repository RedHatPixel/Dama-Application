package com.db.service;

import com.db.model.ModelProfile;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceProfile extends Service {
    
    public ServiceProfile() {
        super();
    }
   
    public void getAccountProfile(final ModelProfile profile) throws SQLException {
        try (final PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(
                "SELECT ProfileID, AboutMe, ProfilePicture FROM `profile` WHERE AccountID=? limit 1")) {
            ps.setInt(1, profile.getUserID());
            ps.execute();
            try (ResultSet r = ps.executeQuery()) {
                if (r.next()) {
                    final int profileID = r.getInt(1);
                    final String aboutMe = r.getString(2);
                    final String profilePicture = r.getString(3);
                    profile.setProfileID(profileID);
                    profile.setAboutMe(aboutMe);
                    profile.setProfilePicture(profilePicture);
                }
            }
        }
    }
    
    public void insertProfile(final ModelProfile profile) throws SQLException {
        final int profileID;
        try (final PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(
                "INSERT INTO `profile` (AccountID, AboutMe, ProfilePicture) VALUES (?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, profile.getUserID());
            ps.setString(2, "");
            ps.setString(3, "");
            ps.execute();
            try (ResultSet r = ps.getGeneratedKeys()) {
                r.next();
                profileID = r.getInt(1);
            }
        }
        profile.setProfileID(profileID);
        profile.setAboutMe("");
        profile.setProfilePicture("");
    }
    
    public boolean checkAccountProfileExist(final int accountID) throws SQLException {
        boolean exist = false;
        try (final PreparedStatement ps = getConnection().prepareStatement(
                "SELECT ProfileID FROM `profile` WHERE AccountID=? limit 1")) {
            ps.setInt(1, accountID);
            try (final ResultSet r = ps.executeQuery()) {
                if (r.next()) {
                    exist = true;
                }
            }
        }
        return exist;
    }
    
    public boolean updateProfile(final ModelProfile profile) throws SQLException {
        final String query = "UPDATE `account` JOIN `profile` ON account.AccountID = profile.AccountID SET "
                + "account.Username=?, profile.AboutMe=?, profile.ProfilePicture=? WHERE account.AccountID=? limit 1";
        try (final PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(query)) {
            ps.setString(1, profile.getUserName());
            ps.setString(2, profile.getAboutMe());
            ps.setString(3, profile.getProfilePicture());
            ps.setInt(4, profile.getUserID());
            final int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
