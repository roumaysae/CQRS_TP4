package com.roumaysae.comptecqrseventsourcing.commands.aggregates;

import lombok.extern.slf4j.Slf4j;
import com.roumaysae.comptecqrseventsourcing.commonapi.commands.CreateAccountCommand;
import com.roumaysae.comptecqrseventsourcing.commonapi.commands.CreditAccountCommand;
import com.roumaysae.comptecqrseventsourcing.commonapi.commands.DebitAccountCommand;
import com.roumaysae.comptecqrseventsourcing.commonapi.enums.AccountStatus;
import com.roumaysae.comptecqrseventsourcing.commonapi.events.AccountActivatedEvent;
import com.roumaysae.comptecqrseventsourcing.commonapi.events.AccountCreatedEvent;
import com.roumaysae.comptecqrseventsourcing.commonapi.events.AccountCreditedEvent;
import com.roumaysae.comptecqrseventsourcing.commonapi.events.AccountDebitedEvent;
import com.roumaysae.comptecqrseventsourcing.commonapi.exceptions.AmountNegativeException;
import com.roumaysae.comptecqrseventsourcing.commonapi.exceptions.BalanceNotSufficientException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;
    //Obligatoirement on doit avoir un constructeur sans parametre

    public AccountAggregate() {

    }
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        if(command.getInitialBalance()<0) throw new RuntimeException("Negative balance");
        //Event Sourcing
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                command.getCurrency(),
                AccountStatus.CREATED
        ));

    }
    @EventSourcingHandler
    //Etat de l'application
    public void on(AccountCreatedEvent event){
        log.info("AccountCreatedEven sourced");
        this.accountId=event.getId();
        this.balance=event.getInitialBalance();
        this.currency=event.getCurrency();
        this.status=AccountStatus.CREATED;
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED
        ));
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        this.status=event.getStatus();
    }
    @CommandHandler
    public void handle(CreditAccountCommand command){
        if(command.getAmount()<0) throw new AmountNegativeException("Amount should not be negative");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.balance+=event.getAmount();
     }
    @CommandHandler
    public void handle(DebitAccountCommand command){
        if(command.getAmount()<0) throw new AmountNegativeException("Amount should not be negative");
        if(this.balance<command.getAmount()) throw new BalanceNotSufficientException("Balance Not Sufficient Exception");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.balance-=event.getAmount();
    }


}
