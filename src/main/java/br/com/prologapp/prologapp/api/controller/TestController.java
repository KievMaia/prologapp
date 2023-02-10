package br.com.prologapp.prologapp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prologapp.prologapp.api.model.dto.TotalPeriodoDTO;
import br.com.prologapp.prologapp.domain.service.MarcacaoVinculoService;

@RestController
@RequestMapping("/v1")
public class TestController {

	@Autowired
	private MarcacaoVinculoService service;

	@GetMapping("/marcacao/completa/{cpf}")
	public TotalPeriodoDTO lista(@PathVariable String cpf) {
		return service.calculoIntervaloMarcacao(cpf);
	}
}
