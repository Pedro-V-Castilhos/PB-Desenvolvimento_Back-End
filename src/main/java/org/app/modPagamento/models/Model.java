package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;
import org.app.modPagamento.servicos.CsvManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public abstract class Model<T extends Model<T>> {
    // Campos das classes modelo -----------------------
    public int id;
    private String csvFilePath;
    private String encripKeyPath;
    private String decripKeyPath;

    // Serviços das classes modelo ---------------------
    private CsvManager csvManager = new CsvManager();

    // Métodos das classes modelos ---------------------
    public void insert(T obj) throws IOException {
        csvManager.addLineInFile(this.toCsv(), csvFilePath);
    }

    public void delete(T obj) {

    }

    public T find(T obj) {
        return null;
    }

    public ArrayList<T> listAll() {
        return null;
    }

    public String[] toCsv() throws IllegalArgumentException{
        // Pega todos os campos da classe e torna eles acessíveis------------
        Field[] fields = this.getClass().getFields();
        Arrays.stream(fields).forEach(field -> {field.setAccessible(true);});

        // Faz o retorno da leitura de todos os campos da classe ------------
        return Arrays.stream(fields)
                .map(field ->
                {
                    try {
                        return field.get(this.getClass());
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
