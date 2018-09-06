package com.tfors.services.impl;

import com.tfors.api.v1.mapper.CustomerMapper;
import com.tfors.api.v1.model.CustomerDTO;
import com.tfors.controllers.v1.CustomerController;
import com.tfors.domain.Customer;
import com.tfors.exceptions.ResourceNotFoundException;
import com.tfors.repositories.CustomerRepository;
import com.tfors.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository
                .findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return  customerDTO;})
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        return customerRepository.findById(id).map(customer -> {
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
            return customerDTO;
        }).orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public CustomerDTO crateNewCustomer(CustomerDTO customerDTO) {

        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customerToSave) {

        Customer savedCustomer = customerRepository.save(customerToSave);
        CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnedDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

        return returnedDTO;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return  saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {

        return customerRepository.findById(id).map(customer -> {

            if(customerDTO.getFirstName() != null){
                customer.setFirstName(customerDTO.getFirstName());
            }
            if(customerDTO.getLastName() != null){
                customer.setLastName(customerDTO.getLastName());
            }
            CustomerDTO returnedDTO =  customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            returnedDTO.setCustomerUrl(getCustomerUrl(id));
            return returnedDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    private String getCustomerUrl(Long id){

        return CustomerController.BASE_URL + "/" + id;
    }
    @Override
    public void deleteCustomerById(Long id) {

        customerRepository.deleteById(id);
    }

}
