package com.roumaysae.comptecqrseventsourcing.query.repositories;
import com.roumaysae.comptecqrseventsourcing.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
