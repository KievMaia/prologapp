package br.com.prologapp.prologapp.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.prologapp.prologapp.api.model.vo.MarcacaoVinculoInicioFimVO;
import br.com.prologapp.prologapp.domain.model.MarcacaoVinculoInicioFim;

@Repository
public interface MarcacaoVinculoInicioFimRepository extends JpaRepository<MarcacaoVinculoInicioFim, Long> {
	
	@Query("SELECT new br.com.prologapp.prologapp.api.model.vo.MarcacaoVinculoInicioFimVO(mt.codigo, mt.nome, c.nome, date_trunc('day', mi.dataHoraMarcacao) as dia, " +
	           "mi.dataHoraMarcacao as dataHoraMarcacaoInicio, " +
	           "mf.dataHoraMarcacao as dataHoraMarcacaoFim) " +
	           "FROM MarcacaoVinculoInicioFim mvi " +
	           "JOIN Marcacao mi ON mvi.marcacaoInicio = mi.codigo " +
	           "JOIN Marcacao mf ON mvi.marcacaoFim = mf.codigo " +
	           "JOIN MarcacaoTipo mt ON mt.codigo = mi.marcacaoTipo " +
	           "JOIN Colaborador c ON mi.colaborador = c.cpf " +
	           "WHERE mi.colaborador.cpf = :cpf " +
	           "AND mi.dataHoraMarcacao BETWEEN to_date(:dataInicial, 'DD/MM/YYYY') AND to_date(:dataFinal, 'DD/MM/YYYY') " +
	           "GROUP BY mt.codigo, c.nome, dia, mi.dataHoraMarcacao, mf.dataHoraMarcacao " +
	           "ORDER BY dia")
	List<MarcacaoVinculoInicioFimVO> findAllGroupedByDia(@Param("cpf") String cpf, @Param("dataInicial") String dataIncial, @Param("dataFinal") String dataFinal);
}