package com.tfors.api.v1.mapper;


import com.tfors.api.v1.model.VendorCommentDTO;

import com.tfors.domain.VendorComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface VendorCommentMapper {

    VendorCommentMapper INSTANCE = Mappers.getMapper(VendorCommentMapper.class);

    @Mapping(source = "description", target = "description")
    VendorCommentDTO vendorCommentToVendorCommentDTO(VendorComment vendorComment);
    @Mapping(source = "description", target = "description")
    VendorComment vendorCommentDTOToVendorComment(VendorCommentDTO vendorCommentDTO);
}
