package br.com.prologapp.prologapp.domain.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
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
@Table(name = "MARCACAO")
public class Marcacao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO")
	private Long codigo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_TIPO_MARCACAO", nullable = false)
	private MarcacaoTipo marcacaoTipo;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPF_COLABORADOR", referencedColumnName = "CPF")
	private Colaborador colaborador;

	@Column(name = "DATA_HORA_MARCACAO", nullable = false)
	private ZonedDateTime dataHoraMarcacao;

	@Column(name = "TIPO_MARCACAO", nullable = false)
	private String tipoMarcacao;
}
