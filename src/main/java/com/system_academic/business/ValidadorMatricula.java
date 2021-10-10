package com.system_academic.business;

import com.system_academic.controller.IStrategy;
import com.system_academic.dao.AlunoTurmaDao;
import com.system_academic.domain.DomainEntity;

public class ValidadorMatricula implements IStrategy {
	@Override
	public String process(DomainEntity entidade) {
		AlunoTurmaDao dao = new AlunoTurmaDao();
		Integer quantidade = dao.quantidadeMatriculados(entidade);
		if (quantidade < 5) {
			return null;
		} else {
			return "Capacidade maxima da turma atingida";
		}
	}
}