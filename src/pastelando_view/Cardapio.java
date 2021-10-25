/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pastelando_view;

import controller.CardapioController;
import dao.CardapioDao;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import tableModel.BebidaTableModel;
import model.CardapioModel;
import tableModel.ComplementoTableModel;
import tableModel.PastelDoceTableModel;
import tableModel.RecheioTableModel;
import tableModel.RecheioValorTableModel;
import tableModel.TipoPastelTableModel;

/**
 *
 * @author gsa38
 */
public final class Cardapio extends javax.swing.JFrame {

    private static final Cardapio cardapio = new Cardapio();
    private int auxOperacao = 0;
    private int auxTabela = 0;
    private int i;
    private TipoPastelTableModel tpTableModel;
    private PastelDoceTableModel pdTableModel;
    private BebidaTableModel bTableModel;
    private RecheioValorTableModel rTableModel;
    private ComplementoTableModel cTableModel;
    private CardapioModel tp = new CardapioModel();
    private CardapioModel b = new CardapioModel();
    private CardapioModel pd = new CardapioModel();
    private CardapioModel r = new CardapioModel();
    private CardapioModel c = new CardapioModel();
    private CardapioDao cardapioDao = new CardapioDao();
    List<CardapioModel> listaTp;
    List<CardapioModel> listaPd;
    List<CardapioModel> listaB;
    List<CardapioModel> listaC;
    List<CardapioModel> listaR;

    public void lerTabelaTipoPastel() throws SQLException {
        listaTp = cardapioDao.exibirTipoPastel();
        tpTableModel = new TipoPastelTableModel(listaTp);
        tipoPastelTable.setModel(tpTableModel);
    }

    public void lerTabelaPastelDoce() throws SQLException {
        listaPd = cardapioDao.exibirPastelDoce();
        pdTableModel = new PastelDoceTableModel(listaPd);
        pastelDoceTable.setModel(pdTableModel);
    }

    public void lerTabelaBebida() throws SQLException {
        listaB = cardapioDao.exibirBebida();
        bTableModel = new BebidaTableModel(listaB);
        bebidasTable.setModel(bTableModel);
    }

    public void lerTabelaComplemento() throws SQLException {
        listaC = cardapioDao.exibirComplementos();
        cTableModel = new ComplementoTableModel(listaC);
        complementosTable.setModel(cTableModel);
    }

    public void lerTabelaRecheio() throws SQLException {
        listaR = cardapioDao.exibirRecheios();
        rTableModel = new RecheioValorTableModel(listaR);
        recheiosTable.setModel(rTableModel);
    }

    public void selectOnlyTp() {
        bebidasTable.clearSelection();
        pastelDoceTable.clearSelection();
        complementosTable.clearSelection();
        recheiosTable.clearSelection();
    }

    public void selectOnlyPd() {
        bebidasTable.clearSelection();
        tipoPastelTable.clearSelection();
        complementosTable.clearSelection();
        recheiosTable.clearSelection();
    }

    public void selectOnlyB() {
        tipoPastelTable.clearSelection();
        pastelDoceTable.clearSelection();
        complementosTable.clearSelection();
        recheiosTable.clearSelection();
    }

    public void selectOnlyC() {
        bebidasTable.clearSelection();
        tipoPastelTable.clearSelection();
        pastelDoceTable.clearSelection();
        recheiosTable.clearSelection();
    }

