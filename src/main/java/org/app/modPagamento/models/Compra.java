package org.app.modPagamento.models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Compra {
    public Integer idCompra;
    private Client clienteCompra;
    private ArrayList<Item> listaItems;
    private DiscCoupon cupomDesconto;
    private ArrayList<Payment> pgtoDaCompra;

    public Double calcPrecoTotal(){
        return null;
    }

    public Double aplicarDesconto(){
        return null;
    }

    public String verificarPagamento(){
        return null;
    }

    public JSONObject jsonfy(){
        return null;
    }

    public String autualizarPagamentoStatus(JSONObject json){
        return null;
    }
}
