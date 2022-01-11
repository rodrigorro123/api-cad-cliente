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
 * Classe de mapeamento no banco de telefone
 * @author rodrigo
 *
 */
@Data
@Entity
@Builder
@Table(name = Telefone.TABLE_NAME)
public class Telefone implements Serializable {
 
	private static final long serialVersionUID = 1884395250354356546L;
	public static final String TABLE_NAME = "tbl_telefone";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	 private Long id;

	@Column(name = "ddd")
	 private String ddd;
	
	@Column(name = "numero")
	 private String numero;

	@Column(name = "tipo")
	 private String tipo;
	 
    @ManyToOne
    private Cliente cliente;
	
}
