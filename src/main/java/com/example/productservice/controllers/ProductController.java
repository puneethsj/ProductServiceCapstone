package com.example.productservice.controllers;

import com.example.productservice.dtos.CreateFakeStoreProductDto;
import com.example.productservice.dtos.ErrorDto;
import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        ProductResponseDto productResponseDto = ProductResponseDto.from(product);
        ResponseEntity<ProductResponseDto> responseEntity = new ResponseEntity<>(productResponseDto, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products) {
            ProductResponseDto productResponseDto = ProductResponseDto.from(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody CreateFakeStoreProductDto createFakeStoreProductDto) {
        Product product = productService.createProduct(
                createFakeStoreProductDto.getName(),
                createFakeStoreProductDto.getDesc(),
                createFakeStoreProductDto.getPrice(),
                createFakeStoreProductDto.getImageUrl(),
                createFakeStoreProductDto.getCategoty());
        ProductResponseDto productResponseDto = ProductResponseDto.from(product);
        return productResponseDto;
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ErrorDto handleNullPointerExceptions() {
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setStatus("Failure");
//        errorDto.setMessage("Null pointer exception occurred");
//        return  errorDto;
//    }
}
