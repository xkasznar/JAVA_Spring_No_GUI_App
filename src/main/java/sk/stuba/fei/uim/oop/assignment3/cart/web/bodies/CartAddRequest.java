package sk.stuba.fei.uim.oop.assignment3.cart.web.bodies;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartItem;

@Getter
@NoArgsConstructor
public class CartAddRequest {
    private Long productId;
    private Long amount;

    public CartAddRequest(CartItem ci){
        this.productId = ci.getProductId();
        this.amount = ci.getAmount();
    }
}
