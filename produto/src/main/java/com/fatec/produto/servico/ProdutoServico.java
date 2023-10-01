package com.fatec.produto.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.produto.model.Catalogo;
import com.fatec.produto.model.IProdutoRepository;
import com.fatec.produto.model.Imagem;
import com.fatec.produto.model.Produto;

@Service
public class ProdutoServico implements IProdutoServico {
	@Autowired
	IProdutoRepository repository;
	@Autowired
	ImagemServico imagemServico;
	Logger logger = LogManager.getLogger(this.getClass());
	@Override
	public List<Catalogo> consultaCatalogo() {
		Catalogo c = null;
		List<Catalogo> lista = new ArrayList<>();
		List<Produto> listaP = repository.findAll();
		List<Imagem> listaI = imagemServico.getAll();
		for (Produto p : listaP) {
			for (Imagem i : listaI) {
				if (p.getId().equals(i.getId())) {
					c = new Catalogo(p.getId(), p.getDescricao(), p.getCategoria(), p.getCusto(),
							p.getQuantidadeNoEstoque(), i.getArquivo());
					lista.add(c);
				}
			}
		}
		return lista;
	}

	@Override
	public List<Catalogo> consultaPorDescricao(String descricao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Produto> cadastrar(Produto produto) {
		logger.info(">>>>>> servico cadastrar produto iniciado ");
		return Optional.ofNullable(repository.save(produto));

	}

	@Override
	public Optional<Catalogo> consultarPorId(String id) {
		logger.info(">>>>>> servico consulta por id chamado");
		Long codProduto = Long.parseLong(id);
		
		Catalogo c = null;
		List<Catalogo> listaCatalogo = new ArrayList<>();
		List<Produto> listaP = repository.findAll();
		List<Imagem> listaI = imagemServico.getAll();
		for (Produto p : listaP) {
			for (Imagem i : listaI) {
				if (p.getId().equals(i.getId())) {
					c = new Catalogo(p.getId(), p.getDescricao(), p.getCategoria(), p.getCusto(),
							p.getQuantidadeNoEstoque(), i.getArquivo());
					listaCatalogo.add(c);
				}
			}
		}
		logger.info(">>>>>> lista de catalogo contem =>" + listaCatalogo.size());
		//int cod = Long.valueOf(codProduto).intValue();
		//logger.info(">>>>>> codigo convertido para inteiro =>" + cod);
		Optional<Catalogo> produtoDoCatalogo = Optional.empty();
		for(Catalogo catalogo : listaCatalogo) {
			
			if ((catalogo.getId().longValue() == codProduto.longValue())) {
				 produtoDoCatalogo = Optional.ofNullable(catalogo);
			}
		}
		
		return produtoDoCatalogo;

	}

	@Override
	public Optional<Produto> atualizar(Long id, Produto produto) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

}
