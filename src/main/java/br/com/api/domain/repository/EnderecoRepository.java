package br.com.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.domain.entity.Cliente;
import br.com.api.domain.entity.Endereco;

/**
 * interface de acesso aos dados do Endereco do cliente
 * @author rodrigo
 *
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	Endereco findByClienteAndTipo(Cliente cliente, String tipo);
	
}
