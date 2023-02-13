package br.com.prologapp.prologapp.domain.service;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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

	public TotalPeriodoDTO listaTotalPeriodo(String cpf, String dataInicial, String dataFinal) {
		TotalPeriodoDTO totalPeriodoDTO = new TotalPeriodoDTO();
		
		List<MarcacaoVinculoInicioFimVO> listaMarcacoes = marcacaoVinculoInicioFimRepository.findAllGroupedByDia(cpf,
				dataInicial, dataFinal);

		Map<String, List<MarcacaoVinculoInicioFimVO>> mapResultados = new TreeMap<>(listaMarcacoes
				.stream()
				.collect(
						Collectors.groupingBy(marcacao -> DateUtils.formataData((marcacao.getDia())))));

		this.calculoTotalPeriodo(mapResultados, totalPeriodoDTO.getTotalPeriodo());
		
		this.calculoHorasNoturnasCLT(mapResultados, totalPeriodoDTO.getHorasNoturnasCLT());
		
		totalPeriodoDTO.getMarcacaoListTotalPeriodo().putAll(mapResultados);
		
		return totalPeriodoDTO;
	}

	private void calculoTotalPeriodo(Map<String, List<MarcacaoVinculoInicioFimVO>> mapResultados, Map<String, String> mapTotalPeriodo) {
		Map<String, Duration> intervalos = new HashMap<>();

		mapResultados.values()
				.stream()
				.flatMap(List::stream)
				.forEach(marcacao -> {
							Duration interval = Duration.between(marcacao.getDataHoraMarcacaoInicio().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")) ,
									marcacao.getDataHoraMarcacaoFim().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
							
							intervalos.merge(marcacao.getNome(), interval, Duration::plus);
		});

		intervalos
			.entrySet()
			.stream()
			.forEach(entry -> mapTotalPeriodo.put(entry.getKey(), DateUtils.formatDuration(entry.getValue())));
	}

	private void calculoHorasNoturnasCLT(Map<String, List<MarcacaoVinculoInicioFimVO>> mapResultados, Map<String, String> horasNoturnasCLT) {
		Map<String, Duration> intervalosCLT = new HashMap<>();
		
		mapResultados.values()
				.stream()
				.flatMap(List::stream)
				.forEach(marcacao -> {
							ZonedDateTime dataInicio = marcacao.getDataHoraMarcacaoInicio().withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
							ZonedDateTime dataFim = marcacao.getDataHoraMarcacaoFim().withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
							
							if (dataInicio.getHour() >= 22 || dataInicio.getHour() <= 5) {
								Duration interval = Duration.between(dataInicio, dataFim);
								
								intervalosCLT.merge(marcacao.getNome(), interval, Duration::plus);
							}
		});
		intervalosCLT.entrySet().stream().forEach(entry -> horasNoturnasCLT.put(entry.getKey(),
				DateUtils.formatDuration(entry.getValue())));
	}

}