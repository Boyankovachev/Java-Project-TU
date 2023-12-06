package com.kursova.kursovajavatu.users.sub;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class User {
    private String userName;
    private String passwordHash;
    private String salt;
    private String email;
    private boolean is2FactorAuthenticationRequired;
    private List<Notification> notifications = new ArrayList<>();
    private int userId;
    private boolean isEmailVerified;
    private final String pepper = "QtuSl0Z3RE";

    public User(){}
    public User(String userName, String passwordHash,String salt){
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }
    public User(int userId, String userName, String passwordHash,String salt, String email, Boolean is2fa){
        this.userId = userId;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.salt = salt;
        if(email != null){
            this.email = email;
        }
        if(is2fa != null){
            this.is2FactorAuthenticationRequired = is2fa;
        }
    }
    public User(int userId, String userName, String passwordHash,String salt ,String email,
                boolean is2FactorAuthenticationRequired, List<Notification> notifications){
        this.userId = userId;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.email = email;
        this.is2FactorAuthenticationRequired = is2FactorAuthenticationRequired;
        this.notifications = notifications;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }


    public boolean getIs2FactorAuthenticationRequired() {
        return is2FactorAuthenticationRequired;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setIs2FactorAuthenticationRequired(boolean is2FactorAuthenticationRequired) {
        this.is2FactorAuthenticationRequired = is2FactorAuthenticationRequired;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
    public void addNotification(Notification notification){
        this.notifications.add(notification);
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    public String getSalt(){
        return salt;
    }

    public int getUserId(){
        return userId;
    }
    public void setUserId(int id){
        this.userId = id;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public HashMap<String, String> generateSaltAndHash(String password){
        /*
            generate random salt
            add to user input password + the pepper
            use hashing
            return list 0 - password 1 - salt
         */
        //generate random salt
        Random r = new SecureRandom();
        byte[] salt = new byte[20];
        r.nextBytes(salt);
        String saltString = salt.toString();

        //generate hash
        String hash = Hashing.sha256()
                .hashString(password + saltString + pepper, StandardCharsets.UTF_8)
                .toString();

        //return result
        HashMap<String, String> result= new HashMap<>();
        result.put("hash", hash);
        result.put("salt", saltString);
        return result;
    }

    public boolean checkPassword(String inputPassword){
        //Check input password against actual hash coded password
        //hash user input + salt + pepper
        String newHash = Hashing.sha256()
                .hashString(inputPassword + salt + pepper, StandardCharsets.UTF_8)
                .toString();
        //compare to actual hash
        return newHash.equals(passwordHash);
    }

    public boolean removeNotificationByName(String name){
        //remove notification by name
        for(int i=0; i<notifications.size(); i++){
            if(notifications.get(i).getNotificationName().equals(name)){
                notifications.remove(i);
                return true;
            }
        }
        return false;
    }

}
