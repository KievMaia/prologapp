package br.com.prologapp.prologapp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.prologapp.prologapp.api.model.dto.TotalPeriodoDTO;
import br.com.prologapp.prologapp.domain.service.MarcacaoVinculoService;
import br.com.prologapp.prologapp.infrastructure.service.PdfTotalPeriodoReportService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/v1")
public class TestController {

	@Autowired	
	private MarcacaoVinculoService service;
	
	@Autowired 
	private PdfTotalPeriodoReportService pdfTotalPeriodoService;

	@GetMapping(path = "/marcacao/completa/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TotalPeriodoDTO consultaTotalPeriodo(
			@PathVariable String cpf,
			@RequestParam(value = "dataInicial", required = true) String dataInicial,
			@RequestParam(value = "dataFinal", required = true) String dataFinal
			) {
		return service.listaTotalPeriodo(cpf, dataInicial, dataFinal);
	}
	
	@GetMapping(path = "/marcacao/completa/{cpf}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> consultaTotalPeriodoPdf(
			@PathVariable String cpf,
			@RequestParam(value = "dataInicial", required = true) String dataInicial,
			@RequestParam(value = "dataFinal", required = true) String dataFinal
			) throws JRException {
		
		byte[] bytesPdf = pdfTotalPeriodoService.emitirTotalPeriodo(cpf, dataInicial, dataFinal);
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_controle_jornada.pdf");
		
		return ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_PDF)
						.headers(headers)
						.body(bytesPdf);
	}
}
