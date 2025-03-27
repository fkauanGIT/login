package com.app.eazy_bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Noticescontroller {

    @GetMapping("/notices")
    public String getNotices() {
        return "Notices";
    }
}
