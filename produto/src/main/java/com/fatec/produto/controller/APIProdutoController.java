package com.fatec.produto.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.produto.model.Produto;
import com.fatec.produto.servico.IProdutoServico;

@RestController
@RequestMapping("api/v1/produtos")
public class APIProdutoController {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	IProdutoServico produtoServico;
	/*
	 * consulta catalogo retorna um arquivo json de produtos
	 */
	@CrossOrigin
	@GetMapping
	public ResponseEntity <Object> consultaTodos(){
		logger.info(">>>>>> apicontroller consulta todos");
		return ResponseEntity.status(HttpStatus.OK).body(produtoServico.consultaCatalogo());
	}
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Object> cadastraProduto(@RequestBody Produto p) {
		logger.info(">>>>>> apicontroller cadastrar produto iniciado");
		Optional<Produto> produto = produtoServico.cadastrar(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(produto.get());
	}

}
