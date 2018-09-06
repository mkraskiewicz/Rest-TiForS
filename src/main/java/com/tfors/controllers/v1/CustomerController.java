package com.tfors.controllers.v1;

import com.tfors.api.v1.model.CustomerDTO;
import com.tfors.api.v1.model.CustomerListDTO;
import com.tfors.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Api(description = "This is my Customer controller")
@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This will get us a list of customers.", notes = "These are some notes about API.")
    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers(){

        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAllCustomers()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerByID(@PathVariable("id") Long id){

        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){

        return new ResponseEntity<CustomerDTO>(customerService.crateNewCustomer(customerDTO),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id,
                                                      @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.saveCustomerByDTO(id, customerDTO),
                HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable("id") Long id,
                                                     @RequestBody CustomerDTO customerDTO) {

        return new ResponseEntity<CustomerDTO>(customerService.patchCustomer(id, customerDTO),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") Long id){

        customerService.deleteCustomerById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
