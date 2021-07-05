package org.johngregg.connector;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class MyController {

    @GetMapping("/open")
    public ResponseEntity<String> open(@RequestParam("host") String host) throws IOException {
        URL url = new URL("https://" + host);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String s = urlConnection.getContent().toString();
        urlConnection.disconnect();
        return ResponseEntity.ok(s);
    }
}
