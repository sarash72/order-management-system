package org.example.order.service;

import lombok.RequiredArgsConstructor;
import org.example.order.entity.Order;
import org.example.order.repository.OrderRepository;
import org.example.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final RestClient restClient;
    private final WebClient webClient;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        String url = "http://localhost:8082/api/products";

        ResponseEntity<Product[]> response =
                restTemplate.getForEntity(url, Product[].class);

        Product[] products = response.getBody();

        return Arrays.asList(products);
    }

    public List<Product> getAllProductsWebClient() {
        String url = "http://localhost:8082/api/products";
        return webClient.get().uri(url).retrieve().bodyToFlux(Product.class).collectList().block();
    }

    public Product getProductById(Long id) {
        return restClient.get().uri("/{id}", 1)
                .retrieve().body(Product.class);
    }
}
