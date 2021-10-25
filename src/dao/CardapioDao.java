/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CardapioModel;
import pastelando_view.Cardapio;

/**
 *
 * @author gsa38
 */
public class CardapioDao {

    public List<CardapioModel> exibirTipoPastel() throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultset = null;

        List<CardapioModel> tipoPastel = new ArrayList<>();
        try {
            statement = con.prepareStatement("select * from tipo_pastel order by id_tipo");
            resultset = statement.executeQuery();

            while (resultset.next()) {
                CardapioModel tp = new CardapioModel();
                tp.setSabor_m(resultset.getString("sabor_m"));
                tp.setValor_tp(resultset.getDouble("valor_tp"));
                tp.setId_tipo(resultset.getInt("id_tipo"));
                tp.setCategoria(resultset.getInt("categoria"));
                tipoPastel.add(tp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a tabela." + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            statement.close();
            resultset.close();
        }
        return tipoPastel;
    }

    public List<CardapioModel> exibirPastelDoce() throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultset = null;

        List<CardapioModel> pastelDoce = new ArrayList<>();
        try {
            statement = con.prepareStatement("select * from pastel_doce");
            resultset = statement.executeQuery();

            while (resultset.next()) {
                CardapioModel pd = new CardapioModel();
                pd.setSabor_pd(resultset.getString("sabor_pd"));
                pd.setValor_pd(resultset.getDouble("valor_pd"));
                pd.setId_pd(resultset.getInt("id_pd"));
                pastelDoce.add(pd);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a tabela." + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            statement.close();
            resultset.close();
        }
        return pastelDoce;
    }

    public List<CardapioModel> exibirBebida() throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet resultset = null;

        List<CardapioModel> bebida = new ArrayList<>();
        try {
            statement = con.prepareStatement("select * from bebida order by id_bebida");
            resultset = statement.executeQuery();

            while (resultset.next()) {
                CardapioModel b = new CardapioModel();
                b.setTipo_b(resultset.getString("tipo_b"));
                b.setValor_refri(resultset.getDouble("valor_refri"));
                b.setTamanho(resultset.getDouble("tamanho"));
                b.setId_bebida(resultset.getInt("id_bebida"));
                bebida.add(b);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a tabela." + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            statement.close();
            resultset.close();
        }
        return bebida;
    }

    public List<CardapioModel> exibirRecheios() throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<CardapioModel> recheios = new ArrayList<>();

        try {
            pstm = con.prepareStatement("select * from recheio order by id_recheio");
            rs = pstm.executeQuery();

            while (rs.next()) {
                CardapioModel recheio = new CardapioModel();
                recheio.setSabor_r(rs.getString("sabor_r"));
                recheio.setId_recheio(rs.getInt("id_recheio"));
                recheio.setValor_r(rs.getDouble("valor_r"));
                recheios.add(recheio);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dado do banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            pstm.close();
            rs.close();
        }
        return recheios;
    }

    public List<CardapioModel> exibirComplementos() throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<CardapioModel> complementos = new ArrayList<>();

        try {
            pstm = con.prepareStatement("select * from complemento order by id_complemento");
            rs = pstm.executeQuery();

            while (rs.next()) {
                CardapioModel complemento = new CardapioModel();
                complemento.setSabor_c(rs.getString("sabor_c"));
                complemento.setId_complemento(rs.getInt("id_complemento"));
                complementos.add(complemento);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dado do banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            pstm.close();
            rs.close();
        }
        return complementos;
    }
     public void removerTP(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("delete from tipo_pastel where sabor_m=? and valor_tp=?");
            pstm.setString(1, cardapiomodel.getSabor_m());
            pstm.setDouble(2, cardapiomodel.getValor_tp());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Exclusão efetuada.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao excluir do banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void inserirTP(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("insert into tipo_pastel(sabor_m, valor_tp,categoria) values(?,?,?)");
            pstm.setString(1, cardapiomodel.getSabor_m());
            pstm.setDouble(2, cardapiomodel.getValor_tp());
            pstm.setInt(3, cardapiomodel.getCategoria());
            pstm.executeUpdate();
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Inserção efetuada.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void atualizarTP(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("update tipo_pastel set sabor_m=?, valor_tp=?, categoria=? where id_tipo=?");
            pstm.setString(1, cardapiomodel.getSabor_m());
            pstm.setDouble(2, cardapiomodel.getValor_tp());
            pstm.setInt(3, cardapiomodel.getCategoria());
            pstm.setInt(4, cardapiomodel.getId_tipo());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao atulizar no banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removerPD(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("delete from pastel_doce where sabor_pd=? and valor_pd=?");
            pstm.setString(1, cardapiomodel.getSabor_pd());
            pstm.setDouble(2, cardapiomodel.getValor_pd());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Exclusão efetuada.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao excluir do banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void inserirPD(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("insert into pastel_doce(sabor_pd, valor_pd) values(?,?)");
            pstm.setString(1, cardapiomodel.getSabor_pd());
            pstm.setDouble(2, cardapiomodel.getValor_pd());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Inserção efetuada.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void atualizarPD(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("update pastel_doce set sabor_pd=?, valor_pd=? where id_pd=?");
            pstm.setString(1, cardapiomodel.getSabor_pd());
            pstm.setDouble(2, cardapiomodel.getValor_pd());
            pstm.setInt(3, cardapiomodel.getId_pd());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao atulizar no banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public void removerB(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("delete from bebida where tipo_b=? and valor_refri=? and tamanho=?");
            pstm.setString(1, cardapiomodel.getTipo_b());
            pstm.setDouble(2, cardapiomodel.getValor_refri());
            pstm.setDouble(3, cardapiomodel.getTamanho());
            pstm.executeUpdate();
           
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Exclusão efetuada.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao excluir do banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void inserirB(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("insert into bebida(tipo_b, valor_refri, tamanho) values(?,?,?)");
            pstm.setString(1, cardapiomodel.getTipo_b());
            pstm.setDouble(2, cardapiomodel.getValor_refri());
            pstm.setDouble(3, cardapiomodel.getTamanho());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Inserção efetuada.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void atualizarB(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("update bebida set tipo_b=?, valor_refri=?, tamanho=? where id_bebida=?");
            pstm.setString(1, cardapiomodel.getTipo_b());
            pstm.setDouble(2, cardapiomodel.getValor_refri());
            pstm.setDouble(3, cardapiomodel.getTamanho());
            pstm.setInt(4, cardapiomodel.getId_bebida());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao atulizar no banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removerR(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("delete from recheio where sabor_r=?");
            pstm.setString(1, cardapiomodel.getSabor_r());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Exclusão efetuada.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao excluir do banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void inserirR(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("insert into recheio(sabor_r,valor_r) values(?,?)");
            pstm.setString(1, cardapiomodel.getSabor_r());
            pstm.setDouble(2, cardapiomodel.getValor_r());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Inserção efetuada.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void atualizarR(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("update recheio set sabor_r=?,valor_r=? where id_recheio=?");
            pstm.setString(1, cardapiomodel.getSabor_r());
            pstm.setDouble(2, cardapiomodel.getValor_r());
            pstm.setInt(3, cardapiomodel.getId_recheio());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao atulizar no banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removerC(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("delete from complemento where sabor_c=?");
            pstm.setString(1, cardapiomodel.getSabor_c());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Exclusão efetuada.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao excluir do banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void inserirC(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("insert into complemento(sabor_c) values(?)");
            pstm.setString(1, cardapiomodel.getSabor_c());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Inserção efetuada.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void atualizarC(CardapioModel cardapiomodel) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try
        {
            pstm = con.prepareStatement("update complemento set sabor_c=? where id_complemento=?");
            pstm.setString(1, cardapiomodel.getSabor_c());
            pstm.setInt(2, cardapiomodel.getId_complemento());
            pstm.executeUpdate();
            
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso.");
        }catch(SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Erro ao atulizar no banco." +e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
