package sk.stuba.fei.uim.oop.assignment3.product.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductAmount;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductEditRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>(productRepository.findAll());
        return products;
    }
    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product newProduct = new Product(
            productRequest.getName(),
            productRequest.getDescription(),
            productRequest.getAmount(),
            productRequest.getUnit(),
            productRequest.getPrice()
        );
        productRepository.save(newProduct);
        return newProduct;
    }
    @Override
    public Product getProduct(Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())
            return product.get();
        else
            throw new NotFoundException();
    }
    @Override
    public Product updateProduct(Long id, ProductEditRequest productEditRequest) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            if(productEditRequest.getName() != null)
                updatedProduct.setName(productEditRequest.getName());
            if(productEditRequest.getDescription() != null)
                updatedProduct.setDescription(productEditRequest.getDescription());
            productRepository.save(updatedProduct);
            return updatedProduct;
        }
        else
            throw new NotFoundException();
    }
    @Override
    public void deleteProduct(Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())
            productRepository.deleteById(id);
        else
            throw new NotFoundException();
    }
    @Override
    public Long getProductAmount(Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())
            return product.get().getAmount();
        else
            throw new NotFoundException();
    }
    @Override
    public Long addProductAmount(Long id, ProductAmount productAmount) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            Product updatedProduct = product.get();
            updatedProduct.addAmount(productAmount.getAmount());
            productRepository.save(updatedProduct);
            return updatedProduct.getAmount();
        }
        else
            throw new NotFoundException();
    }
}
