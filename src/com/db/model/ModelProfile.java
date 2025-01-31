package com.db.model;

public class ModelProfile {
    
    private int profileID;
    private int userID;
    private String userName;
    private String email;
    private String aboutMe;
    private String profilePicture;

    public ModelProfile(final int profileID, final int userID, final String userName, final String email) {
        this.profileID = profileID;
        this.userID = userID;
        this.userName = userName;
        this.email = email;
    }
    
    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(final int profileID) {
        this.profileID = profileID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(final int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(final String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(final String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
