package org.app.modPagamento.models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Compra {
    public Integer idCompra;
    private Cliente clienteCompra;
    private ArrayList<Item> listaItems;
    private CupomDesconto cupomDesconto;
    private ArrayList<Pagamento> pgtoDaCompra;

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
