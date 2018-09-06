package com.tfors.repositories;


import com.tfors.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
