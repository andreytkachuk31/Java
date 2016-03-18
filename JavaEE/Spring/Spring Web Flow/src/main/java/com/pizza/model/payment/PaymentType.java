package com.pizza.model.payment;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang.WordUtils.capitalizeFully;

/**
 * @author Andrii_Tkachuk
 * @since 6/2/2015
 */
public enum PaymentType implements Serializable {
    CASH, CHECK, CREDIT_CARD;

    public static List<PaymentType> asList() {
        PaymentType[] all = PaymentType.values();
        return Arrays.asList(all);
    }

    @Override
    public String toString() {
        return capitalizeFully(name().replace('_', ' '));
    }
}
