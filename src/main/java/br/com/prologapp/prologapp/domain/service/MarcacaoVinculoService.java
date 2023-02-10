package br.com.prologapp.prologapp.domain.service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prologapp.prologapp.api.model.dto.TotalPeriodoDTO;
import br.com.prologapp.prologapp.api.model.vo.MarcacaoVinculoInicioFimVO;
import br.com.prologapp.prologapp.domain.repository.MarcacaoVinculoInicioFimRepository;
import br.com.prologapp.prologapp.utils.DateUtils;

@Service
public class MarcacaoVinculoService {

	@Autowired
	private MarcacaoVinculoInicioFimRepository marcacaoVinculoInicioFimRepository;

	public TotalPeriodoDTO listaTotalPeriodo(String cpf, String dataInicial, String dataFinal) {
		List<MarcacaoVinculoInicioFimVO> listaMarcacoes = marcacaoVinculoInicioFimRepository.findAllGroupedByDia(cpf, dataInicial, dataFinal);

		return this.calculoTotalPeriodo(listaMarcacoes);
	}

	private TotalPeriodoDTO calculoTotalPeriodo(List<MarcacaoVinculoInicioFimVO> listaMarcacoes) {
		Map<String, Duration> intervalos = new HashMap<>();
		TotalPeriodoDTO totalPeriodoDTO = new TotalPeriodoDTO();
		
		listaMarcacoes.stream().forEach(marcacao -> {
		    Duration interval = Duration.between(
		    		marcacao.getDataHoraMarcacaoInicio(), 
		    		marcacao.getDataHoraMarcacaoFim());
		    
		    intervalos.merge(marcacao.getNome(), interval, Duration::plus);
		});
		
		intervalos.entrySet().stream().forEach(entry -> totalPeriodoDTO.getTotalPeriodo().put(entry.getKey(), DateUtils.formatDuration(entry.getValue())));
		totalPeriodoDTO.getMarcacaoListTotalPeriodo().addAll(listaMarcacoes);
		
		return totalPeriodoDTO;
	}

	
}
