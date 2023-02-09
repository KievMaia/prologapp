package br.com.prologapp.prologapp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prologapp.prologapp.api.model.vo.MarcacaoVinculoInicioFimVO;
import br.com.prologapp.prologapp.domain.repository.MarcacaoVinculoInicioFimRepository;

@RestController
@RequestMapping("/v1")
public class TestController {

	@Autowired
	private MarcacaoVinculoInicioFimRepository marcacaoVinculoInicioFimRepository;
	
//	@GetMapping("/marcacao/{codigo}")
//	public MarcacaoVinculoInicioFimVO busca(@PathVariable Long codigo) {
//		MarcacaoVinculoInicioFimVO marcacao = marcacaoVinculoInicioFimRepository.findByCodigo(codigo);
//		
//		return marcacao;
//	}

	@GetMapping("/marcacao/completa/{cpf}")
	public List<MarcacaoVinculoInicioFimVO> lista(@PathVariable String cpf) {
		return marcacaoVinculoInicioFimRepository.findAllGroupedByDia(cpf);
	}
}
