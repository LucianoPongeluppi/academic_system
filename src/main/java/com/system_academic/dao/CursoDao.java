package com.system_academic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.system_academic.domain.Curso;
import com.system_academic.domain.DomainEntity;
import com.system_academic.util.Conection;

public class CursoDao extends AbstractDao {
	@Override
	public void create(DomainEntity entidade) {
		Curso curso = (Curso) entidade;
		String create = "insert into cursos (nome_curso,descricao_curso) values (?,?)";
		Connection connection = new Conection().conectar();

		try {
			PreparedStatement pst = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, curso.getNome());
			pst.setString(2, curso.getDescricao());
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			Integer id = null;
			if (rs != null && rs.next())
				id = rs.getInt(1);
			curso.setId(id);

			System.out.println("Id do banco = " + id);
			connection.close();
			System.out.println("Query Executada - Curso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(DomainEntity entidade) {
		System.out.println("Conectado para alterar - curso");
		Curso curso = (Curso) entidade;
		PreparedStatement pst = null;

		try {
			Connection connection = new Conection().conectar();
			connection.setAutoCommit(true);
			String alterar = "UPDATE cursos SET nome_curso = ?,descricao_curso = ? WHERE id_curso = ?";
			pst = connection.prepareStatement(alterar, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, curso.getNome());
			pst.setString(2, curso.getDescricao());
			pst.setInt(3, curso.getId());
			
			pst.executeUpdate();
			connection.close();
			System.out.println("UPDATE CONCLUIDO NO DAO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(DomainEntity entidade) {
		System.out.println("Conectado para excluir");
		Curso curso = (Curso) entidade;
		PreparedStatement pst = null;

		try {
			Connection connection = new Conection().conectar();
			String delete = "DELETE FROM cursos WHERE id_curso = ?";

			pst = connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, curso.getId());
			pst.executeUpdate();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<DomainEntity> read(DomainEntity entidade) {
		Connection connection = new Conection().conectar();
		ArrayList<DomainEntity> listaCursos = new ArrayList<>();
		String read = "select * from cursos order by nome_curso";

		try {
			PreparedStatement pst = connection.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String nome = rs.getString(2);
				String descricao = rs.getString(3);
				Curso cursoBD = new Curso(nome, descricao);
				cursoBD.setId(id);
				listaCursos.add(cursoBD);
			}
			connection.close();
			return listaCursos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	public DomainEntity select(DomainEntity entidade) {	
		System.out.println("Entrou no comando de selecionar");
		String read2 = "select * from cursos where id_curso =?";
		Curso curso = (Curso) entidade;

		try {
			Connection connection = new Conection().conectar();
			PreparedStatement pst = connection.prepareStatement(read2);
			pst.setInt(1, entidade.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				curso.setId(rs.getInt(1));
				curso.setNome(rs.getString(2));
				curso.setDescricao(rs.getString(3));
			}
			System.out.println("Valores pegos no Banco de Dados");
			System.out.println(curso.getId());
			System.out.println(curso.getNome());
			System.out.println(curso.getDescricao());
			connection.close();
			return curso;			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
