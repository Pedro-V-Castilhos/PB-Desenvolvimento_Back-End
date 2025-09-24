package org.app.modPagamento.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.app.modPagamento.models.Client;
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
    public void getAll(Context context) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Prepara a lista para retorno
        List<Model> models = new ArrayList<>();

        // Pega a lista do conteúdo do Arquivo Csv
        ArrayList<String[]> csvData = CsvManager.listContent(csvFilePath);

        // Para cada linha do arquivo, cria um novo objeto
        for (int i = 1; i < csvData.size(); i++) {
            Model<?> newObject = clazz.getDeclaredConstructor(String[].class).newInstance((Object) csvData.get(i));
            models.add(newObject);
        }

        // Retorna a lista de modelos
        context.result(new ObjectMapper().writeValueAsString(models));
    };

    public void getById(Context context) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Optional<String[]> objectCsv = CsvManager.getLineInFile(context.pathParam("id"), csvFilePath);

        context.json(clazz.getDeclaredConstructor().newInstance(objectCsv.get()));
    };

    public void post(Context context){
        try{
            Model newObject = context.bodyAsClass(clazz);
            CsvManager.insertObjectAsLine(newObject, csvFilePath);
            context.result("Ok");
        } catch (Exception e) {
            context.result(e.getMessage());
        }
    };
//    public boolean put(Context context);
//    public boolean patch(Context context);
//    public Model delete(Context context);
}
