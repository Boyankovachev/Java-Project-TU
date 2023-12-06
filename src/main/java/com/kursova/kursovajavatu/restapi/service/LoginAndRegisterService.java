package com.kursova.kursovajavatu.restapi.service;

import com.kursova.kursovajavatu.users.UserManager;
import com.kursova.kursovajavatu.users.sub.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginAndRegisterService {

    public String createUser(JSONObject jsonObject){
        /*
            Create user and add to database
         */
        try {
            if (!jsonObject.getString("password").equals(jsonObject.getString("password2"))) {
                return "Passwords don't match";
            }

            // TO DO: Implement...

            return "Account created successfully!";

        }catch (JSONException jsonException){
            return "Missing input data!";
        }
    }

    public User getUserByEmail(String email){
        //returns the first user found with matching name
        // (for future) - fix when 2 identical names

        // TO DO: add database connection and add this
        return null;
    }



    public JSONObject HtmlFromStringToJson(String htmlString){
        /*
        Converts HTML from submitted data to JSONObject
         */
        JSONObject jsonObject = new JSONObject();
        for(String string: htmlString.split("&")){
            String[] temp = string.split("=");
            if(temp.length>1) {
                jsonObject.put(temp[0], temp[1]);
            }
        }
        return jsonObject;
    }

    public String removeCharFromHtmlFormData(String string){
        string = string.replace("+", " ");
        return string;
    }


}
