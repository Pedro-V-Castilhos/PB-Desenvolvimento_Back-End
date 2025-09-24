package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Item extends Model{
    // Campos ================================================================
    public static final String csvFileName = "Items.csv";
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
        super(Integer.parseInt(csvData[0]), csvData[1], csvData[2]);
        this.descricao = csvData[3];
        this.quantidade = Integer.parseInt(csvData[4]);
        this.valorUnitario = Double.parseDouble(csvData[5]);
    }
}
