package com.tfors.services.impl;

import com.tfors.services.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class MapServiceImpl implements MapService {


    private static final String SUBSCRIPTION_KEY = "hOuX9ZyOjkupvrbaIXzOXGykm-GqAPE7mVceUdmZb5k";


    public ResponseEntity<String> generateJsonMapWrapper(String startCoordinate, String endCoordinate){
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder resourceUrl = new StringBuilder("https://atlas.microsoft.com/route/directions/" +
                "json?subscription-key=");
        resourceUrl.append(SUBSCRIPTION_KEY)
                .append("&api-version=1.0&query=")
                .append(startCoordinate)
                .append(":")
                .append(endCoordinate);
        ResponseEntity<String> response
                = restTemplate.getForEntity(resourceUrl.toString(), String.class);

        return response;
    }




}
