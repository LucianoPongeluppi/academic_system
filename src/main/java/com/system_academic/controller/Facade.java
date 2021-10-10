package com.system_academic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.system_academic.business.ValidadorAluno;
import com.system_academic.business.ValidadorAno;
import com.system_academic.business.ValidadorCpf;
import com.system_academic.business.ValidadorDuplicidade;
import com.system_academic.business.ValidadorMatricula;
import com.system_academic.dao.AlunoDao;
import com.system_academic.dao.AlunoTurmaDao;
import com.system_academic.dao.CursoDao;
import com.system_academic.dao.IDao;
import com.system_academic.dao.MateriaDao;
import com.system_academic.dao.TurmaDao;
import com.system_academic.domain.Aluno;
import com.system_academic.domain.AlunoTurma;
import com.system_academic.domain.Curso;
import com.system_academic.domain.DomainEntity;
import com.system_academic.domain.Materia;
import com.system_academic.domain.Turma;

public class Facade implements IFacade {
	private Map<String, IDao> daos;
	private Map<String, List<IStrategy>> rns;

	public Facade() {
		definirDAOS();
		definirRNS();
	}

	private void definirRNS() {
		rns = new HashMap<String, List<IStrategy>>();
		
		List<IStrategy> rnsAluno = new ArrayList<IStrategy>();
		ValidadorCpf vCpf = new ValidadorCpf();
		ValidadorAluno vDados = new ValidadorAluno();
		rnsAluno.add(vCpf);		
		rnsAluno.add(vDados);
		rns.put(Aluno.class.getName(), rnsAluno);
		
		List<IStrategy> rnsTurma = new ArrayList<IStrategy>();
		ValidadorAno vAno = new ValidadorAno();
		rnsTurma.add(vAno);
		rns.put(Turma.class.getName(), rnsTurma);
		
		List<IStrategy> rnsTurmaAluno = new ArrayList<IStrategy>();
		ValidadorMatricula vMatr = new ValidadorMatricula();
		ValidadorDuplicidade vDupli = new ValidadorDuplicidade();
		rnsTurmaAluno.add(vMatr);
		rnsTurmaAluno.add(vDupli);		
		rns.put(AlunoTurma.class.getName(), rnsTurmaAluno);
	}

	private void definirDAOS() {
		daos = new HashMap<String, IDao>();
		daos.put(Aluno.class.getName(), new AlunoDao());
		daos.put(Curso.class.getName(), new CursoDao());
		daos.put(Materia.class.getName(), new MateriaDao());
		daos.put(Turma.class.getName(), new TurmaDao());
		daos.put(AlunoTurma.class.getName(), new AlunoTurmaDao());
	}

	@Override
	public String create(DomainEntity entidade) {
		String msg = executeRules(entidade);
		String nmClasse = entidade.getClass().getName();
		if (msg == null) {
			IDao dao = daos.get(nmClasse);
			dao.create(entidade);
		} else {
			return msg;
		}
		return null;
	}
	
	@Override
	public DomainEntity select(DomainEntity entidade) {
		try {
			String nmClasse = entidade.getClass().getName();
			IDao dao = daos.get(nmClasse);
			return dao.select(entidade);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	private String executeRules(DomainEntity entidade) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();

		List<IStrategy> regras = rns.get(nmClasse);

		if (regras != null) {
			for (IStrategy s : regras) {
				String m = s.process(entidade);
				if (m != null) {
					msg.append(m);
					msg.append("\n");
				}
			}
		}
		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}

	@Override
	public String delete(DomainEntity entidade) {
		String nmClasse = entidade.getClass().getName();
		IDao dao = daos.get(nmClasse);
		dao.delete(entidade);
		return null;
	}

	@Override
	public String update(DomainEntity entidade) {
		String nmClasse = entidade.getClass().getName();
		IDao dao = daos.get(nmClasse);
		dao.update(entidade);
		return null;
	}

	@Override
	public List<DomainEntity> read(DomainEntity entidade) {
		String msg = null;
		String nmClass = entidade.getClass().getName();

		List<DomainEntity> listaRetorno = null;
		
		if (msg == null) {
			IDao dao = daos.get(nmClass);
			listaRetorno = dao.read(entidade);
		}
		return listaRetorno;
	}
}
