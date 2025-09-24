package org.app.modPagamento.Controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.app.modPagamento.models.Model;

import java.util.List;

public abstract class Controller {
    // Configuração da API ====================================================
    public void config(Javalin app, String resource) {
        app.get(resource, this::getAll);
        app.get(resource + "/{id}", this::getById);
        app.post(resource, this::post);
        app.put(resource + "/{id}", this::put);
        app.patch(resource, this::patch);
        app.delete(resource + "/{id}", this::delete);
    }

    // Métodos padrão ==========================================================
    public abstract List<Model> getAll(Context context);
    public abstract Model getById(Context context);
    public abstract boolean post(Context context);
    public abstract boolean put(Context context);
    public abstract boolean patch(Context context);
    public abstract Model delete(Context context);
}
