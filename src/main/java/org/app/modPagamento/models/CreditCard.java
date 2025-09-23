package org.app.modPagamento.models;

import java.io.IOException;

public class CreditCard extends Model {
    public static final String csvFileName = "CreditCards.csv";
    private String propName;
    private String propCpf;
    private String number;
    private Byte[] cvv;
    private Byte[] expiryDate;
    private int idUser;
    private User user;

    public CreditCard(String propName, String propCpf, String number) throws IOException {
        this.propName = propName;
        this.propCpf = propCpf;
        this.number = number;
    }
}
