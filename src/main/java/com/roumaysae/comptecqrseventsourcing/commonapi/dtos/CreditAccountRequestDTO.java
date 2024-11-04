package com.roumaysae.comptecqrseventsourcing.commonapi.dtos;

import lombok.Data;

@Data
public class CreditAccountRequestDTO {
    private String accoundId;
    private double amount;
    private String currency;

}
