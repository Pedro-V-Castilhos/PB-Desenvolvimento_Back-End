package org.app.modPagamento.models;

import org.app.modPagamento.servicos.CsvManager;

import java.util.ArrayList;

public class Usuario {
    // Campos
    public Integer idUsuario;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    // Caminho pro arquivo CSV
    private static final String savePath = "C:\\Users\\Pedro Viana\\IdeaProjects\\TP3 - Projeto de Bloco\\src\\main\\java\\org\\app\\modPagamento\\usuarios.csv";

    // Construtor ==============================================================================
    public Usuario(int idUsuario, String nome, String cpf, String telefone, String email) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    // Getter  e setters ====================================================================================
    public String getSavePath() {
        return savePath;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Métodos de leitura e escrita de CSV =======================================================
    public void registrarUsuario(){
        ArrayList<String[]> arraySalvar = new ArrayList<>();
        String[] dadosUsuario = {idUsuario.toString(), getNome(), getCpf(), getTelefone(), getEmail()};
        arraySalvar.add(dadosUsuario);

        CsvManager csv = new CsvManager();
        try {
            csv.addInFile(arraySalvar, savePath);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void listarUsuarios(){
        try {
            CsvManager csv = new CsvManager();
            ArrayList<String[]> listaUsuarios = csv.listContent(savePath);

            for(String[] usuario : listaUsuarios){
                String output = String.format("===============================\n" +
                        "Id: %s\n" +
                        "Nome: %s\n" +
                        "CPF: %s\n" +
                        "Telefone: %s\n" +
                        "Email: %s\n" +
                        "===============================", usuario[0], usuario[1], usuario[2], usuario[3], usuario[4]);
                System.out.println(output);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
