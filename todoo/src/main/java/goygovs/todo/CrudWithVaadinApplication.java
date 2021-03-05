package goygovs.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudWithVaadinApplication {

	private static final Logger log = LoggerFactory.getLogger(CrudWithVaadinApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CrudWithVaadinApplication.class);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository repository) {
		return (args) -> {
			// save a couple of users
			repository.save(new User("Life", "Be a good fella", "Said", "Goygov"));
			repository.save(new User("Middleearth", "Become a king", "Aragorn", "Elessar"));

			// fetch all users
			log.info("Users found with findAll():");
			log.info("-------------------------------");
			for (User user : repository.findAll()) {
				log.info(user.toString());
			}
			log.info("");

			// fetch an individual user by ID
			User user = repository.findById(1L).get();
			log.info("User found with findOne(1L):");
			log.info("--------------------------------");
			log.info(user.toString());
			log.info("");

			// fetch users by last name
			log.info("User found with findByLastNameStartsWithIgnoreCase('Goygov'):");
			log.info("--------------------------------------------");
			for (User goygov : repository
					.findByLastNameStartsWithIgnoreCase("Bauer")) {
				log.info(goygov.toString());
			}
			log.info("");
		};
	}

}