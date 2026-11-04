package sk.stuba.fei.uim.oop.assignment3.cart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.cart.logic.ICartService;
import sk.stuba.fei.uim.oop.assignment3.cart.web.bodies.CartAddRequest;
import sk.stuba.fei.uim.oop.assignment3.cart.web.bodies.CartResponse;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

@RestController
public class CartController {
    @Autowired
    private ICartService service;

    @PostMapping(value = "/cart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResponse> createCart() {
        return new ResponseEntity<>(new CartResponse(service.createCart()), HttpStatus.CREATED);
    }
    @GetMapping(value = "/cart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse getCart(@PathVariable("id") Long id) throws NotFoundException {
        return new CartResponse(service.getCart(id));
    }
    @DeleteMapping(value = "/cart/{id}")
    public void deleteCart(@PathVariable("id") Long id) throws NotFoundException {
        service.deleteCart(id);
    }
    @PostMapping(value = "/cart/{id}/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse addToCart(@PathVariable("id") Long id, @RequestBody CartAddRequest cartAddRequest) throws NotFoundException, IllegalOperationException {
        return new CartResponse(service.addToCart(id, cartAddRequest));
    }
    @GetMapping(value = "/cart/{id}/pay", produces = MediaType.TEXT_PLAIN_VALUE)
    public String payForCart(@PathVariable("id") Long id) throws NotFoundException, IllegalOperationException {
        return service.payForCart(id);
    }
}
