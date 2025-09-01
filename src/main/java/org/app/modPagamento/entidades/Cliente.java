package org.app.modPagamento.entidades;

import java.util.ArrayList;

public class Cliente extends Usuario {
    private String endereco;

    public Cliente(int idUsuario, String nome, String cpf, String telefone, String email, String endereco) {
        super(idUsuario, nome, cpf, telefone, email);
        this.endereco = endereco;
    }

    public Cartao registrarCartao() {
        return null;
    }

    public ArrayList<Cartao> listarCartoes() {
        return null;
    }

    public Compra novaCompra(){
        return null;
    }

    public ArrayList<Compra> listarCompras() {
        return null;
    }
}
