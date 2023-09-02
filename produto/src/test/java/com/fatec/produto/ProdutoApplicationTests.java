package com.fatec.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.produto.model.Produto;
import com.fatec.produto.servico.IProdutoServico;

@SpringBootTest
class ProdutoApplicationTests {
	@Autowired
	IProdutoServico produtoServico;

	@Test
	void contextLoads() {
	}

	@Test
	void ct01_cadastra_produto_com_sucesso_custo() {
		Produto produto = new Produto();
		produto.setCusto(12);
		assertEquals(12, produto.getCusto());

	}
	@Test 
	void ct02_consulta_com_sucesso() {
		List<Produto> produtos = produtoServico.consultaCatalogo();
		assertEquals(1,produtos.size());
		
	}

}
