package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.dtos.SearchRequestDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {
    SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public Page<ProductResponseDto> search(@RequestBody SearchRequestDto searchRequestDto) {
        Page<Product> productPage = searchService.search(searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(),
                searchRequestDto.getPageSize(),
                searchRequestDto.getSortParam());
        return getProductResponseDtoFromProductPage(productPage);
    }

    @GetMapping("/search")
    public Page<ProductResponseDto> search(@RequestParam String query,
                                @RequestParam int pageNumber,
                                @RequestParam int pageSize,
                                @RequestParam String sortParam) {
        Page<Product> productPage = searchService.search(query, pageNumber, pageSize, sortParam);
        return getProductResponseDtoFromProductPage(productPage);
    }

    private Page<ProductResponseDto> getProductResponseDtoFromProductPage(Page<Product> productPage) {
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        List<Product> products = productPage.getContent();

        for(Product product: products) {
            ProductResponseDto productResponseDto = ProductResponseDto.from(product);
            productResponseDtos.add(productResponseDto);
        }

        return new PageImpl<>(productResponseDtos,
                productPage.getPageable(),
                productPage.getTotalElements());
    }
}
