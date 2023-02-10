package br.com.prologapp.prologapp.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prologapp.prologapp.domain.model.MarcacaoTipo;

@Repository
public interface MarcacaoTipoRespository extends JpaRepository<MarcacaoTipo, Long>{

	List<MarcacaoTipo> findAll();
}
