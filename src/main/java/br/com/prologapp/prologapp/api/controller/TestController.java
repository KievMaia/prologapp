package br.com.prologapp.prologapp.api.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.prologapp.prologapp.api.model.dto.TotalPeriodoDTO;
import br.com.prologapp.prologapp.domain.service.MarcacaoVinculoService;

@RestController
@RequestMapping("/v1")
public class TestController {

	@Autowired	private MarcacaoVinculoService service;

	@GetMapping("/marcacao/completa/{cpf}")
	public TotalPeriodoDTO lista(
			@PathVariable String cpf,
			@RequestParam(value = "dataInicial", required = true) String dataInicial,
			@RequestParam(value = "dataFinal", required = true) String dataFinal
			) throws ParseException {
		return service.listaTotalPeriodo(cpf, dataInicial, dataFinal);
	}
}
