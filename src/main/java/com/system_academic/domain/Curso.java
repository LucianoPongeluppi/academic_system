package com.system_academic.domain;

import java.util.ArrayList;
import java.util.List;

public class Curso extends DomainEntity {
	
	private String nome;
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Curso(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	
	List<Materia> materias = new ArrayList<Materia>();

	public Curso() {
		super();
	}
}
