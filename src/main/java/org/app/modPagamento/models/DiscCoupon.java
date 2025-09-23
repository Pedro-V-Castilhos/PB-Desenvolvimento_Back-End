package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class DiscCoupon extends Model{
    public static final String csvFileName = "Coupons.csv";
    private String token;
    private double percDiscount;

    public DiscCoupon(String token, double percDiscount) throws IOException {
        this.token = token;
        this.percDiscount = percDiscount;
    }
}
