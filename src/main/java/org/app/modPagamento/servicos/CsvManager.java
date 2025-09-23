package org.app.modPagamento.servicos;


import org.app.modPagamento.models.Model;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CsvManager {
    // Métodos do Gerenciador ==============================================================
    public static <T> boolean createFileIfNotExists(Class<T> classe, String path) throws IOException {
        // Gera o caminho do arquivo ---------------------------
        Path filePath = Paths.get(path);

        // Verifica se o arquivo existe ------------------------
        if(Files.notExists(filePath)) {
            // Se o arquivo não existe, cria ele ---------------
            Files.createFile(filePath);
            // Cria os cabeçalhos do arquivo
            CsvManager.createHeaders(classe, path);
            // Retorna true se o arquivo foi criado
            return true;
        }

        // Retorna false se o arquivo já existe
        return false;
    }

    public static <T> void createHeaders(Class<T> classe, String path) throws IOException {
        // Prepara o arrayList para guardar todos os campos da classe e superclasses
        List<Field> allFields = new ArrayList<>();

        // Instancia a classe para percorrer a árvore de herança dela
        Class<?> current = classe;

        // Percorre a árvore de herança até que chegue na raiz
        while (current != null && current != Object.class) {
            // Pega todos os campos declarados da classe
            Field[] declared = current.getDeclaredFields();
            // Seta todos os campos como acessíveis, para leitura
            Arrays.stream(declared).forEach(f -> f.setAccessible(true));
            // Adiciona todos os campos da classe da iteração no array
            // Reversed é para organização na ordem final que os campos irão ficar*
            allFields.addAll(Arrays.asList(declared).reversed());

            // Passa para a classe pai
            current = current.getSuperclass();
        }

        // Pega o nome de todos os campos da classe e monta o cabeçalho
        // Reversed é para organização na ordem final que os campos irão ficar*
        ArrayList<String[]> headers = new ArrayList<>();
        headers.add(allFields.reversed().stream().map(Field::getName).toArray(String[]::new));
        CsvManager.writeFile(headers, path);
    }

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

    public static <T> void insertObjectAsLine(Model object, String path) throws IOException {
        //Instancia um novo writer ---------------------------
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));

        // Configura o ID do produto adicionado e faz a escrita no arquivo --------------------------
        object.setId(listContent(path).size());
        bw.write(object.toCsv());
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
