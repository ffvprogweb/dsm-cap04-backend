package com.fatec.produto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.produto.servico.IProdutoServico;

@RestController
@RequestMapping("api/v1/produtos")
public class APIProdutoController {
	@Autowired
	IProdutoServico produtoServico;
	@GetMapping
	public ResponseEntity <Object> consultaTodos(){
		return ResponseEntity.status(HttpStatus.OK).body(produtoServico.consultaCatalogo());
	}

}
