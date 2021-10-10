package com.system_academic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.system_academic.domain.Aluno;
import com.system_academic.domain.DomainEntity;
import com.system_academic.domain.Turma;
import com.system_academic.domain.enums.Modulo;
import com.system_academic.domain.enums.Semana;
import com.system_academic.domain.enums.Turno;
import com.system_academic.util.Conection;

public class AlunoDao extends AbstractDao{

	@Override
	public void create(DomainEntity entidade) {
		Aluno aluno = (Aluno) entidade;
		String create = "insert into alunos (nome_aluno, cpf_aluno, mae_aluno,email_aluno) values (?,?,?,?)";

		try {
			Connection connection = new Conection().conectar();
			PreparedStatement pst = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getCPF());
			pst.setString(3, aluno.getNome_mae());
			pst.setString(4, aluno.getEmail());
			pst.executeUpdate();
			System.out.println("Query Executada - Alunos");

			ResultSet rs = pst.getGeneratedKeys();
			Integer id = null;
			if (rs != null && rs.next())
				id = rs.getInt(1);
			aluno.setId(id);
			System.out.println("Id do banco = " + id);
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(DomainEntity entidade) {
		Aluno aluno = (Aluno) entidade;
		System.out.println("Conectado para alterar - ALUNO- NO DAO");
		PreparedStatement pst = null;

		try {
			Connection connection = new Conection().conectar();
			//connection.setAutoCommit(true);
			String alterar = "update alunos set nome_aluno=?, cpf_aluno=?, mae_aluno=? ,email_aluno=? where id_aluno=?";
			pst = connection.prepareStatement(alterar, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getCPF());
			pst.setString(3, aluno.getNome_mae());
			pst.setString(4, aluno.getEmail());
			pst.setInt(5, aluno.getId());
			pst.executeUpdate();
			connection.close();
			System.out.println("UPDATE CONCLUIDO NO DAO");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void delete(DomainEntity entidade) {
		System.out.println("Conectado para excluir");
		Aluno aluno = (Aluno) entidade;
		PreparedStatement pst = null;

		try {
			Connection connection = new Conection().conectar();
			String delete = "DELETE FROM alunos WHERE id_aluno = ?";

			pst = connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, aluno.getId());
			pst.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<DomainEntity> read(DomainEntity entidade) {
		Connection connection = new Conection().conectar();
		ArrayList<DomainEntity> listaAlunos = new ArrayList<>();
		String read = "select * from alunos order by id_aluno";

		try {
			PreparedStatement pst = connection.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Integer id = rs.getInt(1);
					String nome = rs.getString(2);
					String CPF = rs.getString(3);
					String nome_mae = rs.getString(4);
					String email = rs.getString(5);

					Aluno alunoBD = new Aluno(nome, CPF, nome_mae, email);
					alunoBD.setId(id);
					atualizarTurmasByAluno(alunoBD);
					listaAlunos.add(alunoBD);
				}
			
			connection.close();
			return listaAlunos;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public DomainEntity select(DomainEntity entidade) {
		System.out.println("Entrou no comando de selecionar");
		String read2 = "select * from alunos where id_aluno =?";
		Aluno aluno = (Aluno) entidade;

		try {
			Connection connection = new Conection().conectar();
			PreparedStatement pst = connection.prepareStatement(read2);
			pst.setInt(1, entidade.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				aluno.setId(rs.getInt(1));
				aluno.setNome(rs.getString(2));
				aluno.setCPF(rs.getString(3));
				aluno.setNome_mae(rs.getString(4));
				aluno.setEmail(rs.getString(5));
			}
			System.out.println("Valores pegos no Banco de Dados");
			System.out.println(aluno.getId());
			System.out.println(aluno.getNome());
			System.out.println(aluno.getCPF());
			System.out.println(aluno.getNome_mae());
			System.out.println(aluno.getEmail());
			connection.close();
			return aluno;
		} catch (Exception e) {
			System.out.println(e);

		}
		return null;
	}
	
	private DomainEntity atualizarTurmasByAluno(DomainEntity entidade) {
		Aluno aluno = (Aluno) entidade;
		String consulta = "SELECT ID_TURMA, CAPACIDADE_TURMA, ANO_TURMA, SEMESTRE_TURMA, MODULO_MATERIA, TURNO_MATERIA, DIA_SEMANA_MATERIA FROM alunos alu "
				+ " join alunos_turmas AluTur on alu.ID_ALUNO = AluTur.ID_ALUNO_AT "
				+ " join turmas tur on tur.ID_TURMA=AluTur.ID_TURMA_AT"
				+ " join materias mat on mat.ID_MATERIA = tur.ID_MATERIA where alu.ID_aluno = ?";

		try {
			Connection connection = new Conection().conectar();
			PreparedStatement pst = connection.prepareStatement(consulta);
			pst.setInt(1, aluno.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer ano = rs.getInt(3);
				Integer semestre = Integer.parseInt(rs.getString(4));
				Modulo modulo = Modulo.valueOf(rs.getString(5));
				Turno turno = Turno.valueOf(rs.getString(6));
				Semana semana = Semana.valueOf(rs.getString(7));
				Turma turma = new Turma(ano, semestre, modulo, turno, semana);
				turma.setId(id);
				aluno.setTurmas(turma);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
