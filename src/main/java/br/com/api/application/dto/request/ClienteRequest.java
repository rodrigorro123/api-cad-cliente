package br.com.api.application.dto.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Classe de mapeamento request para o cliente
 * 
 * @author rodrigo
 *
 */
@Validated
@Data
public class ClienteRequest implements Serializable {

	private static final long serialVersionUID = -2348089903917261471L;

	@NotBlank
	private String nome;
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	@NotNull
	private String sexo;
	@CPF
	@NotBlank
	@Pattern(regexp = "\\d+")
	private String cpf;
	private List<EnderecoRequest> enderecos = new ArrayList<>();
	private  List<TelefoneRequest> telefones ;
}
