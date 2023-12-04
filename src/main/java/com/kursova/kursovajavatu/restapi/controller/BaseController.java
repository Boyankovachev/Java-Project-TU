package com.kursova.kursovajavatu.restapi.controller;

import com.kursova.kursovajavatu.restapi.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @Autowired
    private BaseService baseService;


}
