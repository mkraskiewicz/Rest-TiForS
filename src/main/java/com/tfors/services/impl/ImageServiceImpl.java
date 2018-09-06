package com.tfors.services.impl;

import com.tfors.domain.Vendor;
import com.tfors.repositories.VendorRepository;
import com.tfors.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Maciej on 27/05/2018
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final VendorRepository vendorRepository;

    public ImageServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long vendorId, MultipartFile file) {

        try{
            Vendor vendor = vendorRepository.findById(vendorId).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int itr = 0;
            for(byte b : file.getBytes()){
                byteObjects[itr++] = b;
            }
            vendor.setImage(byteObjects);

            vendorRepository.save(vendor);
        }catch (IOException exception){
            log.error("Error occured", exception);
            exception.printStackTrace();
        }
    }
}
