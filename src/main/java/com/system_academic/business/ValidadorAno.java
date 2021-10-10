package com.system_academic.business;

import java.util.Calendar;
import com.system_academic.domain.Turma;
import com.system_academic.controller.IStrategy;
import com.system_academic.domain.DomainEntity;

public class ValidadorAno implements IStrategy {
    @Override
	public String process(DomainEntity entidade) {
		if (entidade instanceof Turma) {
			Turma turma = (Turma) entidade;
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			if (turma.getAno() < year) {
				return "Não é possível cadastrar turmas em anos anteriores";
			} else {
				return null;
			}
		}
		return "Algo de errado aconteceu!";
	}
}

