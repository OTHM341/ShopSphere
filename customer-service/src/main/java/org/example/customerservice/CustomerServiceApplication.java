package org.example.customerservice;

import org.example.customerservice.entities.Customer;
import org.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.deleteAll();

            List<Customer> customers = Arrays.asList(
                    Customer.builder()
                            .firstName("Othmane")
                            .lastName("Mtk")
                            .email("othmane.mtk@company.com")
                            .build(),

                    Customer.builder()
                            .firstName("Ali")
                            .lastName("Badr")
                            .email("ali.badr@company.com")
                            .build(),

                    Customer.builder()
                            .firstName("Jean")
                            .lastName("Dupont")
                            .email("jean.dupont@gmail.com")
                            .build(),

                    Customer.builder()
                            .firstName("Marie")
                            .lastName("Martin")
                            .email("marie.martin@outlook.com")
                            .build(),

                    Customer.builder()
                            .firstName("Pierre")
                            .lastName("Durand")
                            .email("pierre.durand@yahoo.com")
                            .build(),

                    Customer.builder()
                            .firstName("Sophie")
                            .lastName("Leroy")
                            .email("sophie.leroy@icloud.com")
                            .build(),

                    Customer.builder()
                            .firstName("Thomas")
                            .lastName("Moreau")
                            .email("thomas.moreau@business.com")
                            .build(),

                    Customer.builder()
                            .firstName("Julie")
                            .lastName("Simon")
                            .email("julie.simon@enterprise.com")
                            .build(),

                    Customer.builder()
                            .firstName("Nicolas")
                            .lastName("Laurent")
                            .email("nicolas.laurent@tech.com")
                            .build(),

                    Customer.builder()
                            .firstName("Isabelle")
                            .lastName("Petit")
                            .email("isabelle.petit@web.com")
                            .build()
            );

            // Sauvegarde les 10 clients
            customerRepository.saveAll(customers);

            // Affichage simple
            System.out.println("\n✅ CUSTOMER SERVICE INITIALIZED");
            System.out.println("📋 10 CLIENTS CREATED:");
            System.out.println("-".repeat(50));

            customerRepository.findAll().forEach(c -> {
                System.out.printf("ID: %2d | %s %s | %s%n",
                        c.getId(), c.getFirstName(), c.getLastName(), c.getEmail());
            });

            System.out.println("-".repeat(50));
        };
    }
}