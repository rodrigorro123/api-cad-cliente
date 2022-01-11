package br.com.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.domain.entity.Cliente;
import br.com.api.domain.entity.Telefone;

/**
 * interface de acesso aos dados do Endereco do cliente
 * @author rodrigo
 *
 */
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
	
	Telefone findByClienteAndTipo(Cliente cliente, String tipo);
	
}
