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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ClienteModel;

/**
 *
 * @author gsa38
 */
public class ClienteDao {
    public List<ClienteModel> exibirCliente() throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultset = null;

        List<ClienteModel> cliente = new ArrayList<>();
        try {
            statement = con.prepareStatement("select * from cliente order by id_cliente");
            resultset = statement.executeQuery();

            while (resultset.next()) {
                ClienteModel cm = new ClienteModel();
                cm.setId_cliente(resultset.getInt("id_cliente"));
                cm.setNome(resultset.getString("nome"));
                cm.setTelefone(resultset.getString("telefone"));
                cm.setBairro(resultset.getString("bairro"));
                cm.setRua(resultset.getString("rua"));
                cm.setNumero_casa(resultset.getString("numero_casa"));
                cm.setComplemento(resultset.getString("complemento"));
                cm.setReferencia(resultset.getString("referencia"));
                cliente.add(cm);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a tabela." + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            statement.close();
            resultset.close();
        }
        return cliente;
    }
    
    
        public List<ClienteModel> filtrarClienteTel(String telefone) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultset = null;

        List<ClienteModel> cliente = new ArrayList<>();
        try {
            statement = con.prepareStatement("select * from cliente where telefone like ? ");
            statement.setString(1, telefone+"%");
            resultset = statement.executeQuery();

            while (resultset.next()) {
                ClienteModel cm = new ClienteModel();
                cm.setId_cliente(resultset.getInt("id_cliente"));
                cm.setNome(resultset.getString("nome"));
                cm.setTelefone(resultset.getString("telefone"));
                cm.setBairro(resultset.getString("bairro"));
                cm.setRua(resultset.getString("rua"));
                cm.setNumero_casa(resultset.getString("numero_casa"));
                cm.setComplemento(resultset.getString("complemento"));
                cm.setReferencia(resultset.getString("referencia"));
                cliente.add(cm);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a tabela." + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            statement.close();
            resultset.close();
        }
        return cliente;
    }
        public List<ClienteModel> filtrarClienteNome(String nome) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultset = null;

        List<ClienteModel> cliente = new ArrayList<>();
        try {
            statement = con.prepareStatement("select * from cliente where upper(nome) like upper(?) ");
            statement.setString(1, nome+"%");
            resultset = statement.executeQuery();

            while (resultset.next()) {
                ClienteModel cm = new ClienteModel();
                cm.setId_cliente(resultset.getInt("id_cliente"));
                cm.setNome(resultset.getString("nome"));
                cm.setTelefone(resultset.getString("telefone"));
                cm.setBairro(resultset.getString("bairro"));
                cm.setRua(resultset.getString("rua"));
                cm.setNumero_casa(resultset.getString("numero_casa"));
                cm.setComplemento(resultset.getString("complemento"));
                cm.setReferencia(resultset.getString("referencia"));
                cliente.add(cm);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a tabela." + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            statement.close();
            resultset.close();
        }
        return cliente;
    }
            public List<ClienteModel> dadosClienteNota(Integer id) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultset = null;

        List<ClienteModel> cliente = new ArrayList<>();
        try {
            statement = con.prepareStatement("select * from cliente where id_cliente = ? ");
            statement.setInt(1, id);
            resultset = statement.executeQuery();

            while (resultset.next()) {
                ClienteModel cm = new ClienteModel();
                cm.setId_cliente(resultset.getInt("id_cliente"));
                cm.setNome(resultset.getString("nome"));
                cm.setTelefone(resultset.getString("telefone"));
                cm.setBairro(resultset.getString("bairro"));
                cm.setRua(resultset.getString("rua"));
                cm.setNumero_casa(resultset.getString("numero_casa"));
                cm.setComplemento(resultset.getString("complemento"));
                cm.setReferencia(resultset.getString("referencia"));
                cliente.add(cm);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a tabela." + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            statement.close();
            resultset.close();
        }
        return cliente;
    }
    public void inserirC(ClienteModel clientemodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("insert into cliente(nome, telefone, bairro, rua, numero_casa, complemento,referencia) values(?,?,?,?,?,?,?)");
            pstm.setString(1, clientemodel.getNome());
            pstm.setString(2, clientemodel.getTelefone());
            pstm.setString(3, clientemodel.getBairro());
            pstm.setString(4, clientemodel.getRua());
            pstm.setString(5, clientemodel.getNumero_casa());
            pstm.setString(6, clientemodel.getComplemento());
            pstm.setString(7, clientemodel.getReferencia());
            pstm.executeUpdate();
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao salvar cliente." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     public void removerC(ClienteModel clientemodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("delete from cliente where nome=? and telefone=? and bairro=? and rua=? and numero_casa=? and complemento=? and Referencia=?");
            pstm.setString(1, clientemodel.getNome());
            pstm.setString(2, clientemodel.getTelefone());
            pstm.setString(3, clientemodel.getBairro());
            pstm.setString(4, clientemodel.getRua());
            pstm.setString(5, clientemodel.getNumero_casa());
            pstm.setString(6, clientemodel.getComplemento());
            pstm.setString(7, clientemodel.getReferencia());
            pstm.executeUpdate();
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Exclusão efetuada.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao excluir do banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void atualizarC(ClienteModel clientemodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("update cliente set nome=?, telefone=?, bairro=?, rua=?, numero_casa=?, complemento=?, referencia=? where id_cliente=?");
            pstm.setString(1, clientemodel.getNome());
            pstm.setString(2, clientemodel.getTelefone());
            pstm.setString(3, clientemodel.getBairro());
            pstm.setString(4, clientemodel.getRua());
            pstm.setString(5, clientemodel.getNumero_casa());
            pstm.setString(6, clientemodel.getComplemento());
            pstm.setString(7, clientemodel.getReferencia());
            pstm.setInt(8, clientemodel.getId_cliente());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao atulizar no banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
