package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productDBService")
public class ProductDBService implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public ProductDBService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id:" + id + " not found");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String name, String description, double price, String imageUrl, String category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        Category category1 = getCategoryFromDB(category);

        product.setCategory(category1);
        return productRepository.save(product);
    }

    private Category getCategoryFromDB(String name) {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        if(optionalCategory.isPresent()) {
            return optionalCategory.get();
        }

        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }
}
