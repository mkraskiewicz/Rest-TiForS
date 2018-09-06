package com.tfors.controllers.v1;

import com.tfors.api.v1.model.VendorListDTO;
import com.tfors.services.MapService;
import com.tfors.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Api(description = "Controller responsible for working with Microsoft Maps.")
@Controller
@RequestMapping(MapController.BASE_URL)
public class MapController {

    public static final String BASE_URL = "/api/v1/maps";
    private final MapService mapService;
    private final VendorService vendorService;

    public MapController(MapService mapService, VendorService vendorService) {
        this.mapService = mapService;
        this.vendorService = vendorService;
    }

    @GetMapping("{startCoordinate}/{endCoordinate}")
    public ResponseEntity<String> getVendorById(@PathVariable("startCoordinate") String startCoordinate,
                                                @PathVariable("endCoordinate") String endCoordinate){
        //vendorService.generateVendorsInRange(startCoordinate,endCoordinate);
        ResponseEntity<String> jsonMap = mapService.generateJsonMapWrapper(startCoordinate,endCoordinate);
        return jsonMap;
    }
}

//    @GetMapping("closest/({startCoordinate}/{endCoordinate})/{range}")
//    public ResponseEntity<VendorListDTO> getVendorById(@PathVariable("startCoordinate") String startCoordinate,
//                                                       @PathVariable("endCoordinate") String endCoordinate,
//                                                       @PathVariable("range}") String range){
//        VendorListDTO vendorListDTO = new VendorListDTO();
//
//        vendorService.getAllVendors().stream().forEach( vendorDTO -> );
//    }
