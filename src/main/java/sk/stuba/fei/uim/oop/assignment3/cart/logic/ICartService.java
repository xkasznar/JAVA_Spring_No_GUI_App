package sk.stuba.fei.uim.oop.assignment3.cart.logic;

import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.web.bodies.CartAddRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

public interface ICartService {
    Cart createCart();
    Cart getCart(Long id) throws NotFoundException;
    void deleteCart(Long id) throws NotFoundException;
    Cart addToCart(Long id, CartAddRequest cartAddRequest) throws NotFoundException, IllegalOperationException;
    String payForCart(Long id) throws NotFoundException, IllegalOperationException;
}
