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
    private String encripKeyPath;
    private String decripKeyPath;

    // Métodos das classes modelos ---------------------
    public String toCsv() throws RuntimeException{
        // Prepara o array para guardar todos os campos da classe
        List<Field> allFields = new ArrayList<>();

        // Percorre a árvore de herança da classe, guardando os campos declarados no array
        Class<?> current = this.getClass();
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
                        return field.get(this) == null ? "" : field.get(this);
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
