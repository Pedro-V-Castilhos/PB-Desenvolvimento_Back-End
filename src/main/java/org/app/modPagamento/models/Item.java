package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Item extends Model{
    public static final String csvFileName = "Items.csv";
    private String descricao;
    private Integer quantidade;
    private Double valorUnitario;

    public Item(String descricao, Integer qtde, Double price) throws IOException {
        this.descricao = descricao;
        this.quantidade = qtde;
        this.valorUnitario = price;
    }
}
