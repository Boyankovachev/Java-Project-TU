package com.kursova.kursovajavatu.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginAndRegisterController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(@ModelAttribute("loginstatus") String loginStatus, Model model){
        if(loginStatus!=null){
            model.addAttribute("message", loginStatus);
        }
        return "login";
    }

}
