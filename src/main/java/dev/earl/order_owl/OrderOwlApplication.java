package dev.earl.order_owl;

import com.github.javafaker.Faker;
import dev.earl.order_owl.controller.ProductController;
import dev.earl.order_owl.model.*;
import dev.earl.order_owl.model.converter.Status;
import dev.earl.order_owl.model.embedded.Address;
import dev.earl.order_owl.post.PostGeneratedService;
import dev.earl.order_owl.repository.*;
import dev.earl.order_owl.service.CartService;
import dev.earl.order_owl.service.CustomerService;
import dev.earl.order_owl.service.ProductService;
import dev.earl.order_owl.service.ShipmentService;
import dev.earl.order_owl.service.mapper.CustomerMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class OrderOwlApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderOwlApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
											   CustomerService customerService,
											   CustomerMapper customerMapper,
											   PaymentRepository paymentRepository,
											   OrderRepository orderRepository,
											   ProductService productService,
											   ShipmentRepository shipmentRepository,
											   CartRepository cartRepository,
											   CartService cartService)
	{
		return args -> {
			productService.fillDbWithProducts();
			Faker faker = new Faker();
			for (int i = 0; i < 100; i++) {
				Address address = Address.builder()
						.addressLine1(faker.address().streetAddress())
						.addressLine2(faker.address().streetAddress())
						.state(faker.address().state())
						.city(faker.address().city())
						.country(faker.address().country())
						.postalCode(faker.address().zipCode())
						.build();


				String firstName = faker.name().firstName();
				String lastName = faker.name().lastName();
				Customer customer = Customer.builder()
						.address(address)
						.email(firstName + lastName + "@mail.com")
						.phone(faker.phoneNumber().cellPhone())
						.name(firstName + " " + lastName)
						.build();
				Customer savedCustomer = customerRepository.save(customer);

				Order order = Order.builder()
						.orderDate(LocalDate.now())
						.text(faker.commerce().productName())
						.customer(customer)
						.build();
				orderRepository.save(order);

				Shipment shipment = Shipment.builder()
						.shipmentDate(LocalDate.now())
						.status(Status.PENDING)
						.address(address)
						.customer(customer)
						.build();
				shipmentRepository.save(shipment);

				customerRepository.save(customer);
				Random random = new Random();


				Map<Product, Integer> productToQuantity = new HashMap<>();
				for(int j = 0; j < 5; j++){
					int ranInt = random.nextInt(20) + 1; //random number between 20 and 1
					Product product = productService.getProduct(ranInt);
					productToQuantity.put(product, ranInt);
				}

				Cart cart = Cart.builder()
						.customer(customer)
						.numberOfItems(productToQuantity.size())
						.subtotal(random.nextDouble(100) + 1)
						.productToQuantity(productToQuantity)
						.build();

				customer.setCart(cart);
				cartRepository.save(cart);



		    }
		};
	}
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


