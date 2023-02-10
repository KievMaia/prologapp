package br.com.prologapp.prologapp.api.model.dto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.prologapp.prologapp.api.model.vo.MarcacaoVinculoInicioFimVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalPeriodoDTO {

	private List<MarcacaoVinculoInicioFimVO> marcacaoVinculoInicioFimList = new ArrayList<>();
	
	private Map<String, String> totalPeriodo = new HashMap<>();
}
