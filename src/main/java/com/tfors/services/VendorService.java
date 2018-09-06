package com.tfors.services;

import com.tfors.api.v1.model.VendorDTO;
import com.tfors.api.v1.model.VendorListDTO;

import java.util.List;


public interface VendorService {

    List<VendorDTO> getAllVendors();
    List<VendorDTO> generateVendorsInRange(String startCoordinate, String range);
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO vendorDTO);
    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);
    void deleteVendorById(Long id);

}
