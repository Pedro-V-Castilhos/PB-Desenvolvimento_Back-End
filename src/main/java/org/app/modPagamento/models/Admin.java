package org.app.modPagamento.models;

import java.io.IOException;

public class Admin extends User {
    public static final String csvFileName = "Admins.csv";
    // Construtor com os dados da Super Classe ===================================================
    public Admin(String name, String cpf, String phone, String email) throws IOException {
        super(name, cpf, phone, email);
    }

    public Admin(String[] csvData) throws IOException {
        super(Integer.parseInt(csvData[0]), csvData[1], csvData[2], csvData[3], csvData[4], csvData[5], csvData[6]);
    }
}
