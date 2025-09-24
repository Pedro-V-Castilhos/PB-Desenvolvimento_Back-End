package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class DiscCoupon extends Model{
    // Campos ============================================================
    public static final String csvFileName = "Coupons.csv";
    private String token;
    private double percDiscount;

    // Construtores ======================================================
    public DiscCoupon(String token, double percDiscount) throws IOException {
        this.token = token;
        this.percDiscount = percDiscount;
    }

    public DiscCoupon(String[] csvData){
        super(Integer.parseInt(csvData[0]), csvData[1], csvData[2]);
        this.token = csvData[3];
        this.percDiscount = Double.parseDouble(csvData[4]);
    }
}
