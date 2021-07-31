package ro.fasttrackit.curs22;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.curs22.model.TransactionType;
import ro.fasttrackit.curs22.model.entity.Transaction;
import ro.fasttrackit.curs22.repository.TransactionRepository;

import java.util.List;

import static ro.fasttrackit.curs22.model.TransactionType.BUY;
import static ro.fasttrackit.curs22.model.TransactionType.SELL;

@SpringBootApplication
public class Curs22Application {

	public static void main(String[] args) {
		SpringApplication.run(Curs22Application.class, args);
	}

	@Bean
	CommandLineRunner atStartup(TransactionRepository repository){
		return args -> {
			repository.saveAll(List.of(
					new Transaction("Masa", 1000, BUY),
					new Transaction("Laptop", 3000, SELL),
					new Transaction("Paine", 5.4, BUY),
					new Transaction("Monitor", 450, SELL),
					new Transaction("Rucsac", 232, BUY)
			));
		};
	}
}
