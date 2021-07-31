package ro.fasttrackit.curs22.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.fasttrackit.curs22.service.TransactionService;

@Controller
@RequestMapping("transactions")
public class TransactionsUIController {
    private final TransactionService service;

    public TransactionsUIController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    String transactionsPage(Model model){
        model.addAttribute("transactions", service.getAll());
        return "transactions";
    }
}
