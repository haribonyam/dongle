package com.dongle.dongle.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service

public class SgisServiceImpl  implements  SgisService{

    @Value("${sgis.api.key}")
    private String apiKey;
    //주소api만 사용할 예정
    private final String url = "https://sgisapi.kostat.go.kr/OpenAPI3/addr/stage.json";

    private final RestTemplate restTemplate;

    public SgisServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public String getSidoInfo() {
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("accessToken",apiKey)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }

    @Override
    public String getSggInfo(String sido) {
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("accessToken",apiKey)
                .queryParam("cd", sido)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }
}
