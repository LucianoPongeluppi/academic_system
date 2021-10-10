package com.system_academic.business;

import com.system_academic.controller.IStrategy;
import com.system_academic.dao.AlunoTurmaDao;
import com.system_academic.domain.DomainEntity;

public class ValidadorDuplicidade implements IStrategy{
    @Override
	public String process(DomainEntity entidade) {
		AlunoTurmaDao dao = new AlunoTurmaDao();
		Integer quantidade = dao.VerificarMatriculados(entidade);
		System.out.println("Valor retornado de quantidade: " + quantidade);
		if (quantidade == 0) {
			return null;
		} else {
			return "Aluno Ja cadastrado na turma";
		}
	}
}
