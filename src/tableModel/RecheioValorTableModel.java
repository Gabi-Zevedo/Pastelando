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
public class RecheioValorTableModel extends AbstractTableModel{
     private List<CardapioModel> recheios;
    private String[] colunas = {"RECHEIOS","VALOR"};
    
    public RecheioValorTableModel(List<CardapioModel> recheios){
        this.recheios = new ArrayList<>(recheios);
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return recheios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return recheios.get(linha).getSabor_r();
            case 1:
                return recheios.get(linha).getValor_r();
        }
        return null;
    }

    @Override
    public void setValueAt(Object rValor, int linha, int coluna) {
        switch(coluna){
            case 0:
                recheios.get(linha).setSabor_r((String) rValor);
                break;
            case 1:
                recheios.get(linha).setValor_r((Double.parseDouble((String)rValor)));
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }
    
    public void addRow(CardapioModel tm){
        this.recheios.add(tm);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int linha){
        this.recheios.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
}


