package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateFakeStoreProductDto {
    private String name;
    private String desc;
    private String category;
    private double price;
    private String imageUrl;
}
