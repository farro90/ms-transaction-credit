package com.nttdata.bc19.mstransactioncredit.model.responseWC;

import lombok.Data;

@Data
public class PasiveProduct {
    private String id;
    private String name;
    private double maintenanceCommission;
    private double transactionCommission;
    private double minimumOpeningAmount;
    private int numLimitMovements;
    private int dayMovement;
    private Boolean allowBusinessClient;
    private Boolean allowPersonClient;
}
