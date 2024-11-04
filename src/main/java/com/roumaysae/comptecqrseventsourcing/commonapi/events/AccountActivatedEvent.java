package com.roumaysae.comptecqrseventsourcing.commonapi.events;

import lombok.Getter;
import com.roumaysae.comptecqrseventsourcing.commonapi.enums.AccountStatus;

public class AccountActivatedEvent extends BaseEvent<String>{
    @Getter
    private final AccountStatus status;
    public AccountActivatedEvent(String id ,AccountStatus status) {
        super(id);
        this.status = status;
    }
}
