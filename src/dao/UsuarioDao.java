/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UsuarioModel;


/**
 *
 * @author gsa38
 */
public class UsuarioDao {
    private final Connection connection; 

    public UsuarioDao(Connection connection) {
        this.connection = connection;
    }
    //caso necessite de inserir um novo usuario
    public void insert(UsuarioModel usuario) throws SQLException{
            String sql = "insert into login(usuario,senha) values('"+usuario.getUsuario()+"','"+usuario.getSenha()+"')";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            connection.close();
        }
    //busca no banco e comparar com os dados digitados para confirmar o login
    public boolean existeUsuario(UsuarioModel usuario) throws SQLException{
            String sql = "select * from login where usuario = ? and senha = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());
            statement.execute();
            ResultSet resultset = statement.getResultSet();
            return resultset.next();
    }
    }

