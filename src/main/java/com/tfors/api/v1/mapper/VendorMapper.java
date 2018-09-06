package com.tfors.api.v1.mapper;

import com.tfors.api.v1.model.VendorDTO;
import com.tfors.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(uses = { VendorCommentMapper.class })
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(source = "name", target = "name")
    VendorDTO vendorToVendorDTO(Vendor vendor);
    @Mapping(source = "name", target = "name")
    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
