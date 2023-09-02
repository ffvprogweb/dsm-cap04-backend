package com.fatec.produto.servico;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fatec.produto.model.IProdutoRepository;
import com.fatec.produto.model.Produto;

@Configuration
public class LoadDatabase {
	@Bean
	CommandLineRunner initDatabase (IProdutoRepository repository) {
		return args -> {
			Produto produto1 = new Produto("Tirante Brastemp", "maquina de lavar", 51.66,14);
			repository.saveAll(Arrays.asList(produto1));
		};
	}

}
