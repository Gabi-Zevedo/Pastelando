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
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.CardapioModel;
import model.ClienteModel;
import model.PastelModel;
import model.PedidoModel;

/**
 *
 * @author gsa38
 */
public class PedidoDao {

    public List<PedidoModel> exibirPedidos() throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<PedidoModel> pedido = new ArrayList<>();

        try {
            pstm = con.prepareStatement("select * from pedido order by id_pedido");
            rs = pstm.executeQuery();

            while (rs.next()) {
                PedidoModel p = new PedidoModel();
                p.setCliente_fk(rs.getInt("cliente_fk"));
                p.setPagamento_fk(rs.getInt("pagamento_fk"));
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setTaxa_entrega(rs.getDouble("taxa_entrega"));
                p.setValor_total(rs.getDouble("valor_total"));
                p.setObs(rs.getString("obs"));
                p.setData_pedido(rs.getString("data_pedido"));
                pedido.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dado do banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            pstm.close();
            rs.close();
        }
        return pedido;
    }

    public List<PedidoModel> exibirUltimoPedido() throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<PedidoModel> pedido = new ArrayList<>();

        try {
            pstm = con.prepareStatement("select * from pedido order by id_pedido desc limit 1");
            rs = pstm.executeQuery();

            while (rs.next()) {
                PedidoModel p = new PedidoModel();
                p.setCliente_fk(rs.getInt("cliente_fk"));
                p.setPagamento_fk(rs.getInt("pagamento_fk"));
                p.setId_pedido(rs.getInt("id_pedido"));
                p.setTaxa_entrega(rs.getDouble("taxa_entrega"));
                p.setValor_total(rs.getDouble("valor_total"));
                p.setObs(rs.getString("obs"));
                p.setData_pedido(rs.getString("data_pedido"));
                pedido.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dado do banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            pstm.close();
            rs.close();
        }
        return pedido;
    }

    public void abrirPedido(ClienteModel clientemodel) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement("insert into pedido(cliente_fk) values(?)");
            pstm.setInt(1, clientemodel.getId_cliente());
            pstm.executeUpdate();
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Pedido aberto com sucesso!.");
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void fecharPedido(String obs, int pagamento, double taxa, double valorTotal, PedidoModel pedidomodel, String data) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement("update pedido set taxa_entrega = ?, valor_total = ?, pagamento_fk=?, obs=?, data_pedido=?  where id_pedido=?");
            pstm.setDouble(1, taxa);
            pstm.setDouble(2, valorTotal);
            pstm.setInt(3, pagamento);
            pstm.setString(4, pedidomodel.getObs());
            pstm.setString(5, data);
            pstm.setInt(6, pedidomodel.getId_pedido());
            pstm.executeUpdate();
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Pedido salvo com sucesso!.");
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Erro ao concluir pedido." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void inserirBebidaPedido(PedidoModel pedidomodel) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement("insert into bebida_pedido(bebida_fk,pedido_fk) values(?,?)");
            pstm.setInt(1, pedidomodel.getId_bp());
            pstm.setInt(2, pedidomodel.getId_pedido());
            pstm.executeUpdate();
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Bebida Adicionada.");
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public List<PastelModel> selecionarPastel() throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<PastelModel> pastel = new ArrayList<>();

        try {
            pstm = con.prepareStatement("select id_pastel from pastel order by id_pastel desc limit 1");
            rs = pstm.executeQuery();

            while (rs.next()) {

                PastelModel p = new PastelModel();
                p.setId_pastel(rs.getInt("id_pastel"));
                pastel.add(p);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dado do banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            pstm.close();
            rs.close();
        }
        return pastel;

    }

    public List<PastelModel> notaPastel(Integer id) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<PastelModel> pastel = new ArrayList<>();

        try {
            pstm = con.prepareStatement("select pastel_fk from pastel_pedido where pedido_fk = ?");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {

                PastelModel p = new PastelModel();
                p.setId_pastel(rs.getInt("pastel_fk"));

                pastel.add(p);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dado do banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            pstm.close();
            rs.close();
        }
        return pastel;

    }

    public Double notaPastelValor(Integer id) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        double valor = 0;
        List<PastelModel> pastel = new ArrayList<>();

        try {
            pstm = con.prepareStatement("select valor_r from recheio inner join pastel_recheio on recheio.id_recheio = pastel_recheio.recheio_fk where pastel_recheio.pastel_fk = ?;");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {

                PastelModel p = new PastelModel();
                p.setValor_r(rs.getInt("valor_r"));
                pastel.add(p);

            }
            
            
            pstm.close();
            rs.close();
            
            pstm = con.prepareStatement("select valor_tp from tipo_pastel inner join pastel on tipo_pastel.id_tipo = pastel.tipo_pastel_fk where pastel.id_pastel = ?;");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {

                PastelModel p = new PastelModel();
                p.setValor_tp(rs.getInt("valor_tp"));
                pastel.add(p);

            }
            
            
           
            pstm.close();
            rs.close();
            pstm = con.prepareStatement("select valor_pd from pastel_doce inner join pastel on pastel_doce.id_pd = pastel.pastel_doce_fk where pastel.id_pastel = ?;");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {

                PastelModel p = new PastelModel();
                p.setValor_pd(rs.getInt("valor_pd"));
                pastel.add(p);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dado do banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            pstm.close();
            rs.close();
        }
        for (int i = 0; i<pastel.size();i++){
            valor+= pastel.get(i).getValor_pd() +  pastel.get(i).getValor_r() +  pastel.get(i).getValor_tp();
        }
        return valor;

    }

