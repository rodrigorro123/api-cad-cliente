package br.com.api.application.dto.request;

import java.io.Serializable;

import lombok.Data;

/**
 * Classe de mapeamento request do endereco
 * @author rodrigo
 *
 */ 

@Data
public class EnderecoRequest implements Serializable {
 
	private static final long serialVersionUID = 1884395550354356546L;

	 private String rua;
	 private String bairro;
	 private String cidade;
	 private String estado;
	 private String pais;
	 private String cep;
	 private Long numero;
	 private String tipo;
	
}
