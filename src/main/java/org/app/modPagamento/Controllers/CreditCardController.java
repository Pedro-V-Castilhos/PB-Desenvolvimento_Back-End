package org.app.modPagamento.Controllers;

import org.app.modPagamento.models.Client;
import org.app.modPagamento.models.CreditCard;

public class CreditCardController extends Controller {
    public CreditCardController() {
        super("/creditCards", CreditCard.csvFileName, CreditCard.class);
    }
}
