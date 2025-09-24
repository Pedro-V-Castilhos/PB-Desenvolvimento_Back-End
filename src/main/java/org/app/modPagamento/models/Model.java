package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public abstract class Model<T extends Model<T>> {
    // Campos das classes modelo =========================
    public int id;
    private String encripKeyPath;
    private String decripKeyPath;

    // Construtores =====================================
    public Model(int id, String encripKeyPath, String decripKeyPath) {
        this.id = id;
        this.encripKeyPath = encripKeyPath;
        this.decripKeyPath = decripKeyPath;
    }

    // Construtor de quando transoforma uma linha csv em Model
    public Model(String encripKeyPath, String decripKeyPath) {
        this.encripKeyPath = encripKeyPath;
        this.decripKeyPath = decripKeyPath;
    }

    public Model() {};

    // Métodos das classes modelos ==========================================
    public String toCsv() throws RuntimeException{
        // Prepara o array para guardar todos os campos da classe
        List<Field> allFields = new ArrayList<>();

        // Percorre a árvore de herança da classe, guardando os campos declarados no array
        Class<?> current = this.getClass();
        while (current != null && current != Object.class) {

            List<Field> declared = new ArrayList<>();

            // Pega todos os campos declarados da classe
            for (Field field : current.getDeclaredFields()) {
                if(Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                declared.add(field);
            }

            // Seta todos os campos como acessíveis, para leitura
            declared.forEach(f -> f.setAccessible(true));
            // Adiciona todos os campos da classe da iteração no array
            // Reversed é para organização na ordem final que os campos irão ficar*
            allFields.addAll(declared.reversed());

            // Passa para a classe pai
            current = current.getSuperclass();
        }

        // Faz o retorno da leitura de todos os campos
        return String.join(",",
                allFields.reversed().stream()
                .map(field -> {
                    try {
                        return field.get(this) == null ? "" : field.get(this).toString();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray(String[]::new));
    }
}
