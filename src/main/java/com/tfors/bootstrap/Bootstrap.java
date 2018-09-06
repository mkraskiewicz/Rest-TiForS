package com.tfors.bootstrap;

import com.tfors.domain.Customer;
import com.tfors.domain.Vendor;
import com.tfors.domain.VendorComment;
import com.tfors.repositories.CustomerRepository;
import com.tfors.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();
        loadVendors();

    }

    private void loadCustomers(){
        Customer maciej = new Customer();
        maciej.setFirstName("Maciej");
        maciej.setLastName("Kraskiewicz");

        Customer andrzej = new Customer();
        andrzej.setFirstName("Andrzej");
        andrzej.setLastName("Wrzosek");

        Customer przemek = new Customer();
        przemek.setFirstName("John");
        przemek.setLastName("Snow");

        customerRepository.save(maciej);
        customerRepository.save(andrzej);
        customerRepository.save(przemek);

        log.info("Customer Data loaded = " + customerRepository.count());
    }

    private void loadVendors(){

        String desc = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip " +
                "ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate " +
                "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat " +
                "cupidatat non proident, " +
                "sunt in culpa qui officia deserunt mollit anim id est laborum.";
        Vendor wolf = new Vendor();
        wolf.setName("Wolf");
        wolf.setStars(3);
        wolf.setDescription(desc);
        wolf.setLocalization("52.50931,13.42936");
        wolf.addComment(new VendorComment("Awful"));
        wolf.addComment(new VendorComment("Terrible advisor"));

        Vendor fruits = new Vendor();
        fruits.setName("Fruits Company");

        Vendor sebastian = new Vendor();
        sebastian.setName("Sebastian");
        sebastian.setStars(8);
        sebastian.setDescription(desc);
        sebastian.setLocalization("59.50274,13.49872");
        sebastian.addComment(new VendorComment("Great guy, I tottaly recommend him!"));
        sebastian.addComment(new VendorComment("Best advisor ever!"));
        sebastian.addComment(new VendorComment("10/10 without a doubt!"));

        Vendor mysterious_man = new Vendor();
        mysterious_man.setName("###333333");
        mysterious_man.setStars(0);
        mysterious_man.setDescription(desc);
        mysterious_man.setLocalization("60.90274,15.59872");
        mysterious_man.addComment(new VendorComment("Who is he?!"));


        vendorRepository.save(wolf);
        vendorRepository.save(sebastian);
        vendorRepository.save(mysterious_man);

        log.info("Vendors Data loaded = " + vendorRepository.count());
    }
}
