package ro.fasttrackit.curs22.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs22.model.entity.Transaction;
import ro.fasttrackit.curs22.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public Transaction create(Transaction newTransaction) {
        newTransaction.setId(null);
        return repository.save(newTransaction);
    }

    public Transaction deleteTransaction(int transactionId) {
        Optional<Transaction> transaction = repository.findById(transactionId);
        transaction.ifPresent(repository::delete);
        return transaction.orElse(null);
    }

    public Optional<Transaction> replaceTransaction(int transactionId, Transaction transaction) {
        return repository.findById(transactionId)
                .map(dbTransaction -> patchTransaction(dbTransaction, transaction))
                .map(repository::save);
    }

    private Transaction patchTransaction(Transaction dbTransaction, Transaction transaction) {
        dbTransaction.setAmount(transaction.getAmount());
        dbTransaction.setProduct(transaction.getProduct());
        dbTransaction.setType(transaction.getType());
        return dbTransaction;
    }

    public Optional<Transaction> getTransaction(int transactionId) {
        return repository.findById(transactionId);
    }
}
