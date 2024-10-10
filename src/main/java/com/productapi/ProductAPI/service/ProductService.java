package com.productapi.ProductAPI.service;

import com.productapi.ProductAPI.entity.Product;
import com.productapi.ProductAPI.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // saving a product in the database
    public Product saveProduct(Product product){
        try{
            return productRepository.save(product);
        } catch(Exception e){
            throw new RuntimeException("Failed to save product : " + e.getMessage());
        }
    }

    // Get all the products from the database
    public List<Product> fetchAllProducts(){
        try{
            return productRepository.findAll();
        } catch(Exception e){
            throw new RuntimeException("Failed to fetch all products : " + e.getMessage());
        }
    }

    // Get a product by ID
    public Optional<Product> fetchProductById(Long id){
        try{
            return productRepository.findById(id);
        } catch(Exception e){
            throw new RuntimeException("Failed to fetch product by ID : " + e.getMessage());
        }
    }

    // Update a single product by ID
    public Optional<Product> updateProduct(Long id, Product updatedProduct){
        try{
            Optional<Product> existingProductOptional = productRepository.findById(id);
            if(existingProductOptional.isPresent()){
                Product existingProduct = existingProductOptional.get();
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setQuantity(updatedProduct.getQuantity());
                Product savedEntity = productRepository.save(existingProduct);
                return Optional.of(savedEntity);
            } else{
                return Optional.empty();
            }
        } catch(Exception e){
            throw new RuntimeException("Failed to update product : " + e.getMessage());
        }
    }


    // Delete a single product by ID
    public boolean deleteProduct(Long id){
        try{
            productRepository.deleteById(id);
            return true;  // deletion successful
        } catch(Exception e){
            throw new RuntimeException("Failed to delete product : " + e.getMessage());
        }
    }


}

