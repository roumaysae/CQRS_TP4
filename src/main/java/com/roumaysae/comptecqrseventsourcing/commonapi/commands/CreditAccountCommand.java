package com.roumaysae.comptecqrseventsourcing.commonapi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;



public class CreditAccountCommand extends BaseCommand<String>{
    @TargetAggregateIdentifier
    @Getter
    double amount;
    @Getter
    private String currency;
    public CreditAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
