package br.com.api.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

/**
 * Classe de mapeamento no banco do item do endereco
 * @author rodrigo
 *
 */
@Data
@Entity
@Builder
@Table(name = Endereco.TABLE_NAME)
public class Endereco implements Serializable {
 
	private static final long serialVersionUID = 1884395550354356546L;
	public static final String TABLE_NAME = "tbl_endereco";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	 private Long id;

	@Column(name = "rua")
	 private String rua;
	
	@Column(name = "bairro")
	 private String bairro;
	
	@Column(name = "cidade")
	 private String cidade;
	
	@Column(name = "estado")
	 private String estado;
	
	@Column(name = "pais")
	 private String pais;
	
	@Column(name = "cep")
	 private String cep;
	
	@Column(name = "numero")
	 private Long numero;

	@Column(name = "tipo")
	 private String tipo;
	
    @ManyToOne
    private Cliente cliente;
	
}