    public List<CardapioModel> notaBebida(Integer id) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<CardapioModel> bebida = new ArrayList<>();

        try {
            pstm = con.prepareStatement("select bebida_fk from bebida_pedido where pedido_fk = ?");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {

                CardapioModel c = new CardapioModel();
                c.setId_bebida(rs.getInt("bebida_fk"));
                bebida.add(c);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dado do banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            pstm.close();
            rs.close();
        }
        return bebida;

    }

    public Double notaBebidaValor(Integer id) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<CardapioModel> bebida = new ArrayList<>();

        try {
            pstm = con.prepareStatement("select valor_refri from bebida where id_bebida = ?");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {

                CardapioModel c = new CardapioModel();
                c.setValor_refri(rs.getInt("valor_refri"));
                bebida.add(c);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dado do banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.close();
            pstm.close();
            rs.close();
        }
        double valor = 0;
        for(int i = 0; i<bebida.size();i++){
            valor+=bebida.get(i).getValor_refri();
        }
        return valor;

    }
    public void inserirPastelPedido(PastelModel pastelmodel) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement("insert into pastel_pedido(pastel_fk,pedido_fk) values(?,?)");
            pstm.setInt(1, pastelmodel.getId_pastel());
            pstm.setInt(2, pastelmodel.getPedido_fk());
            pstm.executeUpdate();
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Pastel salvo com sucesso!.");
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void inserirTipoPastel(PastelModel pastelmodel) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            if (pastelmodel.getPastel_doce_fk() == 0) {
                pstm = con.prepareStatement("insert into pastel(pastel_doce_fk,tipo_pastel_fk) values(null,?)");
                pstm.setInt(1, pastelmodel.getTipo_pastel_fk());
            } else {
                pstm = con.prepareStatement("insert into pastel(pastel_doce_fk,tipo_pastel_fk) values(?,?)");
                pstm.setInt(1, pastelmodel.getPastel_doce_fk());
                pstm.setInt(2, pastelmodel.getTipo_pastel_fk());
            }
            pstm.executeUpdate();
            con.close();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void inserirRecheioPastel(PastelModel pastelmodel) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement("insert into pastel_recheio(pastel_fk,recheio_fk) values(?,?)");
            pstm.setInt(1, pastelmodel.getId_pastel());
            pstm.setInt(2, pastelmodel.getRecheio_fk());
            pstm.executeUpdate();
            con.close();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void inserirComplementoPastel(PastelModel pastelmodel) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement("insert into pastel_complemento(pastel_fk,complemento_fk) values(?,?)");
            pstm.setInt(1, pastelmodel.getId_pastel());
            pstm.setInt(2, pastelmodel.getComplemento_fk());
            pstm.executeUpdate();
            con.close();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void excluirPastelPedido(List<PastelModel> pastel) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            for (int i = 0; i < pastel.size(); i++) {
                pstm = con.prepareStatement("delete from pastel where id_pastel = ?");
                pstm.setInt(1, pastel.get(i).getId_pastel());
                pstm.executeUpdate();
            }
            con.close();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Erro ao excluir do banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cancelarPedido(PedidoModel pedidomodel) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement pstm = null;
        PreparedStatement excluirPastel = null;
        PreparedStatement excluirBebida = null;
        List<PastelModel> pastel = new ArrayList<>();
        ResultSet prs = null;
        try {
            excluirPastel = con.prepareStatement("select * from pastel_pedido where pedido_fk = ?");
            excluirPastel.setInt(1, pedidomodel.getId_pedido());
            prs = excluirPastel.executeQuery();
            while (prs.next()) {
                PastelModel p = new PastelModel();
                p.setId_pastel(prs.getInt("pastel_fk"));
                pastel.add(p);
            }
            excluirPastel.close();

            excluirBebida = con.prepareCall("delete from bebida_pedido where pedido_fk = ?");
            excluirBebida.setInt(1, pedidomodel.getId_pedido());
            excluirBebida.executeUpdate();
            excluirBebida.close();

            if (!pastel.isEmpty()) {
                excluirPastelPedido(pastel);
            }

            pstm = con.prepareStatement("delete from pedido where id_pedido = ?");
            pstm.setInt(1, pedidomodel.getId_pedido());
            pstm.executeUpdate();
            con.close();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Pedido cancelado.");
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Erro ao excluir do banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
