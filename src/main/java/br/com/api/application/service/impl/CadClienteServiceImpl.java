package br.com.api.application.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.api.application.dto.request.ClienteRequest;
import br.com.api.application.dto.request.EnderecoRequest;
import br.com.api.application.dto.request.TelefoneRequest;
import br.com.api.application.dto.response.ClienteResponse;
import br.com.api.application.excepetion.ApiException;
import br.com.api.application.service.CadClienteService;
import br.com.api.domain.entity.Cliente;
import br.com.api.domain.entity.Endereco;
import br.com.api.domain.entity.Telefone;
import br.com.api.domain.repository.ClienteRepository;
import br.com.api.domain.repository.EnderecoRepository;
import br.com.api.domain.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 *  Classe para operações do cliente
 * @author rodrigo
 *
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class CadClienteServiceImpl implements CadClienteService {
	 
	private static final String CLIENT_NOT_FOUND = "Client not search";
	private static final String ERROR_EDIT_CLIENT = "error edit client";
	private static final String CLIENT_NOT_FOUND_RESPONSE = "Cliente nao identificado pelo id informado ";
	private final ModelMapper mapper;
	private final ClienteRepository clienteRepository;
	private final EnderecoRepository enderecoRepository;
	private final TelefoneRepository telefoneRepository;

	@Override
	public ClienteResponse obterCliente(Long id) throws ApiException {
		
		try {
			 
			var cliente = clienteRepository.findByIdAndStatus(id,true).orElse(null);
			if(cliente != null) {
				return mapper.map(cliente, ClienteResponse.class);
			}else {
				log.info(CLIENT_NOT_FOUND_RESPONSE + id );
				throw ApiException.notFound(CLIENT_NOT_FOUND, CLIENT_NOT_FOUND_RESPONSE);
			}
		} catch (Exception e) {
			throw ApiException.internalError("Error get client", e.getMessage());
		}
		
	}

	@Override
	public String criarCliente(ClienteRequest cliente) throws ApiException {
		
		try {
			var clienteEntity = mapper.map(cliente, Cliente.class);
			clienteRepository.save(clienteEntity);
			return "Cliente criado com sucesso";
		} catch (Exception e) {
			throw ApiException.internalError("Error save client", e.getMessage());
		}
	}

	@Override
	public String editarCliente(Long id,ClienteRequest cliente) throws ApiException {
		try {
			if(clienteRepository.findByIdAndStatus(id,true).orElse(null) == null){
				log.info("Cliente nao identificado pelo cpf informado ->"  );
				throw ApiException.notFound(CLIENT_NOT_FOUND, "Cliente nao identificado pelo cpf informado");
			}
			var clienteEntity = mapper.map(cliente, Cliente.class);
			clienteEntity.setId(id);
			clienteRepository.save(clienteEntity);
			return "Cliente alterado com sucesso";
		} catch (Exception e) {
			throw ApiException.internalError(ERROR_EDIT_CLIENT, e.getMessage());
		}
	}

	@Override
	public String editarEndCliente(Long id, String tipo, EnderecoRequest endereco) throws ApiException {
		try {
			var cliente = clienteRepository.findByIdAndStatus(id,true).orElse(null); 
			if( cliente == null ){
				log.info(CLIENT_NOT_FOUND_RESPONSE +id  );
				throw ApiException.notFound(CLIENT_NOT_FOUND, CLIENT_NOT_FOUND_RESPONSE);
			}
			var endCliOld = enderecoRepository.findByClienteAndTipo(cliente, tipo);
			
			var clienteEndEntity = mapper.map(endereco, Endereco.class);
			
			if(endCliOld == null){// tipo end novo
				cliente.getEnderecos().removeIf(end -> end.getTipo().equals(clienteEndEntity.getTipo()));
				cliente.getEnderecos().add(clienteEndEntity);
				
			}else {//atualiza apenas end
				enderecoRepository.delete(endCliOld);
				clienteEndEntity.setId(endCliOld.getId());
				clienteEndEntity.setCliente(cliente);
				enderecoRepository.save(clienteEndEntity);
				cliente.getEnderecos().removeIf(end -> end.getTipo().equals(endCliOld.getTipo()));
				cliente.getEnderecos().add(clienteEndEntity);
			}
			clienteRepository.save(cliente);			
			
			return "Alterado endereco com sucesso";
		} catch (Exception e) {
			throw ApiException.internalError(ERROR_EDIT_CLIENT, e.getMessage());
		}
	}
	
	@Override
	public String editarTelCliente(Long id, String tipo, TelefoneRequest telefone) throws ApiException {
		try {
			var cliente = clienteRepository.findByIdAndStatus(id,true).orElse(null); 
			if( cliente == null ){
				log.info("Cliente nao identificado pelo id informado ->" +id  );
				throw ApiException.notFound(CLIENT_NOT_FOUND, CLIENT_NOT_FOUND_RESPONSE);
			}
			var telCliOld = telefoneRepository.findByClienteAndTipo(cliente, tipo);
			
			var clienteTelEntity = mapper.map(telefone, Telefone.class);
			
			if(telCliOld == null){// tipo tel novo
				cliente.getTelefones().removeIf(end -> end.getTipo().equals(clienteTelEntity.getTipo()));
				cliente.getTelefones().add(clienteTelEntity);
				
			}else {//atualiza apenas end
				telefoneRepository.delete(telCliOld);
				clienteTelEntity.setId(telCliOld.getId());
				clienteTelEntity.setCliente(cliente);
				telefoneRepository.save(clienteTelEntity);
				cliente.getTelefones().removeIf(end -> end.getTipo().equals(telCliOld.getTipo()));
				cliente.getTelefones().add(clienteTelEntity);
			}
			clienteRepository.save(cliente);			
			
			return "Alterado telefone com sucesso";
		} catch (Exception e) {
			throw ApiException.internalError(ERROR_EDIT_CLIENT, e.getMessage());
		}
	}
	
	@Override
	public String inativarCliente(Long id) throws ApiException {
		try {
			var cliente = clienteRepository.findByIdAndStatus(id, true).orElse(null); 
			if( cliente == null ){
				log.info("Cliente nao identificado pelo id informado ->" + id );
				throw ApiException.notFound(CLIENT_NOT_FOUND, CLIENT_NOT_FOUND_RESPONSE);
			}
			cliente.setStatus(false);
			clienteRepository.save(cliente );
			return "Cliente inativado";
		} catch (Exception e) {
			log.error(ERROR_EDIT_CLIENT, e);
			throw ApiException.internalError(ERROR_EDIT_CLIENT, e.getMessage());
		}
	}
	
	@Override
	public String ativarCliente(Long id) throws ApiException {
		try {
			var cliente = clienteRepository.findByIdAndStatus(id, false).orElse(null); 
			if( cliente == null ){
				log.info("Cliente inativo nao identificado pelo id informado ->" + id );
				throw ApiException.notFound(CLIENT_NOT_FOUND, CLIENT_NOT_FOUND_RESPONSE);
			}
			cliente.setStatus(true);
			clienteRepository.save(cliente );
			return "Cliente inativado";
		} catch (Exception e) {
			log.error(ERROR_EDIT_CLIENT, e);
			throw ApiException.internalError(ERROR_EDIT_CLIENT, e.getMessage());
		}
	}
	
	@Override
	public Page<ClienteResponse> getAllClientes(int page, int size) throws ApiException {
		try {
			
			Pageable paging = PageRequest.of(page, size);
			
			var clientes = clienteRepository.findAllByStatus(true, paging);
			if(clientes.isEmpty() ) {
				throw ApiException.notFound(CLIENT_NOT_FOUND, "Não existem clientes cadastrados");
			}
 
			return  mapEntityPageIntoDtoPage(clientes, ClienteResponse.class);
			
		} catch (Exception e) {
			log.error("error get all client", e);
			throw ApiException.internalError("Error get all client", e.getMessage());
		}
	}

	@Override
	public Page<ClienteResponse> getAllClientesFilter(ClienteRequest cliente, int page, int size) throws ApiException {

		Pageable paging = PageRequest.of(page, size);
		
		ExampleMatcher matcher = ExampleMatcher
			    .matchingAll()
			    .withIgnorePaths("createdDate")
			    .withIgnorePaths("enderecos")
			    .withIgnorePaths("telefones")
			    .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		var clienteEntity = mapper.map(cliente, Cliente.class);

		var clientes = clienteRepository.findAll( Example.of(clienteEntity, matcher), paging);
		
		return  mapEntityPageIntoDtoPage(clientes, ClienteResponse.class);
	}
	
	private <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
	    return entities.map(objectEntity -> mapper.map(objectEntity, dtoClass));
	}

}
