package com.tfors.api.v1.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class VendorCommentDTO {

    private Long id;
    private String description;
    private VendorDTO vendorDTO;

}
