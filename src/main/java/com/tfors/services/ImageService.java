package com.tfors.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long vendorId, MultipartFile file);

}