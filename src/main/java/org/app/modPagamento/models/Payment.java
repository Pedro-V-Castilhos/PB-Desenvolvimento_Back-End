package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Payment extends Model {
    public static final String csvFileName = "payment.csv";
    public String status;
    private String method;
    private int idApi;

    public Payment(String method) throws IOException {
        this.method = method;
    }
}
