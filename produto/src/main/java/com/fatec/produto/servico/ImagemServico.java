package com.fatec.produto.servico;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fatec.produto.model.IImagemRepository;
import com.fatec.produto.model.IProdutoRepository;
import com.fatec.produto.model.Imagem;
import com.fatec.produto.model.Produto;
@Service
public class ImagemServico implements IImagemServico {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private IImagemRepository imagemRepository;
	@Autowired
	private IProdutoRepository produtoRepository;

	@Override
	public Optional<Imagem> salvar(MultipartFile arquivo, Long id) throws IOException {
		// **********************************************************
		// Obter informações sobre o arquivo - espera um tipo formdata chave/file
		// **********************************************************
		logger.info(">>>>>> servico salvar imagem - iniciado...");
		Optional<Produto> p = produtoRepository.findById(id);
		if (p.isPresent()) {
			logger.info(">>>>>> servico salvar imagem - produto encontrado");
			String nome = arquivo.getOriginalFilename();
			Path caminhoArquivo = Paths.get("imagens/" + nome);
			logger.info(">>>>>> servico salvar imagem - caminho arquivo => " + caminhoArquivo);
			Imagem imagem = new Imagem();
			imagem.setId(id); // associa o id do produto ao id da imagem
			imagem.setNome(arquivo.getOriginalFilename());
			imagem.setCaminho(caminhoArquivo.toString());
			imagem.setArquivo(arquivo.getBytes());
			logger.info(">>>>>> servico salvar imagem - arquivo getSize => " + arquivo.getSize());
			// **********************************************************
			// salva no disco e no db
			// ***********************************************************
			// Files.write(caminhoArquivo, arquivo.getBytes());
			return Optional.of(imagemRepository.save(imagem));
		} else {
			logger.info(">>>>>> servico salvar imagem - id nao encontrado");
			return Optional.empty();
		}
	}

	@Override
	public List<Imagem> getAll() {
		return imagemRepository.findAll();
	}

	@Override
	public byte[] getImagem(String nomeArquivo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getImagemById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
