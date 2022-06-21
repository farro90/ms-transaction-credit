package com.nttdata.bc19.mstransactioncredit.request;

import lombok.Data;

@Data
public class TransactionCreditPersonRequest {
    private String idCreditPerson;
    private double amount;
    private String transactionTypeCredit;
}
