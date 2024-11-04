package com.roumaysae.comptecqrseventsourcing.query.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;
import com.roumaysae.comptecqrseventsourcing.commonapi.enums.AccountStatus;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private String id;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;
    @OneToMany(mappedBy="account")
    private Collection<Operation> operations;


}
