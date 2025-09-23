package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
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
    public static String csvFilePath = "";
    private String encripKeyPath;
    private String decripKeyPath;

    public Model() throws IOException {
        if(Files.exists(Paths.get(csvFilePath))){
            id = CsvManager.listContent(csvFilePath).size();
        }else{
            id = 1;
        }
    }

    // Métodos das classes modelos ---------------------
    public String toCsv(Model<T> obj) throws RuntimeException{
        // Prepara o array para guardar todos os campos da classe
        List<Field> allFields = new ArrayList<>();

        // Percorre a árvore de herança da classe, guardando os campos declarados no array
        Class<?> current = obj.getClass();
        while (current != null && current != Object.class) {
            Field[] declared = current.getDeclaredFields();
            Arrays.stream(declared).forEach(f -> f.setAccessible(true));
            allFields.addAll(Arrays.asList(declared).reversed());

            current = current.getSuperclass();
        }

        // Faz o retorno da leitura de todos os campos
        return String.join(",",
                allFields.reversed().stream()
                .map(field -> {
                    try {
                        return field.get(obj) == null ? "" : field.get(obj);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray(String[]::new));
    }

    public JSONObject toJson(){
        return null;
    }
}
