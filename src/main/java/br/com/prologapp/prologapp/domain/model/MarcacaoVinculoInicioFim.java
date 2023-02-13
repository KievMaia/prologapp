package br.com.prologapp.prologapp.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "MARCACAO_VINCULO_INICIO_FIM")
public class MarcacaoVinculoInicioFim {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_MARCACAO_INICIO", unique = true, nullable = false)
	private Marcacao marcacaoInicio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_MARCACAO_FIM", unique = true, nullable = false)
	private Marcacao marcacaoFim;
}
