package com.example.practiceoauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final String REQUEST_BASE_URL = "https://github.com/login/oauth/authorize";

    private final String EXCHANGE_TOKEN_URL = "https://github.com/login/oauth/access_token";

    @Value("${CLIENT_ID}")
    private String CLIENT_ID;

    @Value("${CLIENT_SECRET}")
    private String CLIENT_SECRET;

    @Value("${STATE}")
    private String STATE;


    public String createGithubAuthTokenUri() {
        return UriComponentsBuilder.fromHttpUrl(REQUEST_BASE_URL)
                .queryParam("client_id", CLIENT_ID)
                .queryParam("state", STATE)
                .build().encode().toString();
    }

    public String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("client_secret", CLIENT_SECRET);
        requestBody.add("code", code);
        requestBody.add("state", STATE);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> accessToken =  restTemplate.postForEntity(EXCHANGE_TOKEN_URL, request, String.class);

        return accessToken.getBody();
    }

}
