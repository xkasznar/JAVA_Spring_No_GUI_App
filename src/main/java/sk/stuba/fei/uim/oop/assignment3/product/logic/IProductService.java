package sk.stuba.fei.uim.oop.assignment3.product.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductAmount;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductEditRequest;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product createProduct(ProductRequest productRequest);
    Product getProduct(Long id) throws NotFoundException;
    Product updateProduct(Long id, ProductEditRequest productEditRequest) throws NotFoundException;
    void deleteProduct(Long id) throws NotFoundException;
    Long getProductAmount(Long id) throws NotFoundException;
    Long addProductAmount(Long id, ProductAmount productAmount) throws NotFoundException;
}
