package org.example.product.service;

import lombok.RequiredArgsConstructor;
import org.example.product.entity.Product;
import org.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductService {

    @Autowired
    private final ProductRepository repo;

    public Product save(Product p) { return repo.save(p); }

    public List<Product> findAll() { return repo.findAll(); }

    public Product findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
