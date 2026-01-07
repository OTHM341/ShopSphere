package org.example.inventoryservice;

import org.example.inventoryservice.entities.Product;
import org.example.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            // Supprime tous les produits existants
            productRepository.deleteAll();

            // Crée 20 produits avec des données aléatoires
            Random random = new Random();
            String[] productNames = {
                    "Computer", "Laptop", "Smartphone", "Tablet", "Printer",
                    "Monitor", "Keyboard", "Mouse", "Headphones", "Speaker",
                    "Router", "Hard Drive", "SSD", "Webcam", "Microphone",
                    "Graphics Card", "Processor", "Motherboard", "RAM", "Power Supply"
            };

            for (int i = 0; i < 20; i++) {
                Product product = Product.builder()
                        .name(productNames[i % productNames.length] + " " + (i + 1))
                        .price(500 + random.nextDouble() * 5000)
                        .quantity(5 + random.nextInt(50))
                        .build();

                productRepository.save(product);
            }

            // Affiche tous les produits
            System.out.println("=== Liste des produits avec IDs séquentiels ===");
            productRepository.findAll().forEach(p -> {
                System.out.printf("ID: %d | Nom: %-25s | Prix: %.2f€ | Quantité: %d%n",
                        p.getId(), p.getName(), p.getPrice(), p.getQuantity());
            });
            System.out.println("================================================");
        };
    }
}