package br.com.prologapp.prologapp.api.model.vo;

import java.time.OffsetDateTime;

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
	private String colaborador;
	private OffsetDateTime dia;
	private OffsetDateTime dataHoraMarcacaoInicio;
	private OffsetDateTime dataHoraMarcacaoFim;

}
