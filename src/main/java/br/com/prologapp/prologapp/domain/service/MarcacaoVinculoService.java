package br.com.prologapp.prologapp.domain.service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prologapp.prologapp.api.model.vo.MarcacaoVinculoInicioFimVO;
import br.com.prologapp.prologapp.domain.repository.MarcacaoVinculoInicioFimRepository;
import br.com.prologapp.prologapp.utils.DateUtils;

@Service
public class MarcacaoVinculoService {

	@Autowired
	private MarcacaoVinculoInicioFimRepository marcacaoVinculoInicioFimRepository;

	public List<ZonedDateTime> calculoIntervaloMarcacao(String cpf) {

		List<MarcacaoVinculoInicioFimVO> listaMarcacoes = marcacaoVinculoInicioFimRepository.findAllGroupedByDia(cpf);
		Map<String, Duration> intervalos = new HashMap<>();

		for (MarcacaoVinculoInicioFimVO marcacao : listaMarcacoes) {
			Duration interval = Duration.between(marcacao.getDataHoraMarcacaoInicio(),
					marcacao.getDataHoraMarcacaoFim());
			intervalos.merge(marcacao.getNome(), interval, Duration::plus);
		}
		
		for (Map.Entry<String, Duration> entry : intervalos.entrySet()) {
			System.out.println(entry.getKey() + ": " + DateUtils.formatDuration(entry.getValue()));
		}

		return null;
	}

	
}
