package com.fatec.produto.servico;

import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.client.RestTemplate;

import com.fatec.produto.model.Cliente;
import com.fatec.produto.model.Endereco;
import com.fatec.produto.model.IClienteRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Service
public class ClienteServico implements IClienteServico {
	@Autowired
	IClienteRepository repository;
	Logger logger = LogManager.getLogger(this.getClass());

	public Optional<Cliente> cadastrar(@Valid Cliente cliente) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
		Optional<Cliente> novoCliente = Optional.empty();
		if (!violations.isEmpty()) {
			logger.info(">>>>>> servico cadastrar cliente violacao de regra de negocio ");
			throw new ConstraintViolationException(violations);
		} else {
			try {
				logger.info(">>>>>> servico cadastrar cliente iniciado ");
				String endereco = obtemEndereco(cliente.getCep());
				if (endereco == null)
					throw new IllegalArgumentException ("CEP não cadastrado.");
				else {
					cliente.setEndereco(endereco);
					novoCliente = Optional.of(repository.save(cliente));
					return novoCliente;
				}
			} catch (TransactionSystemException e) {
				logger.info(">>>>>> servico cadastrar cliente exception nao esperada");
				throw new IllegalArgumentException ("Dados inválidos.");
			}
		}

	}

	public String obtemEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		Optional<Endereco> endereco = Optional.ofNullable(template.getForObject(url, Endereco.class, cep));
		logger.info(">>>>>>> 3. obtem endereco ==> " + endereco.toString());
		return endereco.get().getLogradouro();
	}

}

