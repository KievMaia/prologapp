package br.com.prologapp.prologapp.infrastructure.service;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prologapp.prologapp.domain.repository.MarcacaoVinculoInicioFimRepository;
import br.com.prologapp.prologapp.domain.service.TotalPeriodoReportService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfTotalPeriodoReportService implements TotalPeriodoReportService{

	@Autowired
	private MarcacaoVinculoInicioFimRepository marcacaoVinculoInicioFimRepository;
	
	@Override
	public byte[] emitirTotalPeriodo(String cpf, String dataIncial, String dataFinal) {
		
		try {
		var inputStream = this.getClass().getResourceAsStream("/relatorios/relatorio_controle_jornada.jasper");
		
		var parametros = new HashMap<String, Object>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		
		var totalPeriodo = marcacaoVinculoInicioFimRepository.findAllGroupedByDia(cpf, dataIncial, dataFinal);
		
		var dataSource = new JRBeanCollectionDataSource(totalPeriodo);
		
		var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
		
		}catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
