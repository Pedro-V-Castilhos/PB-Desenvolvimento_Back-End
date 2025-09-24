package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

@Setter
@Getter
public class Client extends User {
    // Campos do Cliente ============================================================
    public static final String csvFileName = "Clients.csv";
    private String address;

    // Construtores =================================================================
    // Construtor com dados explícitos
    public Client(String name, String cpf, String phone, String email, String address) throws IOException {
        super(name, cpf, phone, email);
        this.address = address;
    }

    // Construtor com ArrayList, para instanciar com base na linha do CSV
    public Client(String[] csvData) throws IOException {
        super(Integer.parseInt(csvData[0]), csvData[1], csvData[2], csvData[3], csvData[4], csvData[5], csvData[6]);
        this.address = csvData[7];
    }

    // Métodos =======================================================================
    public void loadCreditCards() throws IOException {}
    public void loadPurchases() throws IOException {}
}
