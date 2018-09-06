package com.tfors.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String description;
    private String localization;
    private Integer stars;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor")
    private Set<VendorComment> comments = new HashSet<>();
    @Lob
    private Byte[] image;

    public Vendor addComment(VendorComment comment){

        comment.setVendor(this);
        this.comments.add(comment);

        return this;
    }
    @JsonIgnore
    public Byte[] getImage() {

        return image;
    }
}
