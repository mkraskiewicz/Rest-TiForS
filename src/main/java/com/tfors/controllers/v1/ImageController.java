package com.tfors.controllers.v1;



import com.tfors.api.v1.model.VendorDTO;
import com.tfors.services.ImageService;
import com.tfors.services.VendorService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Slf4j
@Controller
@RequestMapping(VendorController.BASE_URL)
public class ImageController {

    private final ImageService imageService;
    private final VendorService vendorService;

    public ImageController(ImageService imageService, VendorService vendorService) {
        this.imageService = imageService;
        this.vendorService = vendorService;
    }

    @GetMapping("{vendorId}/image")
    public String showUploadForm(@PathVariable String vendorId, Model model){
        model.addAttribute("vendor", vendorService.getVendorById(Long.valueOf(vendorId)));
        return "upload";
    }


    @ApiOperation(value = "This will save a image for certain vendor.", notes = "These are some notes about API.")
    @PostMapping("{vendorId}/image")
    public String handleImagePost(@PathVariable String vendorId, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(Long.valueOf(vendorId),file);
        return "redirect:" + VendorController.BASE_URL + "/" + vendorId + "/show";
    }

    @GetMapping("{vendorId}/vendorimage")
    public void renderImageFromDB(@PathVariable String vendorId, HttpServletResponse response) throws IOException{

        VendorDTO vendorDTO = vendorService.getVendorById(Long.valueOf(vendorId));
        try {
            byte[] byteArray = new byte[vendorDTO.getImage().length];
            int itr = 0;
            for(Byte wrappedByte : vendorDTO.getImage()){
                byteArray[itr++] = wrappedByte;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        } catch (NullPointerException e){
            System.out.println("No picture has been found for Vendor id:" + vendorId);
        }
    }

}
