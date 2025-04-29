package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductRequestDto {
    String title;
    double price;
    String description;
    String imageUrl;
    String category;
}
