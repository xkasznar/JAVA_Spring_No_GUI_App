package sk.stuba.fei.uim.oop.assignment3.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.logic.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductAmount;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    @Autowired
    private IProductService service;

    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> getAllProducts() {
        return service.getAllProducts().stream().map(ProductResponse::new).collect(Collectors.toList());
    }
    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(new ProductResponse(service.createProduct(productRequest)), HttpStatus.CREATED);
    }
    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse getProduct(@PathVariable("id") Long id) throws NotFoundException {
        return new ProductResponse(service.getProduct(id));
    }
    @PutMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse updateProduct(@PathVariable("id") Long id, @RequestBody ProductEditRequest productEditRequest) throws NotFoundException {
        return new ProductResponse(service.updateProduct(id, productEditRequest));
    }
    @DeleteMapping(value = "/product/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws NotFoundException {
        service.deleteProduct(id);
    }
    @GetMapping(value = "/product/{id}/amount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductAmount getProductAmount(@PathVariable("id") Long id) throws NotFoundException {
        return new ProductAmount(service.getProductAmount(id));
    }
    @PostMapping(value = "/product/{id}/amount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductAmount addProductAmount(@PathVariable Long id, @RequestBody ProductAmount productAmount) throws NotFoundException {
        return new ProductAmount(service.addProductAmount(id, productAmount));
    }
}
