/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gsa38
 */
//cria conexao com o bd
public class Conexao {
    public static Connection getConnection() throws SQLException{
        Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Pastelando2.0","postgres","kelso200");
        //Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5433/pastelando","postgres","tadsmads123");
        //Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5433/pastelando","postgres","pasteland0rj");
        return conexao;
    }
}
