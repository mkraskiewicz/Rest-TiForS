package com.tfors.services.impl;

import com.tfors.api.v1.mapper.VendorMapper;
import com.tfors.api.v1.model.VendorDTO;
import com.tfors.api.v1.model.VendorListDTO;
import com.tfors.controllers.v1.VendorController;
import com.tfors.domain.Vendor;
import com.tfors.exceptions.ResourceNotFoundException;
import com.tfors.repositories.VendorRepository;
import com.tfors.services.MapService;
import com.tfors.services.VendorService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;
    private VendorMapper vendorMapper;
    private MapService mapService;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper,
                             MapService mapService) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
        this.mapService = mapService;
    }

    @Override
    public List<VendorDTO> getAllVendors() {

        return vendorRepository.findAll().stream().map(vendor -> {
            VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
            vendorDTO.setVendorUrl(getCustomerUrl(vendor.getId()));
            return vendorDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {

        return  vendorRepository.findById(id).map(vendor ->{
            VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
            vendorDTO.setVendorUrl(getCustomerUrl(id));
            return vendorDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendorToSave){

        Vendor savedVendor = vendorRepository.save(vendorToSave);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        vendorDTO.setVendorUrl(getCustomerUrl(savedVendor.getId()));

        return vendorDTO;
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

       return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTOToSave) {

        Vendor vendorToSave = vendorMapper.vendorDTOToVendor(vendorDTOToSave);
        vendorToSave.setId(id);

        return  saveAndReturnDTO(vendorToSave);

    }

    @Override
    public void deleteVendorById(Long id) {

        vendorRepository.deleteById(id);
    }

    private String getCustomerUrl(Long id){

        return VendorController.BASE_URL + "/" + id;
    }

    public List<VendorDTO> generateVendorsInRange(String startCoordinate, String range){

        List<VendorDTO> vendorListDTO = getAllVendors();

        return vendorListDTO.stream()
                .filter(p -> Long.valueOf(range) >= computeDistance(startCoordinate, p.getLocalization()))
                .collect(Collectors.toList());

    }

    private Long computeDistance(String startCoordinate, String endCoordinate){

        //TODO horrible callculation, I need to improve it by ObjectMapper with Jackson
        String generatedMap = mapService.generateJsonMapWrapper(startCoordinate,endCoordinate).getBody();
        String distance = new JSONObject(generatedMap).getJSONArray("routes")
                .getJSONObject(0).getJSONObject("summary")
                .get("lengthInMeters").toString();

        return Long.valueOf(distance);
    }
}
