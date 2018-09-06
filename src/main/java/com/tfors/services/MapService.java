package com.tfors.services;

import org.springframework.http.ResponseEntity;

public interface MapService {

    ResponseEntity<String> generateJsonMapWrapper(String startCoordinate, String endCoordinate);

}