    public void selectOnlyR() {
        bebidasTable.clearSelection();
        tipoPastelTable.clearSelection();
        pastelDoceTable.clearSelection();
        complementosTable.clearSelection();
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public static Cardapio getCardapio() {
        return cardapio;
    }

    public int getAuxOperacao() {
        return auxOperacao;
    }

    public void setAuxOperacao(int auxOperacao) {
        this.auxOperacao = auxOperacao;
    }

    public int getAuxTabela() {
        return auxTabela;
    }

    public void setAuxTabela(int auxTabela) {
        this.auxTabela = auxTabela;
    }

    public void hideTamanho() {
        sizeCatLabel.setVisible(false);
        sizeCatText.setVisible(false);
    }

    public void hideTamanhoValor() {
        sizeCatLabel.setVisible(false);
        sizeCatText.setVisible(false);
        valorLabel.setVisible(false);
        valorText.setVisible(false);
    }

    public void showTamanho() {
        sizeCatLabel.setVisible(true);
        sizeCatText.setVisible(true);
    }

    public void showValor() {
        valorLabel.setVisible(true);
        valorText.setVisible(true);
    }

    public void cleanText() {
        saborText.setText("");
        valorText.setText("");
        sizeCatText.setText("");
    }

    public void canSelectTable(boolean opc) {
        pastelDoceTable.setRowSelectionAllowed(opc);

        tipoPastelTable.setRowSelectionAllowed(opc);

        complementosTable.setRowSelectionAllowed(opc);

        recheiosTable.setRowSelectionAllowed(opc);

        bebidasTable.setRowSelectionAllowed(opc);

    }

    public void clearAllTableSelection() {
        bebidasTable.clearSelection();
        pastelDoceTable.clearSelection();
        complementosTable.clearSelection();
        recheiosTable.clearSelection();
        tipoPastelTable.clearSelection();
        setAuxTabela(0);
    }

    public void insertButtonText(String txt) {
        confirmarButton.setText(txt);
    }

    public Cardapio() {
        initComponents();
            Toolkit toolkit = getToolkit();
            Dimension size = toolkit.getScreenSize();
            setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        confirmarButton.setVisible(false);
        try {
            lerTabelaTipoPastel();
            lerTabelaPastelDoce();
            lerTabelaBebida();
            lerTabelaRecheio();
            lerTabelaComplemento();
        } catch (SQLException ex) {
            Logger.getLogger(Cardapio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloPagina = new javax.swing.JLabel();
        tabelaPanel = new javax.swing.JPanel();
        jScrollPaneTp = new javax.swing.JScrollPane();
        tipoPastelTable = new javax.swing.JTable();
        jScrollPaneB = new javax.swing.JScrollPane();
        bebidasTable = new javax.swing.JTable();
        jScrollPaneC = new javax.swing.JScrollPane();
        complementosTable = new javax.swing.JTable();
        jScrollPanePd = new javax.swing.JScrollPane();
        pastelDoceTable = new javax.swing.JTable();
        jScrollPaneR = new javax.swing.JScrollPane();
        recheiosTable = new javax.swing.JTable();
        inserirButton = new javax.swing.JButton();
        ExcluirButton = new javax.swing.JButton();
        atualizarButton = new javax.swing.JButton();
        saborLabel = new javax.swing.JLabel();
        valorLabel = new javax.swing.JLabel();
        sizeCatLabel = new javax.swing.JLabel();
        saborText = new javax.swing.JTextField();
        valorText = new javax.swing.JTextField();
        sizeCatText = new javax.swing.JTextField();
        confirmarButton = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cardapio");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/pastelando_imagem/Logo_loginJP.jpg")
        );
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tituloPagina.setFont(new java.awt.Font("Dialog", 2, 48)); // NOI18N
        tituloPagina.setForeground(new java.awt.Color(117, 17, 217));
        tituloPagina.setText("CARDAPIO PASTELANDO");
        getContentPane().add(tituloPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 590, 80));

        tabelaPanel.setBackground(new java.awt.Color(18, 18, 225));
        tabelaPanel.setOpaque(false);
        tabelaPanel.setPreferredSize(new java.awt.Dimension(420, 519));

        tipoPastelTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tipoPastelTable.getTableHeader().setReorderingAllowed(false);
        tipoPastelTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipoPastelTableMouseClicked(evt);
            }
        });
        jScrollPaneTp.setViewportView(tipoPastelTable);

        bebidasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        bebidasTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bebidasTableMouseClicked(evt);
            }
        });
        jScrollPaneB.setViewportView(bebidasTable);

        complementosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        complementosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                complementosTableMouseClicked(evt);
            }
        });
        jScrollPaneC.setViewportView(complementosTable);

        pastelDoceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        pastelDoceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pastelDoceTableMouseClicked(evt);
            }
        });
        jScrollPanePd.setViewportView(pastelDoceTable);

        recheiosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        recheiosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recheiosTableMouseClicked(evt);
            }
        });
        jScrollPaneR.setViewportView(recheiosTable);

        inserirButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        inserirButton.setText("Inserir");
        inserirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirButtonActionPerformed(evt);
            }
        });

        ExcluirButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        ExcluirButton.setText("Excluir");
        ExcluirButton.setIconTextGap(3);
        ExcluirButton.setMaximumSize(new java.awt.Dimension(70, 25));
        ExcluirButton.setMinimumSize(new java.awt.Dimension(70, 25));
        ExcluirButton.setPreferredSize(new java.awt.Dimension(70, 25));
        ExcluirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirButtonActionPerformed(evt);
            }
        });

        atualizarButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        atualizarButton.setText("Atualizar");
        atualizarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarButtonActionPerformed(evt);
            }
        });

        saborLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        saborLabel.setForeground(new java.awt.Color(255, 255, 255));
        saborLabel.setText("Sabor:");

        valorLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        valorLabel.setForeground(new java.awt.Color(255, 255, 255));
        valorLabel.setText("Valor:");

        sizeCatLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        sizeCatLabel.setForeground(new java.awt.Color(255, 255, 255));
        sizeCatLabel.setText("Tamanho:");

        saborText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saborTextActionPerformed(evt);
            }
        });

        valorText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorTextActionPerformed(evt);
            }
        });

        confirmarButton.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        confirmarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabelaPanelLayout = new javax.swing.GroupLayout(tabelaPanel);
        tabelaPanel.setLayout(tabelaPanelLayout);
        tabelaPanelLayout.setHorizontalGroup(
            tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabelaPanelLayout.createSequentialGroup()
                .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabelaPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sizeCatLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sizeCatText, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabelaPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(inserirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ExcluirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(atualizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPaneTp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPaneB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(tabelaPanelLayout.createSequentialGroup()
                                .addComponent(saborLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tabelaPanelLayout.createSequentialGroup()
                                        .addComponent(valorLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(valorText, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(saborText, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))))))
                .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabelaPanelLayout.createSequentialGroup()
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabelaPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPaneC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPaneR, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabelaPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(jScrollPanePd, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabelaPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(confirmarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
        );
        tabelaPanelLayout.setVerticalGroup(
            tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabelaPanelLayout.createSequentialGroup()
                .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabelaPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPanePd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPaneTp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneR, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPaneC, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPaneB, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabelaPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(atualizarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ExcluirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inserirButton)))
                .addGap(18, 18, 18)
                .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabelaPanelLayout.createSequentialGroup()
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saborLabel)
                            .addComponent(saborText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sizeCatLabel)
                            .addComponent(sizeCatText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valorLabel)
                            .addComponent(valorText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(confirmarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );

        getContentPane().add(tabelaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 940, 610));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pastelando_imagem/backgroung_Pequeno.jpg"))); // NOI18N
        background.setOpaque(true);
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void valorTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorTextActionPerformed

    private void confirmarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarButtonActionPerformed
      
        try {
            switch (auxTabela) {
                //tipo pastel
                case 1:
                    switch (auxOperacao) {
                        //add
                        case 1:
                            tp.setSabor_m(saborText.getText());
                            tp.setValor_tp(Double.parseDouble(valorText.getText()));
                            tp.setCategoria(Integer.parseInt(sizeCatText.getText()));
                            cardapioDao.inserirTP(tp);
                            lerTabelaTipoPastel();
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione a Tabela");

                            break;

                        //excluir    
                        case 2:
                            setI(tipoPastelTable.getSelectedRow());
                            tp = cardapioDao.exibirTipoPastel().get(i);
                            cardapioDao.removerTP(tp);
                            tpTableModel.removeRow(tipoPastelTable.getSelectedRow());
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione o Item");
                            break;

                        //atualizar    
                        case 3:
                            setI(tipoPastelTable.getSelectedRow());
                            tp = cardapioDao.exibirTipoPastel().get(i);
                            tp.setSabor_m(saborText.getText());
                            tp.setValor_tp(Double.parseDouble(valorText.getText()));
                            tp.setCategoria(Integer.parseInt(sizeCatText.getText()));
                            cardapioDao.atualizarTP(tp);
                            lerTabelaTipoPastel();
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione o Item");
                            break;

                    }
                    break;
                //pastel doce
                case 2:
                    switch (auxOperacao) {
                        //add
                        case 1:
                            pd.setSabor_pd(saborText.getText());
                            pd.setValor_pd(Double.parseDouble(valorText.getText()));
                            cardapioDao.inserirPD(pd);
                            lerTabelaPastelDoce();
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione a Tabela");
                            break;

                        //excluir
                        case 2:
                            setI(pastelDoceTable.getSelectedRow());
                            pd = cardapioDao.exibirPastelDoce().get(i);
                            cardapioDao.removerPD(pd);
                            pdTableModel.removeRow(pastelDoceTable.getSelectedRow());
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione o Item");
                            break;

                        //atualizar
                        case 3:
                            setI(pastelDoceTable.getSelectedRow());
                            pd = cardapioDao.exibirPastelDoce().get(i);
                            pd.setSabor_pd(saborText.getText());
                            pd.setValor_pd(Double.parseDouble(valorText.getText()));
                            cardapioDao.atualizarPD(pd);
                            lerTabelaPastelDoce();
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione o Item");
                            break;
                    }
                    break;
                //bebida    
                case 3:
                    switch (auxOperacao) {
                        //add
                        case 1:
                            b.setTipo_b(saborText.getText());
                            b.setValor_refri(Double.parseDouble(valorText.getText()));
                            b.setTamanho(Double.parseDouble(sizeCatText.getText()));
                            cardapioDao.inserirB(b);
                            lerTabelaBebida();
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione a Tabela");
                            break;

                        //excluir
                        case 2:
                            setI(bebidasTable.getSelectedRow());
                            b = cardapioDao.exibirBebida().get(i);
                            cardapioDao.removerB(b);
                            lerTabelaBebida();
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione o Item");
                            break;

                        //atualizar
                        case 3:
                            setI(bebidasTable.getSelectedRow());
                            b = cardapioDao.exibirBebida().get(i);
                            b.setTipo_b(saborText.getText());
                            b.setValor_refri(Double.parseDouble(valorText.getText()));
                            b.setTamanho(Double.parseDouble(sizeCatText.getText()));
                            cardapioDao.atualizarB(b);
                            lerTabelaBebida();
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione o Item");
                            break;

                    }
                    break;
                //complemento    
                case 4:
                    switch (auxOperacao) {
                        //add
                        case 1:
                            c.setSabor_c(saborText.getText());
                            cardapioDao.inserirC(c);
                            lerTabelaComplemento();
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione a Tabela");
                            break;

                        //excluir
                        case 2:
                            setI(complementosTable.getSelectedRow());
                            c = cardapioDao.exibirComplementos().get(i);
                            cardapioDao.removerC(c);
                            cTableModel.removeRow(complementosTable.getSelectedRow());
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione o Item");
                            break;

                        //atualizar
                        case 3:
                            setI(complementosTable.getSelectedRow());
                            c = cardapioDao.exibirComplementos().get(i);
                            c.setSabor_c(saborText.getText());
                            cardapioDao.atualizarC(c);
                            lerTabelaComplemento();
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione o Item");
                            break;

                    }
                    break;
                //recheio    
                case 5:
                    switch (auxOperacao) {
                        //add
                        case 1:
                            r.setSabor_r(saborText.getText());
                            r.setValor_r(Double.parseDouble(valorText.getText()));
                            cardapioDao.inserirR(r);
                            lerTabelaRecheio();
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione a Tabela");
                            break;

                        //excluir
                        case 2:
                            setI(recheiosTable.getSelectedRow());
                            r = cardapioDao.exibirRecheios().get(i);
                            cardapioDao.removerR(r);
                            rTableModel.removeRow(recheiosTable.getSelectedRow());
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione o Item");
                            break;

                        //atualizar
                        case 3:
                            setI(recheiosTable.getSelectedRow());
                            r = cardapioDao.exibirRecheios().get(i);
                            r.setSabor_r(saborText.getText());
                            r.setValor_r(Double.parseDouble(valorText.getText()));
                            cardapioDao.atualizarR(r);
                            lerTabelaRecheio();
                            cleanText();
                            clearAllTableSelection();
                            insertButtonText("Selecione o Item");
                            break;

                    }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Cardapio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_confirmarButtonActionPerformed

    private void inserirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirButtonActionPerformed
        canSelectTable(false);
        clearAllTableSelection();
        cleanText();
        insertButtonText("Selecione a Tabela");
        confirmarButton.setVisible(true);
        setAuxOperacao(1);
    }//GEN-LAST:event_inserirButtonActionPerformed

    private void ExcluirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirButtonActionPerformed
        canSelectTable(true);
        clearAllTableSelection();
        cleanText();
        insertButtonText("Selecione o Item");
        confirmarButton.setVisible(true);
        setAuxOperacao(2);
    }//GEN-LAST:event_ExcluirButtonActionPerformed

    private void atualizarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarButtonActionPerformed
        canSelectTable(true);
        clearAllTableSelection();
        cleanText();
        insertButtonText("Selecione o Item");
        confirmarButton.setVisible(true);
        setAuxOperacao(3);
    }//GEN-LAST:event_atualizarButtonActionPerformed

    private void tipoPastelTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipoPastelTableMouseClicked
        selectOnlyTp();
        sizeCatLabel.setText("Categoria");
        showTamanho();
        showValor();
        setAuxTabela(1);
        if (this.auxOperacao != 1) {
            int linha = tipoPastelTable.getSelectedRow();
            saborText.setText(tpTableModel.getValueAt(linha, 0).toString());
            valorText.setText(tpTableModel.getValueAt(linha, 1).toString());
            sizeCatText.setText(tpTableModel.getValueAt(linha, 2).toString());
            if (this.auxOperacao == 2) {
                insertButtonText("Excluir Tipo de Pastel");
            } else {
                insertButtonText("Atualizar Tipo de Pastel");
            }
        } else {
            insertButtonText("Inserir Tipo de Pastel");
        }
    }//GEN-LAST:event_tipoPastelTableMouseClicked

    private void pastelDoceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pastelDoceTableMouseClicked
        selectOnlyPd();
        hideTamanho();
        showValor();
        setAuxTabela(2);
        if (this.auxOperacao != 1) {
            int linha = pastelDoceTable.getSelectedRow();
            saborText.setText(pdTableModel.getValueAt(linha, 0).toString());
            valorText.setText(pdTableModel.getValueAt(linha, 1).toString());
            if (this.auxOperacao == 2) {
                insertButtonText("Excluir Pastel Doce");
            } else {
                insertButtonText("Atualizar Pastel Doce");
            }
        } else {
            insertButtonText("Inserir Pastel Doce");
        }
    }//GEN-LAST:event_pastelDoceTableMouseClicked

    private void bebidasTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bebidasTableMouseClicked
        selectOnlyB();
        showTamanho();
        sizeCatLabel.setText("Tamanho");
        showValor();
        setAuxTabela(3);
        if (this.auxOperacao != 1) {
            int linha = bebidasTable.getSelectedRow();
            saborText.setText(bTableModel.getValueAt(linha, 0).toString());
            valorText.setText(bTableModel.getValueAt(linha, 1).toString());
            sizeCatText.setText(bTableModel.getValueAt(linha, 2).toString());
            if (this.auxOperacao == 2) {
                insertButtonText("Excluir Bebida");
            } else {
                insertButtonText("Atualizar Bebida");
            }
        } else {
            insertButtonText("Inserir Bebida");
        }
    }//GEN-LAST:event_bebidasTableMouseClicked

    private void complementosTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_complementosTableMouseClicked
        selectOnlyC();
        hideTamanhoValor();
        setAuxTabela(4);
        if (this.auxOperacao != 1) {
            int linha = complementosTable.getSelectedRow();
            saborText.setText(cTableModel.getValueAt(linha, 0).toString());
            if (this.auxOperacao == 2) {
                insertButtonText("Excluir Complemento");
            } else {
                insertButtonText("Atualizar Complemento");
            }
        } else {
            insertButtonText("Inserir Complemento");
        }
    }//GEN-LAST:event_complementosTableMouseClicked

    private void recheiosTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recheiosTableMouseClicked
        selectOnlyR();
        hideTamanho();
        showValor();
        setAuxTabela(5);
        if (this.auxOperacao != 1) {
            int linha = recheiosTable.getSelectedRow();
            saborText.setText(rTableModel.getValueAt(linha, 0).toString());
            valorText.setText(rTableModel.getValueAt(linha, 1).toString());
            if (this.auxOperacao == 2) {
                insertButtonText("Excluir Recheio");
            } else {
                insertButtonText("Atualizar Recheio");
            }
        } else {
            insertButtonText("Inserir Recheio");
        }
    }//GEN-LAST:event_recheiosTableMouseClicked

    private void saborTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saborTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saborTextActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cardapio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExcluirButton;
    private javax.swing.JButton atualizarButton;
    private javax.swing.JLabel background;
    private javax.swing.JTable bebidasTable;
    private javax.swing.JTable complementosTable;
    private javax.swing.JButton confirmarButton;
    private javax.swing.JButton inserirButton;
    private javax.swing.JScrollPane jScrollPaneB;
    private javax.swing.JScrollPane jScrollPaneC;
    private javax.swing.JScrollPane jScrollPanePd;
    private javax.swing.JScrollPane jScrollPaneR;
    private javax.swing.JScrollPane jScrollPaneTp;
    private javax.swing.JTable pastelDoceTable;
    private javax.swing.JTable recheiosTable;
    private javax.swing.JLabel saborLabel;
    private javax.swing.JTextField saborText;
    private javax.swing.JLabel sizeCatLabel;
    private javax.swing.JTextField sizeCatText;
    private javax.swing.JPanel tabelaPanel;
    private javax.swing.JTable tipoPastelTable;
    private javax.swing.JLabel tituloPagina;
    private javax.swing.JLabel valorLabel;
    private javax.swing.JTextField valorText;
    // End of variables declaration//GEN-END:variables
}
