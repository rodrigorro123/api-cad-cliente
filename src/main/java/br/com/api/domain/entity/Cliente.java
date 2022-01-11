package br.com.api.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Classe de mapeamento do banco para o cliente
 * @author rodrigo
 *
 */

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = Cliente.TABLE_NAME)
public class Cliente implements Serializable  {
 
	private static final long serialVersionUID = -2348089903917991471L;

	public static final String TABLE_NAME = "tbl_cliente";

	public Cliente() {
		super();
		status  = true;
		createdDate  = LocalDateTime.now();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	 private Long id;

	@NotBlank
	@Column(name = "cpf")
	 private String cpf;
	
	@NotBlank
	@Column(name = "nome")
	 private String nome;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento")
	 private LocalDate dataNascimento;
		
	@Column(name = "sexo")
	 private String sexo;
	
	@Column(name = "status")
	 private Boolean status;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "updateDate")
	private LocalDateTime updatedDate;
	
	@PreUpdate
	private void preUpdate() {
		updatedDate  = LocalDateTime.now();
	}
   
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER )
    @JoinColumn(name = "cliente_id")
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    private List<Endereco> enderecos;
    
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER )
    @JoinColumn(name = "cliente_id")
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    private List<Telefone> telefones;
    
}
