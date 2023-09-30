package com.fatec.produto.persistenciadd;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.fatec.produto.model.Produto;

class Req01CadastrarProdutoTest {
	
	@ParameterizedTest
	@CsvSource({
		"Eletrobomba 110V para Maquina de Lavar e Lava Louças, maquina de lavar, 51.66, 12, Sucesso"
	})
	

	void leDados(String descricao, String categoria, double custo, int quant, String re) {
		try {
			Produto produto1 = new Produto(descricao, categoria,custo,quant);
		}catch(Exception e){
			
		}
	}

}
