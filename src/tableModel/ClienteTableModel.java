/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.ClienteModel;

/**
 *
 * @author gsa38
 */
public class ClienteTableModel extends AbstractTableModel{
    private List<ClienteModel> cliente;
    private String[] colunas = {"Nome","Telefone","Bairro","Rua","Número","Complemento","Referencia"};

    public ClienteTableModel(List<ClienteModel> cliente){
        this.cliente = new ArrayList<>(cliente);
    }

    @Override
    public String getColumnName(int column) {
        return this.colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return this.cliente.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return this.cliente.get(linha).getNome();
            case 1:
                return this.cliente.get(linha).getTelefone();
            case 2:
                return this.cliente.get(linha).getBairro();
            case 3:
                return this.cliente.get(linha).getRua();
            case 4:
                return this.cliente.get(linha).getNumero_casa();
            case 5:
                return this.cliente.get(linha).getComplemento();
            case 6:
                return this.cliente.get(linha).getReferencia();
        }
        return null;
    }

    @Override
    public void setValueAt(Object cValor, int linha, int coluna) {
        switch(coluna){
            case 0:
                this.cliente.get(linha).setNome((String) cValor);
            case 1:
                this.cliente.get(linha).setTelefone((String) cValor);
            case 2:
                this.cliente.get(linha).setBairro((String) cValor);
            case 3:
                this.cliente.get(linha).setRua((String) cValor);
            case 4:
                this.cliente.get(linha).setNumero_casa((String) cValor);
            case 5:
                this.cliente.get(linha).setComplemento((String) cValor);
            case 6:
                this.cliente.get(linha).setReferencia((String) cValor);
                }
        this.fireTableRowsUpdated(linha, linha);
    }
    
    public void addRow(ClienteModel tm){
        this.cliente.add(tm);
        this.fireTableDataChanged();
    }
    public void removeRow(int linha){
        this.cliente.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
}
