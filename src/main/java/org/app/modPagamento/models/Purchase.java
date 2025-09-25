package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

@Setter
@Getter
public class Purchase extends Model {
    public static final String csvFileName = "Purchases.csv";
    private ArrayList<Item> items;
    private int idClient;
    private String couponToken;
    private ArrayList<Payment> pagamentos;

    public Purchase(){
    }
}
