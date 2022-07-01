package br.com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empresa.entity.AlunoDisciplina;
import br.com.empresa.entity.Avaliacao;


@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao,AlunoDisciplina> {

	//select * from avaliação where idaluno = 1 and iddisciplina = 2
	Avaliacao findByAlunoDisciplina(AlunoDisciplina alunoDisciplina);

	
}
