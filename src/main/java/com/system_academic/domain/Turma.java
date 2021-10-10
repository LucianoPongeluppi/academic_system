package com.system_academic.domain;

import java.util.ArrayList;
import java.util.List;
import com.system_academic.domain.enums.Modulo;
import com.system_academic.domain.enums.Semana;
import com.system_academic.domain.enums.Turno;

public class Turma extends DomainEntity {
	private int ano;
	private int semestre;
	private Modulo modulo;
	private Turno turno;
	private Semana diaSemana;
	List<Aluno> alunos;
	Materia materia;
	
	public Turma( int ano, int semestre, Modulo modulo, Turno turno, Semana diaSemana) {
		super();
		this.ano = ano;
		this.semestre = semestre;
		this.modulo = modulo;
		this.turno = turno;
		this.diaSemana = diaSemana;
		alunos = new ArrayList<Aluno>();
	}
	public enum modulo {
		PRIMEIRO,
		SEGUNDO,
		INTEGRAL;
	}
	
	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public void setAlunos(Aluno aluno) {
		this.alunos.add(aluno);
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public Turma() {
		super();
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Semana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(Semana diaSemana) {
		this.diaSemana = diaSemana;
	}

	@Override
	public String toString() {
		//return "ID do aluno: " + this.getId()+" | Nome: "+ nome + " | CPF=" + CPF + "<br>"  ;
		return "Turma "+this.getId() + " | Dia " + diaSemana +  " | Modulo " + modulo.name()+ "<br>"  ;
	}
}
