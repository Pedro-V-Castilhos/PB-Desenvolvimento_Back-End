package org.app.modPagamento.models;

import java.io.IOException;

public class Admin extends User {
    public Admin(String name, String cpf, String phone, String email) throws IOException {
        super("Admin.csv", name, cpf, phone, email);
    }
}
