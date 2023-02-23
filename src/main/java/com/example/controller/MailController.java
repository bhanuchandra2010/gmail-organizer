package com.example.controller;

import com.example.config.GmailConfig;
import com.example.services.GmailService;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListMessagesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
public class MailController {

    @Autowired
    GmailConfig gmailConfig;

    private static GmailService _gmailService;

    public MailController(GmailService gmailService) {
        _gmailService = gmailService;
    }

    @GetMapping("/label")
    public List<Label> health() throws IOException, GeneralSecurityException {
        return _gmailService.getLabel();
    }

    @GetMapping("/mail")
    public ListMessagesResponse get() throws GeneralSecurityException, IOException {
        return _gmailService.getMail();
    }
}
