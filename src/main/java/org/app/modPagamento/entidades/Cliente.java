package org.app.modPagamento.entidades;

public class Cliente extends Usuario {
    private String endereco;

    public Cliente(int idUsuario, String nome, String cpf, String telefone, String email, String endereco) {
        super(idUsuario, nome, cpf, telefone, email);
        this.endereco = endereco;
    }
}
