package com.example.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter

@Entity
public class Category extends BaseModel implements Serializable {
    private String description;
    @OneToMany(mappedBy = "category")

    @JsonIgnore
    private List<Product> products;
}
