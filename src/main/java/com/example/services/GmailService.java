package com.example.services;

import com.example.config.GmailConfig;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class GmailService {

    @Autowired
    GmailConfig gmailConfig;

    String user = "me";

    private Gmail createService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Gmail.Builder(HTTP_TRANSPORT, GsonFactory.getDefaultInstance(), gmailConfig.getCredentials(HTTP_TRANSPORT))
                .setApplicationName("Gmail API Java Quickstart")
                .build();
    }

    public List<Label> getLabel() throws GeneralSecurityException, IOException {
        var service = createService();

        ListLabelsResponse listResponse = service.users().labels().list(user).execute();
        return listResponse.getLabels();
    }

    public ListMessagesResponse getMail() throws GeneralSecurityException, IOException {
        var service = createService();
        return service.users().messages().list(user).execute();
    }

}
