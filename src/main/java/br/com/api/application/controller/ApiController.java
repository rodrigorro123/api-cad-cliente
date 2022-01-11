package br.com.api.application.controller;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.application.dto.request.ClienteRequest;
import br.com.api.application.dto.request.EnderecoRequest;
import br.com.api.application.dto.request.TelefoneRequest;
import br.com.api.application.dto.response.ClienteResponse;
import br.com.api.application.excepetion.ApiException;
import br.com.api.application.service.CadClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;


/**
 * Classe de controller da api de cadastro cliente
 * @author rodrigo
 *
 */
@RestController
@Api(value = "Java dev backend")
@CrossOrigin
@RequestMapping("/cad-cliente")
@RequiredArgsConstructor
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Registro processado com sucesso"),
		@ApiResponse(code = 201, message = "Registro criado com sucesso"),
		@ApiResponse(code = 202, message = "Registro alterado com sucesso"),
		@ApiResponse(code = 400, message = "Requisiçao invalida"),
		@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado"),
		@ApiResponse(code = 500, message = "Erro interno")
	     })
public class ApiController {

	private final CadClienteService cadClienteService;
 
	@ApiOperation(value = "metodo para retornar cliente solicitado", response = ClienteResponse.class)
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<ClienteResponse>  getCliente (@PathVariable("id") Long id) throws ApiException {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(cadClienteService.obterCliente(id));
	}
	
	@ApiOperation(value = "metodo para retornar todos clientes", response = ClienteResponse.class)
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Page<ClienteResponse>>  getAllCliente (
		       @RequestParam(defaultValue = "0") int page,
		       @RequestParam(defaultValue = "10") int size) throws ApiException {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(cadClienteService.getAllClientes(page, size));
	}
 
	@PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@ApiOperation(value = "metodo para criar cliente")
	public ResponseEntity<String> createCliente( @Valid
			@RequestBody ClienteRequest cliente) throws ApiException {
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(cadClienteService.criarCliente(cliente));
	}
	
	@PatchMapping(path = "/{id}/edit",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@ApiOperation(value = "metodo para editar cliente")
	public ResponseEntity<String> editCliente(
			@PathVariable("id") Long id, 
			@Valid @RequestBody ClienteRequest cliente) throws ApiException {
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(cadClienteService.editarCliente(id, cliente));
	}
	
	@GetMapping(path = "/filter",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@ApiOperation(value = "metodo para filtar campos do cliente")
	public ResponseEntity<Page<ClienteResponse>> filterCliente(
		       @RequestParam(defaultValue = "0") int page,
		       @RequestParam(defaultValue = "10") int size,
		       @RequestBody ClienteRequest cliente) throws ApiException {
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(cadClienteService.getAllClientesFilter(cliente, page, size));
	}
	
	@PatchMapping(path = "/{id}/edit/{tipo}/endereco",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@ApiOperation(value = "metodo para editar endereco cliente")
	public ResponseEntity<String> editEndCliente(
			@PathVariable("id") Long id,
			@PathVariable("tipo") String tipo,
			@RequestBody EnderecoRequest endereco) throws ApiException {
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(cadClienteService.editarEndCliente(id, tipo, endereco));
	}

	@PatchMapping(path = "/{id}/edit/{tipo}/telefone",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@ApiOperation(value = "metodo para editar telefone cliente")
	public ResponseEntity<String> editTelCliente(
			@PathVariable("id") Long id,
			@PathVariable("tipo") String tipo,
			@RequestBody TelefoneRequest telefone) throws ApiException {
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(cadClienteService.editarTelCliente(id, tipo, telefone));
	}
	
	@ApiOperation(value = "metodo para inativar cliente solicitado", response = ClienteResponse.class)
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<String>  deleteCliente (@PathVariable("id") Long id) throws ApiException {
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(cadClienteService.inativarCliente(id));
	}
	
	@ApiOperation(value = "metodo para reativar cliente inativo", response = ClienteResponse.class)
	@DeleteMapping(path = "/{id}/reativar", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<String>  ativarCliente (@PathVariable("id") Long id) throws ApiException {
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(cadClienteService.ativarCliente(id));
	}
}
