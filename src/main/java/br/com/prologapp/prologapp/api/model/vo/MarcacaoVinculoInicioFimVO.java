package br.com.prologapp.prologapp.api.model.vo;

import java.time.ZonedDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarcacaoVinculoInicioFimVO {

	private Long codigo;
	private String nome;
	private Date dia;
	private ZonedDateTime dataHoraMarcacaoInicio;
	private ZonedDateTime dataHoraMarcacaoFim;

}
