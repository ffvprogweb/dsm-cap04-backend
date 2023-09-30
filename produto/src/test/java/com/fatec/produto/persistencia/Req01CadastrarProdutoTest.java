package com.fatec.produto.persistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.produto.model.IProdutoRepository;
import com.fatec.produto.model.Produto;

@SpringBootTest
class Req01CadastrarProdutoTest {
	@Autowired
	IProdutoRepository repository;

	@Test
	void ct01_cadastrar_produto_com_sucesso() {
		// dado que nao existe nenhum produto cadastrado
		repository.deleteAll();
		// quando o usuario cadastrar um produto
		Produto produto1 = new Produto("Eletrobomba 110V para Maquina de Lavar e Lava Louças", "maquina de lavar",
				51.66, 12);
		Produto p = repository.save(produto1);
		// entao o produto fica disponivel para consulta
		assertEquals(1, repository.count());
		assertNotNull(p);
		assertTrue(produto1.equals(p));

	}

	@Test
	void ct02_cadastrar_produto_com_descricao_invalida_vazio() {
		// dado que o produto não aceita cadastro com descricao invalida
		// quando o usuario cadastrar um produto com descricao invalida - isblank = true isempty = true
		try {
			Produto produto1 = new Produto("", "maquina de lavar", 51.66, 12);
			fail("Deveria falhar descricao invalida");
		} catch (IllegalArgumentException e) {
			assertEquals("A descrição não deve estar em branco", e.getMessage());
		}
	}

	@Test
	void ct03_cadastrar_produto_com_descricao_invalida_null() {
		// dado que o produto não aceita cadastro com descricao invalida
		// quando o usuario cadastrar um produto com descricao invalida
		try {
			Produto produto1 = new Produto(null, "maquina de lavar", 51.66, 12);
			fail("Deveria falhar descricao invalida");
		} catch (IllegalArgumentException e) {
			assertEquals("A descrição não deve estar em branco", e.getMessage());
		}
	}

	@Test
	void ct04_cadastrar_produto_com_descricao_invalida_em_branco() {
		// dado que o produto não aceita cadastro com descricao invalida
		// quando o usuario cadastrar um produto com descricao invalida - um string com espacos em branco is.blank=true mas não eh vazio is.empty=false
		try {
			Produto produto1 = new Produto("   ", "maquina de lavar", 51.66, 12);
			fail("Deveria falhar descricao invalida");
		} catch (IllegalArgumentException e) {
			assertEquals("A descrição não deve estar em branco", e.getMessage());
		}
	}

	@Test
	void ct05_cadastrar_produto_com_descricao_invalida_branco() {
		// dado que o produto não aceita cadastro com descricao invalida
		// quando o usuario cadastrar um produto com descricao invalida
		// entao o sistema rejeita o cadastro 
		Produto produto = new Produto();
		assertThrows(IllegalArgumentException.class, () -> produto.setDescricao(""));
	}
}
