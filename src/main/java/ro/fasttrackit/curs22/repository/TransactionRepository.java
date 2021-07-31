package ro.fasttrackit.curs22.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs22.model.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
