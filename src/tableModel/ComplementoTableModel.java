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
public class ComplementoTableModel extends AbstractTableModel{
    private List<CardapioModel> complementos;
    private String[] colunas = {"COMPLEMENTOS"};

    public ComplementoTableModel(List<CardapioModel> complementos){
        this.complementos = new ArrayList<>(complementos);
    }

    @Override
    public String getColumnName(int column) {
        return this.colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return this.complementos.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return this.complementos.get(linha).getSabor_c();
        }
        return null;
    }

    @Override
    public void setValueAt(Object cValor, int linha, int coluna) {
        switch(coluna){
            case 0:
                this.complementos.get(linha).setSabor_c((String) cValor);
        }
        this.fireTableRowsUpdated(linha, linha);
    }
    
    public void addRow(CardapioModel tm){
        this.complementos.add(tm);
        this.fireTableDataChanged();
    }
    public void removeRow(int linha){
        this.complementos.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
}
