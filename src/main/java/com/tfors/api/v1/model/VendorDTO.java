package com.tfors.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tfors.domain.VendorComment;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class VendorDTO {

    private Long id;
    @ApiModelProperty(value = "Name of the Vendor.", required = true)
    private String name;
    @ApiModelProperty(value = "Description of the Vendor")
    private String description;
    @ApiModelProperty(value = "Number of stars")
    private Integer stars;
    @ApiModelProperty(value = "Coordinates of vendor locale")
    private String localization;
    @ApiModelProperty(value = "Comments of the users")
    private Set<VendorCommentDTO> comments = new HashSet<>();


    @Lob
    @ApiParam(hidden = true)
    private Byte[] image;

    @ApiModelProperty(value = "Vendors url.")
    @JsonProperty("vendor_url")
    private String vendorUrl;
}
