package sk.stuba.fei.uim.oop.assignment3.cart.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ElementCollection;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<CartItem> shoppingList;
    @Setter
    private Boolean payed;

    public Cart(){
        shoppingList = new ArrayList<>();
        payed = false;
    }

    public void addCartItem(Long productId, Long amount){
        for(CartItem ci : shoppingList){
            if(ci.getProductId().equals(productId)){
                ci.addAmount(amount);
                return;
            }
        }
        shoppingList.add(new CartItem(productId, amount));
    }
}