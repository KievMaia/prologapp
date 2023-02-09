package br.com.prologapp.prologapp.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.prologapp.prologapp.api.model.vo.MarcacaoVinculoInicioFimVO;
import br.com.prologapp.prologapp.domain.model.MarcacaoVinculoInicioFim;

@Repository
public interface MarcacaoVinculoInicioFimRepository extends JpaRepository<MarcacaoVinculoInicioFim, Long> {

//	@Query("SELECT mv FROM MarcacaoVinculoInicioFim mv JOIN FETCH mv.marcacaoInicio mi JOIN FETCH mv.marcacaoFim mf WHERE mi.cpfColaborador = :cpf")
//	List<MarcacaoVinculoInicioFim> findByCpf(String cpf);
	
	@Query("SELECT new br.com.prologapp.prologapp.api.model.vo.MarcacaoVinculoInicioFimVO(mt.codigo, mt.nome, date_trunc('day', mi.dataHoraMarcacao) as dia, " +
	           "mi.dataHoraMarcacao as dataHoraMarcacaoInicio, " +
	           "mf.dataHoraMarcacao as dataHoraMarcacaoFim) " +
	           "FROM MarcacaoVinculoInicioFim mvi " +
	           "JOIN Marcacao mi ON mvi.marcacaoInicio = mi.codigo " +
	           "JOIN Marcacao mf ON mvi.marcacaoFim = mf.codigo " +
	           "JOIN MarcacaoTipo mt ON mt.codigo = mi.marcacaoTipo " +
	           "WHERE mi.cpfColaborador = :cpf " +
	           "GROUP BY mt.codigo, dia, mi.dataHoraMarcacao, mf.dataHoraMarcacao " +
	           "ORDER BY dia")
	List<MarcacaoVinculoInicioFimVO> findAllGroupedByDia(String cpf);
}