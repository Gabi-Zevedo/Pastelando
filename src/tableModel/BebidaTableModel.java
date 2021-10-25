/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.CardapioModel;

/**
 *
 * @author gsa38
 */
public class BebidaTableModel extends AbstractTableModel {
    private List<CardapioModel> dados; 
    private String[] colunas = {"BEBIDA", "VALOR","TAMANHO"};
    
    public BebidaTableModel(List<CardapioModel> dados){
        this.dados = new ArrayList<>(dados);
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getTipo_b();
            case 1:
                return dados.get(linha).getValor_refri();
            case 2: 
                return dados.get(linha).getTamanho();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setTipo_b((String)valor);
                break;
            case 1:
                dados.get(linha).setValor_refri(Double.parseDouble((String)valor));
                break;
            case 2:
                dados.get(linha).setTamanho(Double.parseDouble((String)valor));
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addRow(CardapioModel tm) {
        this.dados.add(tm);
        this.fireTableDataChanged();
    }
                
    public void removeRow(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    
}
