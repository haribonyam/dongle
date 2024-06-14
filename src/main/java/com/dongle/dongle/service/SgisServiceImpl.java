package com.dongle.dongle.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class SgisServiceImpl implements SgisService {

    @Value("${sgis.api.consumer_key}")
    private String consumerKey;

    @Value("${sgis.api.consumer_secret}")
    private String consumerSecret;

    @Value("${sgis.api.access_token_url}")
    private String accessTokenUrl;

    @Value("${sgis.api.key}")
    private String accessToken;

    private final RestTemplate restTemplate;

    public SgisServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getSidoInfo() {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://sgisapi.kostat.go.kr/OpenAPI3/addr/stage.json")
                .queryParam("accessToken", accessToken)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }

    @Override
    public String getSggInfo(String cd) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://sgisapi.kostat.go.kr/OpenAPI3/addr/stage.json")
                .queryParam("accessToken", accessToken)
                .queryParam("cd", cd)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }

    @Override
    @Scheduled(fixedRate = 3600000) // 매 시간마다 실행 (1시간 = 3600000밀리초)
    public void refreshApiKeyIfNeeded() {
        try {
            // 새로운 키를 받기 위한 요청을 보냅니다.
            URI uri = UriComponentsBuilder.fromHttpUrl(accessTokenUrl)
                    .queryParam("consumerKey", consumerKey)
                    .queryParam("consumerSecret", consumerSecret)
                    .build()
                    .toUri();

            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

            this.accessToken = parseAccessTokenFromResponse(response.getBody());
        } catch (RestClientException e) {

            e.printStackTrace();
        }
    }

    private String parseAccessTokenFromResponse(String responseBody) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            return rootNode.path("result").path("accessToken").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}