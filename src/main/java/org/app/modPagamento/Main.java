package org.app.modPagamento;

import io.javalin.Javalin;
import org.app.modPagamento.Controllers.ClientController;
import org.app.modPagamento.Controllers.CreditCardController;
import org.app.modPagamento.models.Admin;
import org.app.modPagamento.models.Client;
import org.app.modPagamento.models.Item;
import org.app.modPagamento.models.Payment;
import org.app.modPagamento.servicos.CsvManager;

import java.io.IOException;
import java.util.ArrayList;

import static io.javalin.apibuilder.ApiBuilder.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){
        // Criar os arquivos ------------------------------------------
        try {
            Client newClient = new Client("Pedro", "000000000", "62999093237", "pedro.castilhos@al.infnet.edu.br", "Rua dos bobos n°0");
            CsvManager.insertObjectAsLine(newClient, Client.csvFileName);
        }catch(Exception e){
            System.out.println("Erro ao criar arquivo!" + e.getMessage());
        }

//        Javalin app = Javalin.create(config -> {
//                    config.router.mount(router -> {}).apiBuilder(() -> {
//                        path("clients", () -> {
//                            path("", () -> {
//                                get(ctx -> {
//                                    ArrayList<String[]> result = CsvManager.listContent(Client.csvFileName);
//                                    ctx.json(result);
//                                });
//                                post(ctx -> {
//                                    try {
//                                        String[] data = ctx.bodyAsClass(String[].class);
//                                        Client client = new Client(data);
//                                        CsvManager.insertObjectAsLine(client, Client.csvFileName);
//                                        ctx.result("Ok");
//                                    } catch (Exception e) {
//                                        ctx.result(e.getMessage());
//                                    }
//                                });
//                            });
//                        });
//                    });
//                })
//                .get("/", ctx -> ctx.result("Aplicação está rodando normalmente!"));

        Javalin app = Javalin.create();

        new ClientController().config(app);
        new CreditCardController().config(app);
        app.get("/", ctx -> ctx.result("Hello World"));

        app.start(7000);
    }
}