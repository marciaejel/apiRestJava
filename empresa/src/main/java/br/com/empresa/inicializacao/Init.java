package br.com.empresa.inicializacao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.empresa.Service.AvaliacaoService;
import br.com.empresa.Service.DisciplinaService;
import br.com.empresa.Service.TurmaService;
import br.com.empresa.entity.Aluno;
import br.com.empresa.entity.AlunoDisciplina;
import br.com.empresa.entity.Avaliacao;
import br.com.empresa.entity.Turma;
import br.com.empresa.entity.Disciplina;
import br.com.empresa.repository.AlunoRepository;
import br.com.empresa.repository.TurmaRepository;
import br.com.empresa.repository.DisciplinaRepository;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	TurmaRepository turmaRepo;
	
	@Autowired
	AlunoRepository alunoRepo;
	
	@Autowired
	DisciplinaRepository disciplinaRepo;
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@Autowired
	AvaliacaoService avaliacaoService;
	
	@Autowired
	TurmaService turmaService;


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		Aluno aluno1 = new Aluno();
		aluno1.setNome("Márcia");
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Pedro");
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("Tereza");
		
		//alunoRepo.saveAll(Arrays.asList(aluno1,aluno2,aluno3));
		//equivalente a insert into aluno (nome)values("Lucas")
	
		
			Disciplina java = new Disciplina();
			java.setNome("Java");
			
			disciplinaService.salvar(java);
			
			Disciplina python = new Disciplina();
			python.setNome("Python");
			
			disciplinaService.salvar(python);
			
			Disciplina nodejs = new Disciplina();
			nodejs.setNome("Nodejs");
			
			disciplinaService.salvar(nodejs);
			
			
			Turma backend = new Turma();
			backend.setNome("Backend");
			turmaService.salvar(backend);
			
			Turma sysops = new Turma();
			sysops.setNome("Sysops");
			turmaService.salvar(sysops);
			
			Turma aws = new Turma();
			aws.setNome("AWS");
			turmaService.salvar(aws);
			
			
			//setou para salvar as turmas de cada aluno no banco
			aluno1.setTurma(backend);
			aluno2.setTurma(aws);
			aluno3.setTurma(sysops);
			
			//setando as diciplinas que o aluno está matriculado
			aluno1.setDisciplinas(Arrays.asList(java, python, nodejs));
			aluno2.setDisciplinas(Arrays.asList(java, nodejs));
			aluno3.setDisciplinas(Arrays.asList(java, python));
			
			//estava no método de aluno. Deslocou para fazer os relacionamentos.
			alunoRepo.save(aluno1);
			alunoRepo.save(aluno2);
			alunoRepo.save(aluno3);
			
			Avaliacao avaliacaoAluno1 = new Avaliacao();
		
			AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
			alunoDisciplina.setAluno(aluno1);
			alunoDisciplina.setDisciplina(java);
			
			avaliacaoAluno1.setAlunoDisciplina(alunoDisciplina);
			avaliacaoAluno1.setConceito("A");
			avaliacaoService.save(avaliacaoAluno1);
			
			AlunoDisciplina testePython = new AlunoDisciplina();
			testePython.setAluno(aluno2);
			testePython.setDisciplina(python);
			
			Avaliacao avaliacaoTestePython = new Avaliacao();
			avaliacaoTestePython.setAlunoDisciplina(testePython);
			avaliacaoTestePython.setConceito("B");
			
			avaliacaoService.save(avaliacaoTestePython);
			
			// Outra maneira de fazer

//			Turma turma1 = new Turma();
//			turma1.setNome("ImpulsoTec Backend");
//			
//			Turma turma2 = new Turma();
//			turma2.setNome("ImpulsoTec SysOps");
//			
//			Turma turma3 = new Turma();
//			turma3.setNome("K2 Backend");
//			
//						
			//turmaRepo.saveAll(Arrays.asList(turma1,turma2,turma3));
	
//			Disciplina disciplina1 = new Disciplina();
//			disciplina1.setNome("Java");
//			
//			Disciplina disciplina2 = new Disciplina();
//			disciplina2.setNome("Python");
//			
//			Disciplina disciplina3 = new Disciplina();
//			disciplina3.setNome("Nodejs");
//			
//				
//	        disciplinaRepo.saveAll(Arrays.asList(disciplina1,disciplina2,disciplina3));
	
			//Avaliacao aval = avaliacaoService.buscarNotaAlunoDisciplina(alunoDisciplina);
			//System.out.println("Aluno: " + aval.getAlunoDisciplina().getAluno().getNome());
			//System.out.println("Disciplina: " + aval.getAlunoDisciplina().getDisciplina().getNome());
			//System.out.println("Avaliação: " + aval.getConceito());
			
			
	}
}
