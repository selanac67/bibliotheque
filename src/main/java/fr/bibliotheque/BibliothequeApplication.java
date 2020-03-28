package fr.bibliotheque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@ComponentScan({"storage","fr.bibliotheque"})
public class BibliothequeApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BibliothequeApplication.class, args);
		
	}
	

}
