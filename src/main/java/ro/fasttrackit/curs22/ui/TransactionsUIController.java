package ro.fasttrackit.curs22.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.fasttrackit.curs22.service.TransactionService;

import static java.util.Optional.ofNullable;

@Controller
@RequestMapping("transactions")
public class TransactionsUIController {
    private final TransactionService service;

    public TransactionsUIController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    String transactionsPage(Model model, @RequestParam(required = false) Integer showTransaction) {
        model.addAttribute("transactions", service.getAll());
        ofNullable(showTransaction)
                .flatMap(service::getTransaction)
                .ifPresent(transaction -> model.addAttribute("showTransaction", transaction));
        return "transactions";
    }

    @GetMapping("{transactionId}")
    String singleTransactionPage(@PathVariable int transactionId, Model pageModel) {
        pageModel.addAttribute("transaction", service.getTransaction(transactionId).orElse(null));

        return "single-transaction";
    }
}
