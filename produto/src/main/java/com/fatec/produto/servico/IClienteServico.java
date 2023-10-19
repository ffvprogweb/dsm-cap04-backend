package com.fatec.produto.servico;

import java.util.Optional;

import com.fatec.produto.model.Cliente;

import jakarta.validation.Valid;

public interface IClienteServico {
	public Optional<Cliente> cadastrar(@Valid Cliente cliente);
}
