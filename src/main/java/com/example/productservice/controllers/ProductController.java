package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.FakeStoreProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    FakeStoreProductService fakeStoreProductService;

    public ProductController(FakeStoreProductService fakeStoreProductService) {
        this.fakeStoreProductService = fakeStoreProductService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id){
        /*ProductResponseDto dummyProductResponseDto = new ProductResponseDto();
        dummyProductResponseDto.setId(1);
        dummyProductResponseDto.setName("Product " + id);
        dummyProductResponseDto.setDescription("Product Description");
        dummyProductResponseDto.setPrice(1123.4);
        dummyProductResponseDto.setImageUrl("https://dummy.image");
        return dummyProductResponseDto;*/
        Product product = fakeStoreProductService.getProductById(id);
        ProductResponseDto productResponseDto = ProductResponseDto.from(product);
        ResponseEntity<ProductResponseDto> responseEntity = new ResponseEntity<>(productResponseDto, HttpStatus.OK);
        return responseEntity;
    }
}
