package com.fc.fandom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommPageController {

    @GetMapping("/login")
    public String loginPage() {
        return "Member/login";
    }
}
