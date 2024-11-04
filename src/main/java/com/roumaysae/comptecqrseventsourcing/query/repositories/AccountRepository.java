package com.roumaysae.comptecqrseventsourcing.query.repositories;

import com.roumaysae.comptecqrseventsourcing.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
