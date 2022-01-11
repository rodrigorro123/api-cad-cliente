package br.com.api.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.api.domain.entity.Cliente;

/**
 * interface de acesso aos dados do cliente
 * @author rodrigo
 *
 */
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
	
	Optional<Cliente> findByIdAndStatus(Long id, Boolean status);
	
	Page<Cliente> findAllByStatus(Boolean status, Pageable pageable);

	Page<Cliente> findAll(Example<Cliente> of, Pageable pageable); 
	
}
