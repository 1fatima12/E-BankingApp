package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteCourant;
import com.example.demo.entities.CompteEpargne;
import com.example.demo.entities.Operation;
import com.example.demo.entities.Retrait;
import com.example.demo.entities.Versement;
import com.example.demo.service.IBanqueService;

@SpringBootApplication
@ComponentScan({"com.example"})
public class EBankingAppApplication implements CommandLineRunner {
    @Autowired
	ClientRepository clientrepository;
    @Autowired
	CompteRepository compterepository;
    @Autowired
	OperationRepository operationrepository;
    @Autowired
    IBanqueService banqueservicce;
	public static void main(String[] args) {
		SpringApplication.run(EBankingAppApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		/*Client c1=clientrepository.save(new Client("mardi","fatima@gmail.com"));
		Client c2=clientrepository.save(new Client("MARDI","Sara@gmail.com"));
		Compte cp1=compterepository.save(new CompteCourant(new Date(),10000,c1,300));
		Compte cp2=compterepository.save(new CompteEpargne(new Date(), 17000,c2,3.9));
		Operation o1= operationrepository.save(new Versement(new Date(),900,cp1));
		Operation o2= operationrepository.save(new Versement(new Date(),9080,cp1));
		Operation o3= operationrepository.save(new Retrait(new Date(),900,cp1));
		Operation o4= operationrepository.save(new Versement(new Date(),789,cp2));
		Operation o5= operationrepository.save(new Versement(new Date(),89,cp2));
		Operation o6= operationrepository.save(new Retrait(new Date(),900,cp1));
		banqueservicce.verser((long) 3, 100);*/

	}

}
