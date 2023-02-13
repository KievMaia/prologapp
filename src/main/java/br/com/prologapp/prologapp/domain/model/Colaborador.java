package br.com.prologapp.prologapp.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Colaborador {

	@EqualsAndHashCode.Include
	@Id
	private String cpf;
	
	private String nome;
}
