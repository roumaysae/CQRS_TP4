package com.roumaysae.comptecqrseventsourcing.commonapi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DebitAccountCommand extends BaseCommand<String>{
    @TargetAggregateIdentifier
    @Getter
    double amount;
    @Getter
    private String currency;
    public DebitAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
