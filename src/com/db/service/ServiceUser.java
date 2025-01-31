package com.db.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.db.model.ModelLogin;
import com.db.model.ModelUser;
import java.text.DecimalFormat;
import java.util.Random;

public class ServiceUser extends Service{
    
    public ServiceUser() {
        super();
    }
    
    public void deleteAllNonVerified() throws SQLException {
        try (final PreparedStatement ps = getConnection().prepareStatement(
                "DELETE FROM `account` WHERE Status='NonVerified'")) {
            ps.execute();
        }
    }
    
    public void deleteNonVerified(final int userID) throws SQLException {
        try (final PreparedStatement ps = getConnection().prepareStatement(
                "DELETE FROM `account` WHERE AccountID=? AND Status='NonVerified' limit 1")) {
            ps.setInt(1, userID);
            ps.execute();
        }
    }
    
    public boolean deleteVerifiedAccount(final int userID) throws SQLException {
        try (final PreparedStatement ps = getConnection().prepareStatement(
                "DELETE FROM `account` WHERE AccountID=? AND Status='Verified' limit 1")) {
            ps.setInt(1, userID);
            final int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    public ModelUser login(final ModelLogin login) throws SQLException {
        ModelUser data = null;
        try (final PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(
                "SELECT AccountID, Username, Email FROM `account` WHERE BINARY(Email)=? AND BINARY(Password)=? AND Status='Verified' limit 1;")) {
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getPassword());
            try (final ResultSet r = ps.executeQuery()) {
                if (r.next()) {
                    final int accountID = r.getInt(1);
                    final String userName = r.getString(2);
                    final String email = r.getString(3);
                    data = new ModelUser(accountID, userName, email, "-hidden-");
                    doneLogin(accountID);
                }
            }
        }
        return data;
    }
    
    public void doneLogin(final int userID) throws SQLException {
        try (final PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(
                "UPDATE `account` SET LastLogin=CURRENT_TIMESTAMP WHERE AccountID=? limit 1")) {
            ps.setInt(1, userID);
            ps.execute();
        }
    }
    
    public void insertUser(final ModelUser user) throws SQLException {
        final String code;
        final int userID;
        try (final PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(
                "INSERT INTO `account`(UserName, Password, Email, VerifyCode) VALUES (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            code = generateVerifyCode();
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, code);
            ps.execute();
            try (ResultSet r = ps.getGeneratedKeys()) {
                r.next();
                userID = r.getInt(1);
            }
        }
        user.setUserID(userID);
        user.setVerifyCode(code);
    }
    
    public String generateVerifyCode() throws SQLException {
        final DecimalFormat df = new DecimalFormat("0000000");
        final Random random = new Random();
        String code = df.format(random.nextInt(1000000)); // Random from 0 to 999999
        while (checkDuplicatedCode(code)) {
            code = df.format(random.nextInt(1000000));
        }
        return code;
    }
    
    private boolean checkDuplicatedCode(final String code) throws SQLException {
        boolean duplicate = false;
        try (final PreparedStatement ps = getConnection().prepareStatement(
                "SELECT AccountID FROM `account` WHERE VerifyCode=? limit 1")) {
            ps.setString(1, code);
            try (final ResultSet r = ps.executeQuery()) {
                if (r.next()) {
                    duplicate = true;
                }
            }
        }
        return duplicate;
    }
    
    public boolean checkAccountExist(final int userID) throws SQLException {
        boolean duplicate = false;
        try (final PreparedStatement ps = getConnection().prepareStatement(
                "SELECT Username, Email FROM `account` WHERE AccountID=? AND Status='Verified' limit 1")) {
            ps.setInt(1, userID);
            try (final ResultSet r = ps.executeQuery()) {
                if (r.next()) {
                    duplicate = true;
                }
            }
        }
        return duplicate;
    }
    
    public boolean checkDuplicateUser(final String user) throws SQLException {
        boolean duplicate = false;
        try (final PreparedStatement ps = getConnection().prepareStatement(
                "SELECT AccountID FROM `account` WHERE UserName=? AND Status='Verified' limit 1")) {
            ps.setString(1, user);
            try (final ResultSet r = ps.executeQuery()) {
                if (r.next()) {
                    duplicate = true;
                }
            }
        }
        return duplicate;
    }
    
    public boolean checkDuplicateEmail(final String email) throws SQLException {
        boolean duplicate = false;
        try (final PreparedStatement ps = getConnection().prepareStatement(
                "SELECT AccountID FROM `account` WHERE Email=? AND Status='Verified' limit 1")) {
            ps.setString(1, email);
            try (final ResultSet r = ps.executeQuery()) {
                if (r.next()) {
                    duplicate = true;
                }
            }
        }
        return duplicate;
    }
    
    public void doneVerify(final int userID) throws SQLException {
        try (final PreparedStatement ps = getConnection().prepareStatement(
                "UPDATE `account` SET VerifyCode='', Status='Verified' WHERE AccountID=? limit 1")) {
            ps.setInt(1, userID);
            ps.execute();
        }
    }
    
    public boolean verifyCodeWithUser(final int userID, final String code)  throws SQLException {
        boolean verify = false;
        try (final PreparedStatement ps = getConnection().prepareStatement(
                "SELECT AccountID FROM `account` WHERE AccountID=? AND VerifyCode=? limit 1")) {
            ps.setInt(1, userID);
            ps.setString(2, code);
            try (final ResultSet r = ps.executeQuery()) {
                if (r.next()) {
                    verify = true;
                }
            }
        }
        return verify;
    }
    
}
