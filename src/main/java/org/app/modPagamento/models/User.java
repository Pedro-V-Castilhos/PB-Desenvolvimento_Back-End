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

    // Caminho pro arquivo CSV
    private static final String savePath = "C:\\Users\\Pedro Viana\\IdeaProjects\\TP3 - Projeto de Bloco\\src\\main\\java\\org\\app\\modPagamento\\usuarios.csv";

    // Construtor ==============================================================================
    public User(String csvFilePath, String name, String cpf, String phone, String email) throws IOException {
        super(csvFilePath);
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
    }
}
