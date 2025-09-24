package org.app.modPagamento.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.app.modPagamento.models.Model;
import org.app.modPagamento.servicos.CsvManager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Controller<T extends Model> {
    // Campos do Controller ===================================================
    private String resource;
    private String csvFilePath;
    private Class<T> clazz;

    // Construtores ==========================================================
    public Controller(String resource, String csvFilePath, Class<T> clazz) {
        this.resource = resource;
        this.csvFilePath = csvFilePath;
        this.clazz = clazz;
    }

    // Configuração da API ====================================================
    public void config(Javalin app) {
        app.get(resource, this::getAll);
        app.get(resource + "/{id}", this::getById);
        app.post(resource, this::post);
//        app.put(resource + "/{id}", this::put);
//        app.patch(resource, this::patch);
//        app.delete(resource + "/{id}", this::delete);
    }

    // Métodos padrão ==========================================================
    public void getAll(Context context){
        // Prepara a lista para retorno
        List<Model> models = new ArrayList<>();

        // Pega a lista do conteúdo do Arquivo Csv
        ArrayList<String[]> csvData = new ArrayList<>();
        try{
            csvData = CsvManager.listContent(csvFilePath);
        } catch (IOException e) {
            context.status(500);
            System.out.println("Erro ao ler o arquivo: " + csvFilePath);
        }

        // Para cada linha do arquivo, cria um novo objeto
        for (int i = 1; i < csvData.size(); i++) {
            Model<?> newObject = null;
            try {
                newObject = clazz.getDeclaredConstructor(String[].class).newInstance((Object) csvData.get(i));
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |NoSuchMethodException e) {
                System.out.println("Erro ao instanciar objeto: " + e.getMessage());
            }
            models.add(newObject);
        }

        // Retorna a lista de modelos
        try {
            context.status(200);
            context.result(new ObjectMapper().writeValueAsString(models));
        }catch(IOException e){
            context.status(500);
            System.out.println("Erro ao construir JSON: " + e.getMessage());
        }
    };

    public void getById(Context context){
        Optional<String[]> objectCsv = null;
        try {
            objectCsv = CsvManager.getLineInFile(context.pathParam("id"), csvFilePath);
        }catch (IOException e){
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }

        if (objectCsv != null && objectCsv.isPresent()) {
            try {
                context.status(200);
                context.json(clazz.getDeclaredConstructor(String[].class).newInstance((Object) objectCsv.get()));
            }catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                context.status(500);
                System.out.println("Erro ao instanciar objeto: " + e.getMessage());
            }
        }else{
            context.status(404);
        }
    };

    public void post(Context context){
        Model newObject = context.bodyAsClass(clazz);
        try {
            CsvManager.insertObjectAsLine(newObject, csvFilePath);
            context.status(201);
        }catch (IOException e){
            context.status(500);
            System.out.println("Erro ao inserir objeto: " + e.getMessage());
        }
    };

//    public boolean put(Context context);
//    public boolean patch(Context context);
//    public Model delete(Context context);
}
