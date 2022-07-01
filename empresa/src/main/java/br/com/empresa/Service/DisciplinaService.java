package br.com.empresa.Service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.empresa.entity.Disciplina;
import br.com.empresa.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	DisciplinaRepository repo;
	
	public List<Disciplina> listarTodasDisciplinas() {
		return repo.findAll();
		// select * from turma; esse findAll significa isso.
				
	}
	
	
	public Disciplina buscaPorID(Integer id) throws ObjectNotFoundException{
		Optional<Disciplina> disciplina = repo.findById(id);
		return disciplina.orElseThrow(() -> new ObjectNotFoundException(null, "Objeto não encontrado"));
	}
	
	public Disciplina salvar (Disciplina disciplina) {
		return repo.save(disciplina);
		//esse save equivale a insert into turma (id, nome) values (??)
		
		
	}
	
	public void excluir(Integer id) {
		repo.deleteById(id);
	}
	
	public Disciplina alterar(Disciplina objDisciplina) {
		Disciplina disciplina = buscaPorID(objDisciplina.getId());
		disciplina.setNome(objDisciplina.getNome());
		return salvar (disciplina);
	}
}