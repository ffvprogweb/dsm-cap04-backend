package com.fatec.produto.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

	@Test
	void ct01_consultar_catalogo_com_sucesso_por_cod_produto() {

		// dado que o produto e a imagem estao cadastrados
		Produto produto1 = new Produto("Eletrobomba 110V para Maquina de Lavar e Lava Louças", "maquina de lavar",
				51.66, 12);
		Produto p = produtoRepository.save(produto1);
		assertTrue(p.getId() > 0);
		assertTrue(produtoRepository.count() > 0);
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
		imagem.setId(p.getId()); // associa o id do produto ao id da imagem
		imagem.setNome("produto1.jpg");
		imagem.setCaminho("imagens/" + imagem.getNome());
		imagem.setArquivo(arquivo1);
		imagemRepository.save(imagem);
		// quando o usuario consulta o produto pelo codigo
		Catalogo c = produtoServico.consultarPorId(p.getId().toString()).get();
		// entao retorna os detalhes do produto incluindo a imagem
		assertNotNull(c);
		assertEquals("Eletrobomba 110V para Maquina de Lavar e Lava Louças", c.getDescricao());
	}

}
