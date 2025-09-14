package org.app.modPagamento;

import io.javalin.Javalin;
import org.app.modPagamento.models.Item;
import org.app.modPagamento.models.Usuario;

import java.util.Scanner;

import static org.app.modPagamento.models.Usuario.listarUsuarios;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){
        try {
            Item item = new Item("AAAAA", 5, 10.0);
            item.insert(item);
        }catch (Exception e){
            System.out.println("Erro ao instanciar a classe item" + e.getMessage());
        }

        Javalin app = Javalin.create();

        app.get("/", ctx -> ctx.result("Aplicação está rodando normalmente!"));

        app.start(7000);
    }
}