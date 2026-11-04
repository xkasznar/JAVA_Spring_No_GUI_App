package sk.stuba.fei.uim.oop.assignment3.cart.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartItem;
import sk.stuba.fei.uim.oop.assignment3.cart.data.ICartRepository;
import sk.stuba.fei.uim.oop.assignment3.cart.web.bodies.CartAddRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;

import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Cart createCart() {
        Cart newCart = new Cart();
        cartRepository.save(newCart);
        return newCart;
    }
    @Override
    public Cart getCart(Long id) throws NotFoundException {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent())
            return cart.get();
        else
            throw new NotFoundException();
    }
    @Override
    public void deleteCart(Long id) throws NotFoundException {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent())
            cartRepository.deleteById(id);
        else
            throw new NotFoundException();
    }
    @Override
    public Cart addToCart(Long id, CartAddRequest cartAddRequest) throws NotFoundException, IllegalOperationException {
        Optional<Cart> cart = cartRepository.findById(id);
        Long productId = cartAddRequest.getProductId();
        Optional<Product> product = productRepository.findById(productId);

        if(!product.isPresent() || !cart.isPresent())
            throw new NotFoundException();
        Long avail = product.get().getAmount();
        Long asking = cartAddRequest.getAmount();
        if(avail - asking < 0)
            throw new IllegalOperationException();
        if(cart.get().getPayed())
            throw new IllegalOperationException();
        Cart updatedCart = cart.get();
        Product updatedProduct = product.get();

        updatedCart.addCartItem(productId, asking);
        updatedProduct.subtractAmount(asking);
        cartRepository.save(updatedCart);
        productRepository.save(updatedProduct);
        return updatedCart;
    }
    @Override
    public String payForCart(Long id) throws NotFoundException, IllegalOperationException {
        Optional<Cart> cart = cartRepository.findById(id);

        if(!cart.isPresent())
            throw new NotFoundException();
        if(cart.get().getPayed())
            throw new IllegalOperationException();
        Cart updatedCart = cart.get();

        updatedCart.setPayed(true);
        cartRepository.save(updatedCart);
        Double total = 0.0;
        for(CartItem ci : updatedCart.getShoppingList()){
            Optional<Product> product = productRepository.findById(ci.getProductId());
            if(!product.isPresent())
                continue;
            Double sub = product.get().getPrice();
            total += sub * ci.getAmount();
        }
        return total.toString();
    }
}
