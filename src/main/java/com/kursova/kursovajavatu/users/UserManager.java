package com.kursova.kursovajavatu.users;


import com.kursova.kursovajavatu.users.sub.User;

import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager(){}
    public UserManager(List<User> users){
        this.users = users;
    }

    public void addUser(User user){
        users.add(user);
    }
    public void addUsers(List<User> newUsers){
        users.addAll(newUsers);
    }

    public List<User> getUsers(){
        return users;
    }

    public User getUserByName(String userName){
        /*
            return user by username
            return null if non found
         */
        int i;
        for(i=0; i<users.size(); i++){
            if(users.get(i).getUserName().equals(userName)){
                return users.get(i);
            }
        }
        return null;
    }

    public User getUserByEmail(String email){
        /*
            return user by email
            return null if non found
         */
        int i;
        for(i=0; i<users.size(); i++){
            if(users.get(i).getEmail().equals(email)){
                return users.get(i);
            }
        }
        return null;
    }

    public int getUserIndexByName(String userName){
        int i;
        for(i=0; i<users.size(); i++){
            if(users.get(i).getUserName().equals(userName)){
                return i;
            }
        }
        return 0;
    }

    public User getUserByIndex(int index){
        try {
            return users.get(index);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean isEmailPresent(String email){
        /*
            Check if email is already registered
         */
        for(User user: users){
            if(user.getEmail()!=null){
                if(user.getEmail().equals(email)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isUsernamePresent(String username){
        /*
            Check if username is already registered
         */
        for(User user: users){
            if(user.getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }
}
