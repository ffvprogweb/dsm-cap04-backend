package com.fatec.produto.servico;

import java.util.List;
import java.util.Optional;

import com.fatec.produto.model.Catalogo;
import com.fatec.produto.model.Produto;

public interface IProdutoServico {
	public List<Catalogo> consultaCatalogo();
	public List<Catalogo> consultaPorDescricao(String descricao);
	public Optional <Produto> cadastrar(Produto produto);
	public Optional <Catalogo> consultarPorId(String id);
	public Optional <Produto> atualizar(Long id, Produto produto);
	public void excluir(Long id);

}
