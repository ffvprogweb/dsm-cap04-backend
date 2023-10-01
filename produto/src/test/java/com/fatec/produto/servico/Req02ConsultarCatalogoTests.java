package com.fatec.produto.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.produto.model.Catalogo;
import com.fatec.produto.model.IImagemRepository;
import com.fatec.produto.model.IProdutoRepository;
import com.fatec.produto.model.Imagem;
import com.fatec.produto.model.Produto;

@SpringBootTest
class Req02ConsultarCatalogoTests {
	@Autowired
	ProdutoServico produtoServico;
	@Autowired
	IImagemRepository imagemRepository;
	@Autowired
	IProdutoRepository produtoRepository;

	void setup() {
		// dado que o produto e a imagem estao cadastrados
		Produto produto1 = new Produto("Eletrobomba 110V para Maquina de Lavar e Lava Louças", "maquina de lavar",
				51.66, 12);
		Produto p = produtoRepository.save(produto1);
		assertEquals(1, p.getId());
		assertEquals(1, produtoRepository.count());
		// ****************************************************************
		// upload - obtem a imagem do c, atribui ao obj imagem e salva no db do servidor
		// ****************************************************************
		Path path = Paths.get("c:/temp/produto1.jpg");
		byte[] arquivo1 = null;
		try {
			InputStream file = Files.newInputStream(path);
			arquivo1 = file.readAllBytes();
		} catch (Exception e) {
			System.out.println("Erro no upload de imagem");
		}
		Imagem imagem = new Imagem();
		imagem.setId(1L); // associa o id do produto ao id da imagem
		imagem.setNome("produto1.jpg");
		imagem.setCaminho("imagens/" + imagem.getNome());
		imagem.setArquivo(arquivo1);
		Imagem i = imagemRepository.save(imagem);
		assertEquals(1, i.getId());
	}

	@Test
	void ct01_consultar_catalogo_com_sucesso_por_cod_produto() {
		//dado que o usuario entrou com as informações do produto a ser cadastrado
		setup();
		// quando o usuario consulta o produto pelo codigo
		Catalogo c = produtoServico.consultarPorId("1").get();
		// entao retorna os detalhes do produto incluindo a imagem
		assertNotNull(c);
		assertEquals("Eletrobomba 110V para Maquina de Lavar e Lava Louças", c.getDescricao());
	}

}
