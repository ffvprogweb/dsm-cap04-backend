package com.fatec.produto.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.produto.model.Cliente;
import com.fatec.produto.servico.IClienteServico;

@RestController
@RequestMapping("api/v1/clientes")
public class APIClienteController {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	IClienteServico clienteServico;
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Object> cadastrarCliente(@RequestBody Cliente c) {
		logger.info(">>>>>> apicontroller cadastrar cliente iniciado");
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteServico.cadastrar(c));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados invalidos");
		}

	}
}
