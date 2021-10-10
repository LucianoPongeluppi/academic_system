package com.system_academic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.system_academic.domain.DomainEntity;
import com.system_academic.domain.Materia;
import com.system_academic.util.Conection;

public class MateriaDao extends AbstractDao {
    @Override
    public void create(DomainEntity entidade) {
        Materia materia = (Materia) entidade;
        String create = "insert into materias (nome_materia, descricao_materia,carga_horaria_materia) values (?,?,?)";
        Connection connection = new Conection().conectar();
        try {
            PreparedStatement pst = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, materia.getNome());
            pst.setString(2, materia.getDescricao());
            pst.setInt(3, materia.getCargaHoraria());
            pst.executeUpdate();
            System.out.println("Query Executada - Materia");
            ResultSet rs = pst.getGeneratedKeys();
            Integer id = null;
            if (rs != null && rs.next())
                id = rs.getInt(1);
            materia.setId(id);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DomainEntity entidade) {
        System.out.println("Conectado para alterar - materia");
        Materia materia = (Materia) entidade;
        PreparedStatement pst = null;

        try {
            Connection connection = new Conection().conectar();
            String alterar = "UPDATE materias SET nome_materia = ?, descricao_materia = ?,carga_horaria_materia = ? WHERE id_MATERIA = ?";
            pst = connection.prepareStatement(alterar, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, materia.getNome());
            pst.setString(2, materia.getDescricao());
            pst.setInt(3, materia.getCargaHoraria());
            pst.setInt(4, materia.getId());
            pst.executeUpdate();
            connection.close();
            System.out.println("UPDATE CONCLUIDO NO DAO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(DomainEntity entidade) {
        System.out.println("Conectado para excluir - materia");
        Materia materia = (Materia) entidade;
        PreparedStatement pst = null;
        try {
            Connection connection = new Conection().conectar();
            String delete = "DELETE FROM materias WHERE id_materia = ?";
            pst = connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, materia.getId());
            pst.executeUpdate();
            connection.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DomainEntity> read(DomainEntity entidade) {
        Connection connection = new Conection().conectar();
        System.out.println("Conectado para consulta - materia");
        ArrayList<DomainEntity> listaMateria = new ArrayList<>();
        String read = "select * from materias order by id_materia";

        try {
            PreparedStatement pst = connection.prepareStatement(read);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String nome=rs.getString(3);
                String Descricao=rs.getString(4);
                int cargaHoraria=rs.getInt(5);                             
                Materia materiaBD = new Materia(nome,cargaHoraria,Descricao);
                materiaBD.setId(id);
                listaMateria.add(materiaBD);
            }
            connection.close();
            return listaMateria;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

public DomainEntity select(DomainEntity entidade) {
        System.out.println("Entrou no comando de selecionar - materia - dao");
        String read2 = "select * from materias where id_materia =?";
        Materia materia = (Materia) entidade;
        try {
            Connection connection = new Conection().conectar();
            PreparedStatement pst = connection.prepareStatement(read2);
            pst.setInt(1, entidade.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                materia.setId(rs.getInt(1));
                materia.setNome(rs.getString(3));
                materia.setDescricao(rs.getString(4));
                materia.setCargaHoraria(rs.getInt(5));
            }
            return materia;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}