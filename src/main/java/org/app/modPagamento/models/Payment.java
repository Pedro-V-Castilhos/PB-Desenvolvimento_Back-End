package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Payment extends Model {
    // Campos =======================================================================
    public static final String csvFileName = "payment.csv";
    public String status;
    private String method;
    private int idApi;

    // Construtores =================================================================
    public Payment(String method) throws IOException {
        this.method = method;
        this.status = "PENDENTE";
    }

    public Payment(String[] csvData){
        super(Integer.parseInt(csvData[0]), csvData[1], csvData[2]);
        this.status = csvData[3];
        this.method = csvData[4];
        this.idApi = Integer.parseInt(csvData[5]);
    }
}
