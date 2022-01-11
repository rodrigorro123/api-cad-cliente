package br.com.api.application.service;

import org.springframework.data.domain.Page;

import br.com.api.application.dto.request.ClienteRequest;
import br.com.api.application.dto.request.EnderecoRequest;
import br.com.api.application.dto.request.TelefoneRequest;
import br.com.api.application.dto.response.ClienteResponse;
import br.com.api.application.excepetion.ApiException;


public interface CadClienteService {

	/**
	 * retorna dados do cliente por id
	 * @param id
	 * @return
	 * @throws ApiException
	 */
	ClienteResponse obterCliente (Long id) throws  ApiException;
	
	/**
	 * criar cliente
	 * @param clienteRequest
	 * @return
	 * @throws ApiException
	 */
	String criarCliente (ClienteRequest cliente) throws  ApiException;
	
	/**
	 * editar cliente
	 * @param cliente
	 * @return
	 * @throws ApiException
	 */
	String editarCliente (Long id, ClienteRequest cliente) throws  ApiException;
	
	/**
	 * editar endereco cliente
	 * @param id
	 * @param endereco
	 * @return
	 * @throws ApiException
	 */
	String editarEndCliente (Long id, String tipo, EnderecoRequest endereco) throws  ApiException;
	
	/**
	 * inativar cliente
	 * @param id
	 * @return
	 * @throws ApiException
	 */
	String inativarCliente(Long id) throws  ApiException;
	
	String ativarCliente(Long id) throws  ApiException;
	
	/**
	 * retorna todos clientes cadastrados
	 * @param page
	 * @param size
	 * @return
	 * @throws ApiException
	 */
	Page<ClienteResponse> getAllClientes(int page, int size) throws ApiException; 
	
	/**
	 * editar telefone cliente
	 * @param id
	 * @param tipo
	 * @param telefone
	 * @return
	 * @throws ApiException
	 */
	public String editarTelCliente(Long id, String tipo, TelefoneRequest telefone) throws ApiException;
	
	public Page<ClienteResponse> getAllClientesFilter(ClienteRequest cliente, int page, int size) throws ApiException;
}
