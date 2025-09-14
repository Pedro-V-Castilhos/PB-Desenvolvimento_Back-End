package org.app.modPagamento.servicos;


import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class CsvManager {
    // Métodos do Gerenciador ==============================================================
    public static <T> boolean createFileIfNotExists(String path) throws IOException {
        // Gera o caminho do arquivo ---------------------------
        Path filePath = Paths.get(path);

        if(Files.notExists(filePath)) {
            Files.createFile(filePath);
            return true;
        }

        return false;
    }

    // Reader
    public static ArrayList<String[]> listContent(String path) throws IOException {
        // Prepara para retorno ------------------------------
        ArrayList<String[]> returnData = new ArrayList<>();

        // Prepara o reader ----------------------------------
        BufferedReader br = new BufferedReader(new FileReader(path));

        // Guarda todos os dados na matriz -------------------
        String line = "";
        do{
            line = br.readLine();
            if(line != null) {
                returnData.add(line.split(","));
            }
        }while(line != null);

        //Fecha o arquivo ------------------------------------
        br.close();

        // Retorno -------------------------------------------
        return returnData;
    }

    public static void addLineInFile(String[] data, String path) throws IOException {

        //Instancia um novo writer ---------------------------
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));

        // Faz a escrita no arquivo --------------------------
        bw.write(String.join(",", data));
        bw.newLine();

        // Fecha o arquivo -----------------------------------
        bw.close();
    }

    public static void writeFile(ArrayList<String[]> data, String path) throws IOException {

        //Instancia um novo writer ---------------------------
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));

        // Para cada linha que tiver no array ----------------
        for (String[] line : data) {
            bw.write(String.join(",", line));
            bw.newLine();
        }

        // Fecha o arquivo -----------------------------------
        bw.close();
    }

    public static Optional<String[]> getLineInFile(String searchData, String path) throws IOException {
        // Pega todos os dados que está no arquivo -----------
        ArrayList<String[]> fileContent = listContent(path);

        // Procura em uma linha o valor especificado ---------
        String[] returnData = null;
        for (String[] line : fileContent) {
            for (String value : line) {
                if(value.equals(searchData)) {
                    returnData = line;
                }
            }
        }

        // Retorna a linha que está o conteúdo ou nulo se não encontrar
        return Optional.ofNullable(returnData);
    }

    public static void removeInFile(String removeValue, String path) throws IOException {
        // Procura a linha que deseja ser removida -----------------------------------
        Optional<String[]> lineForRemoval = getLineInFile(removeValue, path);

        // Carrega o conteúdo do arquivo e remove a linha ----------------------------
        ArrayList<String[]> fileContent = listContent(path);
        int lineIndex = fileContent.indexOf(lineForRemoval.get());
        fileContent.remove(lineIndex);

        // Escreve o conteúdo do arquivo novamente, mas sem a linha removida ---------
        writeFile(fileContent, path);
    }
}
