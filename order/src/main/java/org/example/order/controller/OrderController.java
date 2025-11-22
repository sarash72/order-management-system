package org.example.order.controller;
import org.example.order.entity.Order;
import org.example.order.service.OrderService;
import org.example.product.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = orderService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products/webClient")
    public ResponseEntity<List<Product>> getProductsWebClient() {
        List<Product> products = orderService.getAllProductsWebClient();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(Long id) {
        return orderService.getProductById(id);
    }
        //
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
//        orderService.deleteOrder(id);
//        return ResponseEntity.noContent().build();
//    }
}
