package br.com.prologapp.prologapp.domain.service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	public TotalPeriodoDTO listaTotalPeriodo(String cpf, OffsetDateTime dataInicial, OffsetDateTime dataFinal) {
		TotalPeriodoDTO totalPeriodoDTO = new TotalPeriodoDTO();
		
		List<MarcacaoVinculoInicioFimVO> listaMarcacoes = marcacaoVinculoInicioFimRepository.findAllGroupedByDia(cpf,
				dataInicial, dataFinal);

		Map<OffsetDateTime, List<MarcacaoVinculoInicioFimVO>> mapResultados = listaMarcacoes.stream()
		        .collect(Collectors.groupingBy(marcacao -> marcacao.getDia()));

		this.calculoTotalPeriodo(mapResultados, totalPeriodoDTO.getTotalPeriodo());
		
		this.calculoHorasNoturnasCLT(mapResultados, totalPeriodoDTO.getHorasNoturnasCLT());
		
		totalPeriodoDTO.getMarcacaoListTotalPeriodo().putAll(mapResultados);
		
		return totalPeriodoDTO;
	}

	private void calculoTotalPeriodo(Map<OffsetDateTime, List<MarcacaoVinculoInicioFimVO>> mapResultados, Map<String, String> mapTotalPeriodo) {
		Map<String, Duration> intervalos = new HashMap<>();

		mapResultados.values()
				.stream()
				.flatMap(List::stream)
				.forEach(marcacao -> {
							Duration interval = Duration.between(marcacao.getDataHoraMarcacaoInicio() ,
									marcacao.getDataHoraMarcacaoFim());
							
							intervalos.merge(marcacao.getNome(), interval, Duration::plus);
		});

		intervalos
			.entrySet()
			.stream()
			.forEach(entry -> mapTotalPeriodo.put(entry.getKey(), DateUtils.formatDuration(entry.getValue())));
	}

	private void calculoHorasNoturnasCLT(Map<OffsetDateTime, List<MarcacaoVinculoInicioFimVO>> mapResultados, Map<String, String> horasNoturnasCLT) {
		Map<String, Duration> intervalosCLT = new HashMap<>();
		
		mapResultados.values()
				.stream()
				.flatMap(List::stream)
				.forEach(marcacao -> {
							OffsetDateTime dataInicio = marcacao.getDataHoraMarcacaoInicio();
							OffsetDateTime dataFim = marcacao.getDataHoraMarcacaoFim();
							
							if (dataInicio.getHour() >= 22 || dataInicio.getHour() <= 5) {
								Duration interval = Duration.between(dataInicio, dataFim);
								
								intervalosCLT.merge(marcacao.getNome(), interval, Duration::plus);
							}
		});
		intervalosCLT.entrySet().stream().forEach(entry -> horasNoturnasCLT.put(entry.getKey(),
				DateUtils.formatDuration(entry.getValue())));
	}

}