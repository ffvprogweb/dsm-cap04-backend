package com.fatec.produto.servico;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.produto.model.IProdutoRepository;
import com.fatec.produto.model.Produto;
@SpringBootTest
class Req01CadastrarProdutoTests {
	@Autowired
	ProdutoServico produtoServico;
	@Autowired
	IProdutoRepository repository;
	

	@Test
	void ct01_cadastrar_produto_com_sucesso() {
		// dado que o usuario entrou com as informações do produto a ser cadastrado
		Produto produto1 = new Produto("Eletrobomba 110V para Maquina de Lavar e Lava Louças", "maquina de lavar",
				51.66, 12);
		//quando o usuario confirma a operacao
		Produto produtoRetornado = produtoServico.cadastrar(produto1).get();
		//entao retorna o produto cadastrado incluindo o id
		assertTrue(produto1.equals(produtoRetornado));
		assertFalse(produtoRetornado.getId()==0);
	}
	@Test
	void ct02_cadastrar_produto_com_descricao_invalida_branco() {
		Produto produto = new Produto();
		assertThrows(IllegalArgumentException.class, () -> produto.setDescricao(""));
	}
}
