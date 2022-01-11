package br.com.api.application.dto.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.api.application.dto.request.TelefoneRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe de mapeamento response para o cliente
 * 
 * @author rodrigo
 *
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ClienteResponse implements Serializable {

	private static final long serialVersionUID = -2348089903917261471L;

	private Long id;
	private String nome;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private String sexo;
	private String cpf;
	private Boolean status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime createdDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime updatedDate;
	private List<EnderecoResponse> enderecos = new ArrayList<>();
	private  List<TelefoneRequest> telefones ;
	
	public Integer getIdade() {
		return Period.between(dataNascimento, LocalDate.now()).getYears();
	}
	

}
