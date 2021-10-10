package com.system_academic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.system_academic.domain.AlunoTurma;
import com.system_academic.domain.DomainEntity;
import com.system_academic.util.Conection;

public class AlunoTurmaDao extends AbstractDao {

	@Override
	public void create(DomainEntity entidade) {
		AlunoTurma alunoTurma = (AlunoTurma) entidade;
		String create = "insert into alunos_turmas (id_aluno_at,id_turma_at) values (?,?)";
		
		try {
			Connection connection = new Conection().conectar();
			PreparedStatement pst = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, alunoTurma.getId_aluno());
			pst.setInt(2, alunoTurma.getId_turma());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(DomainEntity entidade) {

	}

	@Override
	public void delete(DomainEntity entidade) {
		System.out.println("Conectado para excluir");
		AlunoTurma aluTur = (AlunoTurma) entidade;
		PreparedStatement pst = null;

		try {
			Connection connection = new Conection().conectar();
			String delete = "DELETE FROM alunos_turmas WHERE id_aluno = ? and id_turma = ?";

			pst = connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, aluTur.getId_aluno());
			pst.setInt(2, aluTur.getId_turma());
			pst.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<DomainEntity> read(DomainEntity entidade) {
		
		Connection connection = new Conection().conectar();
		ArrayList<DomainEntity> listaAlunoTurma = new ArrayList<>();
		String read = "SELECT ID_ALUNO,NOME_ALUNO,NOME_CURSO, NOME_MATERIA,ID_TURMA "
				+ "FROM alunos alu  join alunos_turmas AluTur on alu.ID_ALUNO = AluTur.ID_ALUNO_AT join turmas tur on tur.ID_TURMA=AluTur.ID_TURMA_AT "
				+ "join materias mat on mat.ID_MATERIA=tur.ID_MATERIA "
				+ "join cursos cur on mat.ID_CURSO=cur.ID_CURSO order by ID_ALUNO ";

		try {
			PreparedStatement pst = connection.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Integer id_alu = rs.getInt(1);
				String nomeAluno = rs.getString(2);
				String nomeCurso = rs.getString(3);
				String nomeMateria = rs.getString(4);
				Integer id_tur = rs.getInt(5);

				AlunoTurma aluTurBD = new AlunoTurma(id_tur,id_alu,nomeAluno,nomeCurso,nomeMateria);
				listaAlunoTurma.add(aluTurBD);
			}
			connection.close();
			return listaAlunoTurma;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public DomainEntity select(DomainEntity entidade) {
		System.out.println("Entrou no comando de selecionar matricula");
		String read2 = "select * from alunos_turmas where id_turma_at =?";
		AlunoTurma alunoTurma = (AlunoTurma) entidade;

		try {
			Connection connection = new Conection().conectar();
			PreparedStatement pst = connection.prepareStatement(read2);
			pst.setInt(1, alunoTurma.getId_turma());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				alunoTurma.setId_aluno(rs.getInt(1));
				alunoTurma.setId_turma(rs.getInt(2));
			}
			return alunoTurma;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public Integer quantidadeMatriculados (DomainEntity entidade) {
		AlunoTurma alunoTurma = (AlunoTurma) entidade;
		String consulta = "SELECT count(id_aluno_at) FROM alunos_turmas where ID_TURMA_AT = ?";
		int quantidade = 0;
		try {
			Connection connection = new Conection().conectar();
			PreparedStatement pst = connection.prepareStatement(consulta);
			pst.setInt(1, alunoTurma.getId_turma());
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
			quantidade = rs.getInt(1);
			}
			return quantidade;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public Integer VerificarMatriculados (DomainEntity entidade) {
		AlunoTurma alunoTurma = (AlunoTurma) entidade;
		System.out.println("Entrou no dao para contagem");
		
		System.out.println(alunoTurma.getId_turma());
		System.out.println(alunoTurma.getId_aluno());
		System.out.println(alunoTurma.getId());
		
		String consulta = "SELECT * FROM alunos_turmas where ID_TURMA_AT = ? and ID_aluno_AT = ? ";
		int quantidade = 0;
		try {
			Connection connection = new Conection().conectar();
			PreparedStatement pst = connection.prepareStatement(consulta);
			pst.setInt(1, alunoTurma.getId_turma());
			pst.setInt(2, alunoTurma.getId_aluno());
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
			quantidade = rs.getInt(1);
			}
			return quantidade;
		} catch (Exception e) {
			//System.out.println(e);
		}
		return 0;
	}
}
