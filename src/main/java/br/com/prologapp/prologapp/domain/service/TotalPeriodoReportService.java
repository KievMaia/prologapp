package br.com.prologapp.prologapp.domain.service;

public interface TotalPeriodoReportService {

	byte[] emitirTotalPeriodo(String cpf, String dataIncial, String dataFinal);
}
