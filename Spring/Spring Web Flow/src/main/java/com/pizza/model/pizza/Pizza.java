package com.pizza.model.pizza;

import com.pizza.model.order.Topping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @since: 30.05.15
 * @author: Андрей
 */
public class Pizza implements Serializable {

    private PizzaSize size;
    private List<Topping> toppings;

    public Pizza() {
        toppings = new ArrayList<Topping>();
        size = PizzaSize.LARGE;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(String[] toppingStrings) {
        for (int i = 0; i < toppingStrings.length; i++) {
            toppings.add(Topping.valueOf(toppingStrings[i]));
        }
    }
}
