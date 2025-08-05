package org.app.modPagamento.servicos;

import java.io.*;
import java.util.ArrayList;

public class ManagerCSV {
    // Caminho do arquivo CSV
    private String path;

    // Getters e Setters
    public String getPath() {
        return path;
    }

    // Construtor =========================================================================
    public ManagerCSV(String path) {
        this.path = path;
    }

    // Métodos do Gerenciador ==============================================================

    // Reader
    public ArrayList<String[]> read() throws Exception {
        // Prepara para retorno
        ArrayList<String[]> retorno = new ArrayList<>();

        // Tenta fazer a leitura do arquivo
        try {
            BufferedReader br = new BufferedReader(new FileReader(getPath()));

            // Guarda todos os dados na matriz
            String linha = "";
            do{
                linha = br.readLine();
                if(linha != null) {
                    retorno.add(linha.split(","));
                }
            }while(linha != null);
            br.close();
        // Exceção pra caso não consiga achar o arquivo
        }catch (FileNotFoundException e){
            throw new Exception("Erro: Arquivo não encontrado!");
        }

        // Retorno
        return retorno;
    }

    public void write(ArrayList<String[]> data) throws Exception {

        //Instancia um novo writer
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getPath(), true));

            // Para cada linha que tiver no array
            for (String[] linha : data) {
                for (int i = 0; i < linha.length - 1; i++) {
                    bw.write(linha[i] + ",");
                }
                bw.write(linha[linha.length - 1]);
                bw.newLine();
            }

            // Fecha o arquivo
            bw.close();
        }catch(Exception e){
            throw new Exception("Erro: Arquivo não encontrado!");
        }
    }
}
