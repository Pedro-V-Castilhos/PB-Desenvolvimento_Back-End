package org.app.modPagamento.models;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

@Setter
@Getter
public class Client extends User {
    private String address;
    private ArrayList<CreditCard> creditCards;
    private ArrayList<Compra> compras;

    public Client(String name, String cpf, String phone, String email, String address) throws IOException {
        super("Clients.csv", name, cpf, phone, email);
        this.address = address;
    }

    public void loadCreditCards() throws IOException {}
    public void loadPurchases() throws IOException {}
}
