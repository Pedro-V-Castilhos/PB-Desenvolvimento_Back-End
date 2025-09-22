package org.app.modPagamento.models;

import org.app.modPagamento.Interfaces.FileReference;

import java.io.IOException;

public class Admin extends User {
    // Construtor com os dados da Super Classe ===================================================
    public Admin(String name, String cpf, String phone, String email) throws IOException {
        super(name, cpf, phone, email);
    }
}
