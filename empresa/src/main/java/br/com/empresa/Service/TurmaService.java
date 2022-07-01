package br.com.empresa.Service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.empresa.entity.Turma;
import br.com.empresa.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository repo;
	
	public List<Turma> listarTodasTurmas() {
		return repo.findAll();
		// select * from turma; esse findAll significa isso.
				
	}
	
	
	public Turma buscaPorID(Integer id) throws ObjectNotFoundException{
		Optional<Turma> turma = repo.findById(id);
		return turma.orElseThrow(() -> new ObjectNotFoundException(null, "Turma não encontrada"));
	}
	
	public Turma salvar (Turma turma) {
		return repo.save(turma);
		//esse save equivale a insert into turma (id, nome) values (??)
		
		
	}
	
	public void excluir(Integer id) {
		repo.deleteById(id);
	}
	
	public Turma alterar(Turma objTurma) {
		Turma turma = buscaPorID(objTurma.getId());
		turma.setNome(objTurma.getNome());
		return salvar (turma);
	}
}
