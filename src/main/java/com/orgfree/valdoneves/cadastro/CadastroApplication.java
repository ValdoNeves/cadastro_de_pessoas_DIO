package com.orgfree.valdoneves.cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;

//@PersistenceContext(name = "projeto_cadastro")
@SpringBootApplication
public class CadastroApplication {

	public static void main(String[] args) {

		SpringApplication.run(CadastroApplication.class, args);
	}

}
