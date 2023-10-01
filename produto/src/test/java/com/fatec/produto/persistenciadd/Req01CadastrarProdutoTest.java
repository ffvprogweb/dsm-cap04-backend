package com.fatec.produto.persistenciadd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.produto.model.IProdutoRepository;
import com.fatec.produto.model.Produto;
@SpringBootTest
class Req01CadastrarProdutoTest {
	@Autowired
	IProdutoRepository repository;
	@ParameterizedTest
	@CsvSource({
		"Eletrobomba 110V para Maquina de Lavar e Lava Louças, maquina de lavar, 51.66, 12, Sucesso",
		"' ', maquina de lavar, 51.66, 12, A descrição não deve estar em branco",
		", maquina de lavar, 51.66, 12, A descrição não deve estar em branco",
		"Eletrobomba 110V para Maquina de Lavar e Lava Louças, , 51.66, 12, A categoria não deve estar em branco"
	})
	

	void leDados(String descricao, String categoria, double custo, int quant, String re) {
		try {
			Produto produto1 = new Produto(descricao, categoria,custo,quant);
			Produto p = repository.save(produto1);
			assertTrue(produto1.equals(p));
		}catch(Exception e){
			assertEquals(re,e.getMessage());
		}
	}

}
