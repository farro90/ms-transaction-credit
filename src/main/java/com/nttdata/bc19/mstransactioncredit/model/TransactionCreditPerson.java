package com.nttdata.bc19.mstransactioncredit.model;

import com.nttdata.bc19.mstransactioncredit.model.responseWC.CreditPerson;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class TransactionCreditPerson extends BaseModel{
    //private  String code;
    private String idCreditPerson;
    private CreditPerson creditPerson;
    private LocalDateTime transactionDate;
    private String transactionTypeCredit;
    private double amount;
}
