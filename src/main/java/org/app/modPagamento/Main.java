package org.app.modPagamento;

import org.app.modPagamento.entidades.Usuario;
import org.app.modPagamento.servicos.ManagerCSV;

import java.util.ArrayList;
import java.util.Scanner;

import static org.app.modPagamento.entidades.Usuario.listarUsuarios;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        int option = 0;
        do{
            System.out.println("O que deseja fazer:\n" +
                    "1: Incluir usuário\n" +
                    "2: Listar usuários\n" +
                    "3: Encerrar o programa");

            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();

            switch(option){
                case 1:
                    Usuario newUser = inputUsuario();
                    newUser.salvarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Erro: Opção não válida, digite novamente!");
            }
        }while(option != 3);
    }

    public static Usuario inputUsuario(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o ID do Usuário: ");
        int idUsuario = sc.nextInt();
        System.out.println("Digite o nome do Usuario: ");
        String nomeUsuario = sc.nextLine();
        System.out.println("Digite o CPF do Usuário: ");
        String cpfUsuario = sc.nextLine();
        System.out.println("Digite o número de telefone do Usuário: ");
        String telefoneUsuario = sc.nextLine();
        System.out.println("Digite o email do Usuário: ");
        String emailUsuario = sc.nextLine();

        return new Usuario(idUsuario, nomeUsuario, cpfUsuario, telefoneUsuario, emailUsuario);
    }
}