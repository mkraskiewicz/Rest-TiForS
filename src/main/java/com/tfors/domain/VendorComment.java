package com.tfors.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = "vendor")
@Entity
public class VendorComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    private Vendor vendor;

    public VendorComment(){}
    public VendorComment(String description){
        this.description = description;
    }
    public VendorComment(String description, Vendor vendor){
        this.description = description;
        this.vendor = vendor;
    }

    @JsonIgnore
    public Vendor getVendor() {
        return vendor;
    }
}
