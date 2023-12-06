package com.kursova.kursovajavatu.restapi.controller;

import com.kursova.kursovajavatu.restapi.service.LoginAndRegisterService;
import com.kursova.kursovajavatu.users.sub.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;




@Controller
public class LoginAndRegisterController {

    @Autowired
    private final LoginAndRegisterService loginAndRegisterService;

    public LoginAndRegisterController(){
        loginAndRegisterService = new LoginAndRegisterService();
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(@ModelAttribute("loginstatus") String loginStatus, Model model){
        if(loginStatus!=null){
            model.addAttribute("message", loginStatus);
        }
        return "login";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RedirectView getRegister(@RequestBody String inputString, RedirectAttributes attributes){

        JSONObject jsonObject = loginAndRegisterService.HtmlFromStringToJson(inputString);
        System.out.println(inputString);
        String result = loginAndRegisterService.createUser(jsonObject);

        if(!result.equals("Account created successfully!")){
            attributes.addFlashAttribute("message", result);
            attributes.addFlashAttribute("isMessage", true);
            return new RedirectView("register");
        }
        else {
            User user = loginAndRegisterService.getUserByEmail(jsonObject.getString("email").replace("%40", "@"));

            Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getEmail(),
                    user.getPasswordHash(),
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(newAuth);

            attributes.addFlashAttribute("user", user);
            return new RedirectView("user");
        }
    }

}
