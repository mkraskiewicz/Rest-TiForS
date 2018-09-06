package com.tfors.api.v1.mapper;

        import com.tfors.api.v1.model.CustomerDTO;
        import com.tfors.domain.Customer;
        import org.mapstruct.Mapper;
        import org.mapstruct.Mapping;
        import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "firstName", target = "firstName")
    CustomerDTO customerToCustomerDTO(Customer customer);
    @Mapping(source = "firstName", target = "firstName")
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
