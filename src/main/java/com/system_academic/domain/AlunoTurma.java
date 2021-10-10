package com.system_academic.domain;

public class AlunoTurma extends DomainEntity {
	private int id_turma;
	private int id_aluno;
	private String nomeAluno;
	private String nomeCurso;
	private String nomeMateria;

	public AlunoTurma() {
		super();
	}
	public AlunoTurma(int id_turma, int id_aluno) {
		super();
		this.id_turma = id_turma;
		this.id_aluno = id_aluno;
	}
	
	public AlunoTurma(int id_turma, int id_aluno, String nomeAluno, String nomeCurso, String nomeMateria) {
		super();
		this.id_turma = id_turma;
		this.id_aluno = id_aluno;
		this.nomeAluno = nomeAluno;
		this.nomeCurso = nomeCurso;
		this.nomeMateria = nomeMateria;
	}
	
	public int getId_turma() {
		return id_turma;
	}
	public void setId_turma(int id_turma) {
		this.id_turma = id_turma;
	}
	public int getId_aluno() {
		return id_aluno;
	}
	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public String getNomeMateria() {
		return nomeMateria;
	}
	public void setNomeMateria(String nomeMateria) {
		this.nomeMateria = nomeMateria;
	}
	
}