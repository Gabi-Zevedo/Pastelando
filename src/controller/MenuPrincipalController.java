/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import pastelando_view.Cardapio;
import pastelando_view.Cliente;
import pastelando_view.MenuPrincipal;
import pastelando_view.NovoPedido;

/**
 *
 * @author gsa38
 */
public class MenuPrincipalController {
    private final MenuPrincipal view;
    
    public MenuPrincipalController(MenuPrincipal view){
        this.view = view;
    }
    
    public void abrirNovoPedido(){
        NovoPedido telapedido = NovoPedido.getNovoPedido();
        telapedido.setVisible(true);
    }
    
    public void abrirCardapio(){
        Cardapio cardapio = Cardapio.getCardapio();
        cardapio.setVisible(true);
    }
    public void abrirCliente(){
        Cliente cliente = Cliente.getCliente();
        cliente.setVisible(true);
    }
}
