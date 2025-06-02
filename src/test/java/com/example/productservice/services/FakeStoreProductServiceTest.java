package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.FakeStoreProductRequestDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FakeStoreProductServiceTest {

    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    RedisTemplate redisTemplate = Mockito.mock(RedisTemplate.class);

    FakeStoreProductService fakeStoreProductService =
            new FakeStoreProductService(restTemplate, redisTemplate);

    @Test
    public void testGetProductByIdReturnsProduct() throws ProductNotFoundException {
        FakeStoreProductDto dummyResponse = new FakeStoreProductDto();
        dummyResponse.setId(1L);
        dummyResponse.setTitle("title");
        dummyResponse.setDescription("description");
        dummyResponse.setPrice(2.45);
        dummyResponse.setImage("img.url");
        dummyResponse.setCategory("category");

        when(restTemplate.getForObject(
                "https://fakestoreapi.com//products/1",
                FakeStoreProductDto.class)).thenReturn(dummyResponse);

        Product product = fakeStoreProductService.getProductById(1L);

        assertEquals(1L, product.getId());
        assertEquals("title", product.getName());
        assertEquals("description", product.getDescription());
        assertEquals(2.45, product.getPrice());
        assertEquals("img.url", product.getImageUrl());
    }

    @Test
    public void testGetProductByIdWithNullProductThrowsException() throws ProductNotFoundException {
        when(restTemplate.getForObject(
                "https://fakestoreapi.com//products/1",
                FakeStoreProductDto.class)).thenReturn(null);

        assertThrows(ProductNotFoundException.class,
                () -> fakeStoreProductService.getProductById(1L));
    }

    @Test
    public void testCreateProductReturnsProductWithId() {
        FakeStoreProductDto dummyResponse = new FakeStoreProductDto();
        dummyResponse.setId(1L);
        dummyResponse.setTitle("title");
        dummyResponse.setDescription("description");
        dummyResponse.setPrice(2.45);
        dummyResponse.setImage("img.url");
        dummyResponse.setCategory("category");

        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com//products"),
                any(),
                eq(FakeStoreProductDto.class))).thenReturn(dummyResponse);

        Product product = fakeStoreProductService.createProduct(
                "title", "description", 576.45, "img.url", "category");

        assertEquals(1L, product.getId());
        assertEquals("title", product.getName());
        assertEquals("description", product.getDescription());
        assertEquals(2.45, product.getPrice());
        assertEquals("img.url", product.getImageUrl());
        assertEquals("category", product.getCategory().getName());
    }

    @Test
    public void testCreateProductVerifyAPICalls() {
        FakeStoreProductDto dummyResponse = new FakeStoreProductDto();
        dummyResponse.setId(1L);
        dummyResponse.setTitle("title");
        dummyResponse.setDescription("description");
        dummyResponse.setPrice(2.45);
        dummyResponse.setImage("img.url");
        dummyResponse.setCategory("category");

        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com//products"),
                any(),
                eq(FakeStoreProductDto.class))).thenReturn(dummyResponse);

        Product product = fakeStoreProductService.createProduct(
                "title", "description", 576.45, "img.url", "category");

        verify(restTemplate,times(1)).postForObject(
                eq("https://fakestoreapi.com//products"),
                any(),
                eq(FakeStoreProductDto.class));
    }
}