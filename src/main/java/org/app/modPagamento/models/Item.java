package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Item{
    // Campos ================================================================
    private String descricao;
    private Integer quantidade;
    private Double valorUnitario;

    // Construtores ==========================================================
    public Item(String descricao, Integer qtde, Double price) throws IOException {
        this.descricao = descricao;
        this.quantidade = qtde;
        this.valorUnitario = price;
    }

    public Item(String[] csvData){
        this.descricao = csvData[0];
        this.quantidade = Integer.parseInt(csvData[1]);
        this.valorUnitario = Double.parseDouble(csvData[2]);
    }
}
