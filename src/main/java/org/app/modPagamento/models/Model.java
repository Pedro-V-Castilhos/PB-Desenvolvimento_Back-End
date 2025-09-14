package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;
import org.app.modPagamento.servicos.CsvManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
@Setter
public abstract class Model<T extends Model<T>> {
    // Campos das classes modelo -----------------------
    public int id;
    private String csvFilePath;
    private String encripKeyPath;
    private String decripKeyPath;

    public Model(String csvFilePath) throws IOException {

        if(Files.exists(Paths.get(csvFilePath))){
            id = CsvManager.listContent(csvFilePath).size();
        }else{
            id = 1;
        }

        this.csvFilePath = csvFilePath;
    }

    // Métodos das classes modelos ---------------------
    public void insert(T obj) throws IOException {
        if(CsvManager.createFileIfNotExists(csvFilePath)){
            // Pega todos os campos da classe e torna eles acessíveis------------
            Field[] superFields = obj.getClass().getSuperclass().getFields();
            Field[] fields = obj.getClass().getDeclaredFields();

            Stream.of(superFields, fields)
                    .flatMap(Arrays::stream)
                    .forEach(field -> field.setAccessible(true));

            // Pega o nome de todos os campos da classe e monta o cabeçalho
            CsvManager.addLineInFile(Stream.of(superFields,fields).flatMap(Arrays::stream).map(Field::getName).toArray(String[]::new), csvFilePath);
        }

        CsvManager.addLineInFile(toCsv(obj), csvFilePath);
    }

    public void delete(T obj) throws IOException {
        CsvManager.removeInFile(String.format("%d",obj.getId()), csvFilePath);
    }

    public Optional<String[]> find(T obj) throws IOException {
        return CsvManager.getLineInFile(String.format("%d",obj.getId()), csvFilePath);
    }

    public ArrayList<T> listAll() throws IOException {
        ArrayList<String[]> fileContent = CsvManager.listContent(csvFilePath);
        return null;
    }

    public String[] toCsv(T obj) throws IllegalArgumentException{
        // Pega todos os campos da classe e torna eles acessíveis------------
        Field[] superFields = obj.getClass().getSuperclass().getFields();
        Field[] fields = obj.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {field.setAccessible(true);});

        // Faz o retorno da leitura de todos os campos da classe ------------
        return Stream.of(superFields, fields)
                .flatMap(Arrays::stream)
                .map(field ->
                {
                    try {
                        return field.get(obj).toString();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray(String[]::new);
    }

    public JSONObject toJson(){
        return null;
    }
}
