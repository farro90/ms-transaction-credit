package com.nttdata.bc19.mstransactioncredit.request;

import lombok.Data;

@Data
public class TransactionCreditBusinessRequest {
    private String idCreditBusiness;
    private double amount;
    private String transactionTypeCredit;
}
