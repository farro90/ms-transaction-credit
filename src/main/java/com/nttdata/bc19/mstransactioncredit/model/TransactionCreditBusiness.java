package com.nttdata.bc19.mstransactioncredit.model;

import com.nttdata.bc19.mstransactioncredit.model.responseWC.CreditBusiness;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class TransactionCreditBusiness extends BaseModel{
    //private  String code;
    private String idCreditBusiness;
    private CreditBusiness creditBusiness;
    private LocalDateTime transactionDate;
    private String transactionTypeCredit;
    private double amount;
}
