package com.tfors.controllers.v1;

import com.tfors.api.v1.model.VendorDTO;
import com.tfors.api.v1.model.VendorListDTO;
import com.tfors.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Api(description = "Controller responsible for Vendors.")
@Controller
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Returns back list of all vendors.", notes = "It is not limited by anything. (TODO)")
    @GetMapping
    public ResponseEntity<VendorListDTO> getAllVendors(){

        return new ResponseEntity<VendorListDTO>(new VendorListDTO(vendorService.getAllVendors()),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Returns back list of all vendors filtered by provided range. Localization is the" +
            "localization of the user.",
            notes= "for example get http://localhost:8080/api/v1/vendors/nearby/52.50936,13.42938/1100000 " +
                    "will filter out third vendor.")
    @GetMapping("nearby/{localization}/{range}")
    public ResponseEntity<VendorListDTO> getAllVendorsInRange(@PathVariable String localization,
                                                              @PathVariable String range){

        //TODO this one works very slowly, it needs to be improved.
        return new ResponseEntity<VendorListDTO>(
                new VendorListDTO(vendorService.generateVendorsInRange(localization,range)), HttpStatus.OK);
    }

    @GetMapping("{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("vendor",vendorService.getVendorById(new Long(id)));
        return "show";
    }

    @ApiOperation(value = "Returns Vendor saved by ID provided in path.")
    @GetMapping("{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable("id") Long id){

        return new ResponseEntity<VendorDTO>(vendorService.getVendorById(id),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a new Vendor.")
    @PostMapping
    public ResponseEntity<VendorDTO> createNewVendor(@RequestBody VendorDTO vendorDTO){

        return new ResponseEntity<VendorDTO>(vendorService.createNewVendor(vendorDTO),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates existing Vendor.")
    @PutMapping("{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable("id") Long id,
                                                  @RequestBody VendorDTO vendorDTO){

        return new ResponseEntity<VendorDTO>(vendorService.saveVendorByDTO(id, vendorDTO),
                HttpStatus.OK);
    }



    @ApiOperation(value = "Delete a Vendor.")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable("id") Long id){

        vendorService.deleteVendorById(id);
        return  new ResponseEntity<Void>(HttpStatus.OK);
    }

}
