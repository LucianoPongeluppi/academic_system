package com.system_academic.domain;

public class Materia extends DomainEntity {
	private String nome;
	private int cargaHoraria;
	private String descricao;
	Curso curso;

	public Materia(String nome, int cargaHoraria, String descricao) {
		super();
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.descricao = descricao;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Materia() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		if (nome == null) {
			return "";
		} else {
			return "Materia: " + nome + "<br>";
		}
	}
}