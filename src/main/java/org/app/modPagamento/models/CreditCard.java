package org.app.modPagamento.models;

import java.io.IOException;

public class CreditCard extends Model {
    // Campos do Cartão de crédito ==============================================
    public static final String csvFileName = "CreditCards.csv";
    private String propName;
    private String propCpf;
    private String number;
    private byte[] cvv;
    private byte[] expiryDate;
    private int idUser;

    // Construtores ==============================================================
    public CreditCard(String propName, String propCpf, String number) throws IOException {
        this.propName = propName;
        this.propCpf = propCpf;
        this.number = number;
    }

    public CreditCard(String[] csvData) throws IOException {
        super(Integer.parseInt(csvData[0]), csvData[1], csvData[2]);
        this.propName = csvData[3];
        this.propCpf = csvData[4];
        this.number = csvData[5];
        this.cvv = csvData[6].getBytes();
        this.expiryDate = csvData[7].getBytes();
        this.idUser = Integer.parseInt(csvData[8]);
    }
}
