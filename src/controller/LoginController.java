/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.Conexao;
import dao.UsuarioDao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.UsuarioModel;
import pastelando_view.Login;
import pastelando_view.MenuPrincipal;

/**
 *
 * @author gsa38
 */
public class LoginController {
    private final Login view;

    public LoginController(Login view) {
        this.view = view;
    }
    public void autenticar() throws SQLException{
        //buscar usuario
        String usuario = view.getTextUsuario().getText();
        String senha = view.getPasswordSenhaField().getText();
        
        //recebe e autentica(verifica no bd)
        UsuarioModel usuarioAutenticar = new UsuarioModel(usuario, senha);
        Connection conexao = new Conexao().getConnection();
        UsuarioDao usuarioDao = new UsuarioDao(conexao);
        boolean existe = usuarioDao.existeUsuario(usuarioAutenticar);
            if(existe){
        MenuPrincipal telamenu = new MenuPrincipal();
        telamenu.setVisible(true);
        this.view.dispose();
        }
            else{
                JOptionPane.showMessageDialog(view, "Usuário ou Senhas Inválidos");
           }
    }
}
