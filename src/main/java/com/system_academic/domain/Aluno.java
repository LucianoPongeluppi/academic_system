package com.system_academic.domain;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends DomainEntity {
	private String nome;
	private String CPF;
	private String nome_mae;
	private String email;
	List<Turma> turmas;
	
	public Aluno(String nome, String CPF, String nome_mae, String email) {
		super();
		this.nome = nome;
		this.CPF = CPF;
		this.nome_mae = nome_mae;
		this.email = email;
		this.turmas = new ArrayList<>();
	}

	public Aluno() {
		super();
	}
	
	public void setTurmas(Turma turma) {
		turmas.add(turma);
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}


	public String getNome_mae() {
		return nome_mae;
	}

	public void setNome_mae(String nome_mae) {
		this.nome_mae = nome_mae;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public  String toString() {
		if (nome == null) {
		    return "";
        }else {
            return "ID do aluno: " + this.getId()+" | Nome: "+ nome + " | CPF=" + CPF + "<br>"  ;	
        }
	}
}
