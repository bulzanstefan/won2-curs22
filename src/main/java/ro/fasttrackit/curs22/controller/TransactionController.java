package ro.fasttrackit.curs22.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs22.model.entity.Transaction;
import ro.fasttrackit.curs22.service.TransactionService;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    Transaction addTransaction(@RequestBody Transaction newTransaction) {
        return service.create(newTransaction);
    }

    @PutMapping("{transactionId}")
    Transaction replaceTransaction(@PathVariable int transactionId, @RequestBody Transaction transaction) {
        return service.replaceTransaction(transactionId, transaction)
                .orElseThrow(() -> new RuntimeException("Could not find transaction with id " + transactionId));
    }

    @DeleteMapping("{transactionId}")
    Transaction deleteTransaction(@PathVariable int transactionId) {
        return service.deleteTransaction(transactionId);
    }
}
