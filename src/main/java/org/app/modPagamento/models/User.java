package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public abstract class User extends Model {
    private String name;
    private String cpf;
    private String phone;
    private String email;

    // Construtor ==============================================================================
    public User(String name, String cpf, String phone, String email) throws IOException {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
    }

    public User(String csvFilePath) throws IOException {
    }
}
