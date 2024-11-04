package com.roumaysae.comptecqrseventsourcing.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequestDTO {
    private double initialbalance;
    private String currency;
}
