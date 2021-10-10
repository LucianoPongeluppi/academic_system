package com.system_academic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.system_academic.domain.Aluno;
import com.system_academic.domain.DomainEntity;
import com.system_academic.domain.Materia;
import com.system_academic.domain.Turma;
import com.system_academic.domain.enums.Modulo;
import com.system_academic.domain.enums.Semana;
import com.system_academic.domain.enums.Turno;
import com.system_academic.util.Conection;

public class TurmaDao extends AbstractDao{
    @Override
    public void create(DomainEntity entidade) {
        Turma turma = (Turma) entidade;
        String create = "insert into turmas (turno_materia, modulo_materia, dia_semana_materia, ano_turma, semestre_turma) values (?,?,?,?,?)";

        try {
            Connection connection = new Conection().conectar();
            PreparedStatement pst = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, turma.getTurno().name());
            pst.setString(2, turma.getModulo().name());
            pst.setString(3, turma.getDiaSemana().name());
            pst.setInt(4, turma.getAno());
            pst.setInt(5, turma.getSemestre());
            pst.executeUpdate();
            System.out.println("Query Executada - Salvar Turmas");
            ResultSet rs = pst.getGeneratedKeys();
            Integer id = null;
            if (rs != null && rs.next())
                id = rs.getInt(1);
            turma.setId(id);
            System.out.println("Id do banco = " + id);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @Override
    public void update(DomainEntity entidade) {
        System.out.println("Conectado para alterar - turma");
        Turma turma = (Turma) entidade;
        PreparedStatement pst = null;
        try {
            Connection connection = new Conection().conectar();
            connection.setAutoCommit(true);
            String alterar = "UPDATE turmas SET TURNO_MATERIA = ?, MODULO_MATERIA = ?, DIA_SEMANA_MATERIA = ?, ano_turma = ?,semestre_turma = ? WHERE id_turma = ?";
            pst = connection.prepareStatement(alterar, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, turma.getTurno().name());
            pst.setString(2, turma.getModulo().name());
            pst.setString(3, turma.getDiaSemana().name());
            pst.setInt(4, turma.getAno());
            pst.setInt(5, turma.getSemestre());
            pst.setInt(6, turma.getId());
            pst.executeUpdate();
            connection.close();
            System.out.println("UPDATE CONCLUIDO NO DAO");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @Override
    public void delete(DomainEntity entidade) {
        Turma turma = (Turma) entidade;
        PreparedStatement pst = null;
        try {
            Connection connection = new Conection().conectar();
            String delete = "DELETE FROM turmas WHERE id_turma = ?";
            pst = connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, turma.getId());
            pst.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @Override
    public List<DomainEntity> read(DomainEntity entidade) {
        Connection connection = new Conection().conectar();
        System.out.println("Conectado para consulta - turma");
        ArrayList<DomainEntity> listaTurma = new ArrayList<>();
        String read = "select * from turmas order by id_turma";
        try {
            PreparedStatement pst = connection.prepareStatement(read);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                Turno turno = Turno.valueOf(rs.getString(3));
                Modulo modulo = Modulo.valueOf(rs.getString(4));
                Semana semana = Semana.valueOf(rs.getString(5));
                Integer ano = rs.getInt(7);
                Integer semestre = Integer.parseInt(rs.getString(8));
                Turma turmaBD = new Turma(ano, semestre, modulo, turno, semana);
                turmaBD.setId(id);
                atualizarAlunosByTurma(turmaBD);
                atualizarMateriaByTurma(turmaBD);
                listaTurma.add(turmaBD);
            }
            connection.close();
            return listaTurma;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public DomainEntity select(DomainEntity entidade) {
        System.out.println("Entrou no comando de selecionar");
        String read2 = "select * from turmas where id_turma =?";
        Turma turma = (Turma) entidade;
        try {
            Connection connection = new Conection().conectar();
            PreparedStatement pst = connection.prepareStatement(read2);
            pst.setInt(1, entidade.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                turma.setId(rs.getInt(1));
                turma.setTurno(Turno.valueOf(rs.getString(3)));
                turma.setModulo(Modulo.valueOf(rs.getString(4)));
                turma.setDiaSemana(Semana.valueOf(rs.getString(5)));
                turma.setAno(Integer.parseInt(rs.getString(7)));
                turma.setSemestre(Integer.parseInt(rs.getString(8)));
            }
            return turma;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private DomainEntity atualizarAlunosByTurma(DomainEntity entidade) {
        Turma turma = (Turma) entidade;
        String consulta = "SELECT ID_ALUNO, NOME_ALUNO, CPF_ALUNO, MAE_ALUNO, EMAIL_ALUNO FROM alunos alu "
                + "join alunos_turmas AluTur on alu.ID_ALUNO = AluTur.ID_ALUNO_AT "
                + "join turmas tur on tur.ID_TURMA=AluTur.ID_TURMA_AT "
                + "join materias mat on mat.ID_MATERIA=tur.ID_MATERIA" + " where TUR.ID_TURMA = ?";
        try {
            Connection connection = new Conection().conectar();
            PreparedStatement pst = connection.prepareStatement(consulta);
            pst.setInt(1, turma.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String nome = rs.getString(2);
                String CPF = rs.getString(3);
                String nome_mae = rs.getString(4);
                String email = rs.getString(5);
                Aluno aluno = new Aluno(nome, CPF, nome_mae, email);
                aluno.setId(id);
                turma.setAlunos(aluno);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private DomainEntity atualizarMateriaByTurma(DomainEntity entidade) {
        Turma turma = (Turma) entidade;
        String consulta = "SELECT NOME_MATERIA, DESCRICAO_MATERIA, CARGA_HORARIA_MATERIA, mat.ID_MATERIA "
                + "FROM materias mat  join turmas tur on mat.ID_MATERIA = tur.ID_MATERIA "
                + "where ID_TURMA = ? ";
        try {
            Connection connection = new Conection().conectar();
            PreparedStatement pst = connection.prepareStatement(consulta);
            pst.setInt(1, turma.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String nome=rs.getString(1);
                String Descricao=rs.getString(2);
                int cargaHoraria=rs.getInt(3);
                Integer id = rs.getInt(4);
                Materia materia = new Materia(nome, cargaHoraria, Descricao);
                materia.setId(id);
                turma.setMateria(materia);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
    