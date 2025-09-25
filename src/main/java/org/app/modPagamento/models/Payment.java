package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Payment{
    // Campos =======================================================================
    public String status;
    private String method;
    private int idApi;

    // Construtores =================================================================
    public Payment(String method) throws IOException {
        this.method = method;
        this.status = "PENDENTE";
    }

    public Payment(String[] csvData){
        this.status = csvData[0];
        this.method = csvData[1];
        this.idApi = Integer.parseInt(csvData[2]);
    }
}
