package br.com.prologapp.prologapp.api.model.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.prologapp.prologapp.api.model.vo.MarcacaoVinculoInicioFimVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalPeriodoDTO {

	private Map<OffsetDateTime, List<MarcacaoVinculoInicioFimVO>> marcacaoListTotalPeriodo = new TreeMap<>();
	
	private Map<String, String> totalPeriodo = new TreeMap<>();
	
	private Map<String, String> horasNoturnasCLT = new TreeMap<>();
}
