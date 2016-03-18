package com.pizza.model.order;

import static org.apache.commons.lang.WordUtils.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @since: 01.06.15
 * @author: Андрей
 */
public enum Topping implements Serializable {

    PEPPERONI,
    SAUSAGE,
    HAMBURGER,
    MUSHROOM,
    CANADIAN_BACON,
    PINEAPPLE,
    GREEN_PEPPER,
    JALAPENO,
    TOMATO,
    ONION,
    EXTRA_CHEESE;

    public static List<Topping> asList() {
        return Arrays.asList(Topping.values());
    }

    @Override
    public String toString() {
        return capitalizeFully(name().replace('_', ' '));
    }
}
