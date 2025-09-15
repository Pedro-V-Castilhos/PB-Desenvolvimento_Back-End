package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Payment extends Model {
    public String status;
    private String method;
    private int idApi;

    public Payment(String method) throws IOException {
        super("Payments.csv");
        this.method = method;
    }
}
