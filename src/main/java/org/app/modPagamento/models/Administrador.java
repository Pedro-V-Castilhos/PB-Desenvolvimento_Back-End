package org.app.modPagamento.models;

import java.io.IOException;

public class Administrador extends User {
    public Administrador(String name, String cpf, String phone, String email) throws IOException {
        super("Admin.csv", name, cpf, phone, email);
    }
}
