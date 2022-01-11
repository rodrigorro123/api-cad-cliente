package br.com.api.application.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Classe de mapeamento no response
 * @author rodrigo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class TelefoneResponse implements Serializable {
 
	private static final long serialVersionUID = 1884395250354356546L;
	
	 private String ddd;
	 private String numero;
	 private String tipo;
}
