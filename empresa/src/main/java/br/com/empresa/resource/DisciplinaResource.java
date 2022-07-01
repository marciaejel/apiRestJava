package br.com.empresa.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.empresa.Service.DisciplinaService;
import br.com.empresa.entity.Disciplina;

@RequestMapping("/disciplina")
@RestController
public class DisciplinaResource {

	@Autowired
	private DisciplinaService disciplinaService;
	
	//Método para listar
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Disciplina>> listarDisciplina(){
		List<Disciplina>disciplinas = disciplinaService.listarTodasDisciplinas();
		return ResponseEntity.ok().body(disciplinas);
		
	}
	
	//Método para mostrar pelo id
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Disciplina> buscaPorId(@PathVariable Integer id) throws ObjectNotFoundException{
		Disciplina disciplina = disciplinaService.buscaPorID(id);
		return ResponseEntity.ok().body(disciplina);
	}
	
	//Método para salvar um novo
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Disciplina objDisciplina){
		Disciplina disciplina = disciplinaService.salvar(objDisciplina);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(disciplina.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	//Método para apagar
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		disciplinaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	//Método para alterar ou atualizar
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Disciplina objDisciplina, @PathVariable Integer id){
		objDisciplina.setId(id);
		disciplinaService.alterar(objDisciplina);
		return ResponseEntity.noContent().build();
	}
}
