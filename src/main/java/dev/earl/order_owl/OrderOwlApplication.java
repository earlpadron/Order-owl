package dev.earl.order_owl;

import dev.earl.order_owl.post.PostGeneratedService;
import dev.earl.order_owl.repository.CustomerRepository;
import dev.earl.order_owl.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@SpringBootApplication
public class OrderOwlApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderOwlApplication.class, args);
	}

   @Bean
	public CommandLineRunner commandLineRunner(CustomerRepository customerRepository, PaymentRepository paymentRepository){
		return args -> {
//			Faker faker = new Faker();
//			for(int i = 0; i < 100; i++){
//				Address address = Address.builder()
//						.addressLine1(faker.address().streetAddress())
//						.addressLine2(faker.address().streetAddress())
//						.state(faker.address().state())
//						.city(faker.address().city())
//						.country(faker.address().country())
//						.postalCode(faker.address().zipCode())
//						.build();
//
//				Customer customer = Customer.builder()
//						.address(address)
//						.email(faker.name().firstName() + "@mail.com")
//						.phone(faker.phoneNumber().cellPhone())
//						.creditLimit(Math.random() * 10_000 + 1)
//						.customerName(faker.name().fullName())
//
//						.build();
//				customerRepository.save(customer);

		};
	}

	/**
	 * This will create client methods for Post that will be generated at run-time
	 * @return
	 */
//	@Bean
//	PostGeneratedService postGeneratedService(){
//		RestClient client = RestClient.create("https://jsonlaceholder.typicode.com");
//		HttpServiceProxyFactory factory = HttpServiceProxyFactory
//				.builderFor(RestClientAdapter.create(client))
//				.build();
//		return factory.createClient(PostGeneratedService.class);
//	}

}
