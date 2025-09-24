package org.app.modPagamento.Controllers;

import org.app.modPagamento.models.Client;

public class ClientController extends Controller {
    public ClientController() {
        super("/clients", Client.csvFileName, Client.class);
    }
}
