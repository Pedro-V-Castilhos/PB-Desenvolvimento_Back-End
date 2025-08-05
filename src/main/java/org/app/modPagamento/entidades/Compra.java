package org.app.modPagamento.entidades;

import java.util.ArrayList;

public class Compra {
    public Integer idCompra;
    private Cliente clienteCompra;
    private ArrayList<Item> listaItems;
    private CupomDesconto cupomDesconto;
    private ArrayList<Pagamento> pgtoDaCompra;
}
