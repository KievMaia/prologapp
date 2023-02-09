package br.com.prologapp.prologapp.api.model.vo;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarcacaoVinculoInicioFimAgrupadoPorDiaVO {

	private Long codigo;

	private String nome;

	private ZonedDateTime dia;

	private ZonedDateTime dataHoraMarcacaoInicio;

	private ZonedDateTime dataHoraMarcacaoFim;

}
