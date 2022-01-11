package br.com.api.application.dto.request;

import java.io.Serializable;

import lombok.Data;

/**
 * Classe de mapeamento no request
 * @author rodrigo
 *
 */
@Data
public class TelefoneRequest implements Serializable {
 
	private static final long serialVersionUID = 1884395250354356546L;
	
	 private String ddd;
	 private String numero;
	 private String tipo;
}
