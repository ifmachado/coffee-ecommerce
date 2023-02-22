package com.version1.ecommerce.service;

import com.version1.ecommerce.dto.ProductDto;
import com.version1.ecommerce.model.Category;
import com.version1.ecommerce.model.Product;
import com.version1.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category){
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        productRepository.save(product);

    }

    public ProductDto getProductFromDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setPrice(product.getPrice());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setId(product.getId());
        return productDto;
    }

    public List<ProductDto> listProducts() {
        // first fetch all the products
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : products) {
            // for each product change it to DTO
            productDtos.add(getProductFromDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception{
        Optional<Product> optionalProduct = productRepository.findById(productId);
        //exception if prod doesn't exist
        if (!optionalProduct.isPresent()){
            throw new Exception("product doesn't exist");
        }
        else {
            Product product = optionalProduct.get();
            product.setDescription(productDto.getDescription());
            product.setImageURL(productDto.getImageURL());
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            productRepository.save(product);
        }
    }
}
