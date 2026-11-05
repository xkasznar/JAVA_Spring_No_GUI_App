package sk.stuba.fei.uim.oop.assignment3.product.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;
    @Setter
    private String description;
    private Long amount;
    private String unit;
    private Double price;
    
    public Product(){
        this.name = "";
        this.description = "";
        this.amount = 0L;
        this.unit = "";
        this.price = 0.0;
    }

    public Product(String name, String description, Long amount, String unit, Double price){
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.unit = unit;
        this.price = price;
    }

    public void addAmount(Long amount){
        this.amount += amount;
    }
    public void subtractAmount(Long amount){
        this.amount -= amount;
    }
}