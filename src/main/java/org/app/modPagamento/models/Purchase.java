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
    public Integer idCompra;
    private Client clienteCompra;
    private ArrayList<Item> listaItems;
    private DiscCoupon cupomDesconto;
    private ArrayList<Payment> pgtoDaCompra;

    public Purchase() throws IOException {
    }
}
