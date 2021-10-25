/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pastelando_view;

import dao.ClienteDao;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClienteModel;
import tableModel.ClienteTableModel;

/**
 *
 * @author gsa38
 */
public class Cliente extends javax.swing.JFrame {

    private static final Cliente cliente = new Cliente();
    private ClienteTableModel cTableModel;
    private ClienteModel cm = new ClienteModel();
    private ClienteDao clienteDao = new ClienteDao();
    List<ClienteModel> listaC;
    int auxOperacao, linha;

    public static Cliente getCliente() {
        return cliente;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getAuxOperacao() {
        return auxOperacao;
    }

    public void setAuxOperacao(int auxOperacao) {
        this.auxOperacao = auxOperacao;
    }

    public void cleanText() {
        nomeTextField.setText("");
        telefoneTextField.setText("");
        bairroTextField.setText("");
        ruaTextField.setText("");
        numeroTextField.setText("");
        complementoTextField.setText("");
        referenciaTextField.setText("");
    }

    public void insertButtonText(String txt) {
        confirmarButton.setText(txt);
    }

    public void lerTabelaCliente() throws SQLException {
        listaC = clienteDao.exibirCliente();
        cTableModel = new ClienteTableModel(listaC);
        clienteTable.setModel(cTableModel);
    }

    public void lerTabelaClienteFiltradaTel() throws SQLException {
        listaC = clienteDao.filtrarClienteTel(buscarTelTextField.getText());
        cTableModel = new ClienteTableModel(listaC);
        clienteTable.setModel(cTableModel);
    }

    public void lerTabelaClienteFiltradaNome() throws SQLException {
        listaC = clienteDao.filtrarClienteNome(buscarNomeTextField.getText());
        cTableModel = new ClienteTableModel(listaC);
        clienteTable.setModel(cTableModel);
    }

    public Cliente() {
        try {
            initComponents();
            Toolkit toolkit = getToolkit();
            Dimension size = toolkit.getScreenSize();
            setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
            this.auxOperacao = 1;
            lerTabelaCliente();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
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

        tabelaPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clienteTable = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        confirmarButton = new javax.swing.JButton();
        inserirButton = new javax.swing.JButton();
        atualizarButton = new javax.swing.JButton();
        excluirButton = new javax.swing.JButton();
        buscarTelTextField = new javax.swing.JTextField();
        buscarTelButton = new javax.swing.JButton();
        buscarNomeTextField = new javax.swing.JTextField();
        buscarNomeButton = new javax.swing.JButton();
        nomeLabel = new javax.swing.JLabel();
        telefoneLabel = new javax.swing.JLabel();
        bairroLabel = new javax.swing.JLabel();
        ruaLabel = new javax.swing.JLabel();
        numeroLabel = new javax.swing.JLabel();
        complementoLabel = new javax.swing.JLabel();
        nomeTextField = new javax.swing.JTextField();
        telefoneTextField = new javax.swing.JTextField();
        bairroTextField = new javax.swing.JTextField();
        ruaTextField = new javax.swing.JTextField();
        numeroTextField = new javax.swing.JTextField();
        complementoTextField = new javax.swing.JTextField();
        referenciaLabel = new javax.swing.JLabel();
        referenciaTextField = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/pastelando_imagem/Logo_loginJP.jpg")
        );
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelaPanel.setOpaque(false);

        clienteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        clienteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(clienteTable);

        buttonPanel.setOpaque(false);

        confirmarButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        confirmarButton.setText("Confirmar Inserção");
        confirmarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarButtonActionPerformed(evt);
            }
        });

        inserirButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inserirButton.setText("Inserir");
        inserirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirButtonActionPerformed(evt);
            }
        });

        atualizarButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        atualizarButton.setText("Atualizar");
        atualizarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarButtonActionPerformed(evt);
            }
        });

        excluirButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        excluirButton.setText("Excluir");
        excluirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirButtonActionPerformed(evt);
            }
        });

        buscarTelTextField.setText("Digite o Telefone");
        buscarTelTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarTelTextFieldMouseClicked(evt);
            }
        });

        buscarTelButton.setText("Filtrar Telefone");
        buscarTelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarTelButtonActionPerformed(evt);
            }
        });

        buscarNomeTextField.setText("Digite o Nome");
        buscarNomeTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarNomeTextFieldMouseClicked(evt);
            }
        });

        buscarNomeButton.setText("Filtrar Nome");
        buscarNomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarNomeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addComponent(inserirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                                .addComponent(atualizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(excluirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(buscarTelTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(buscarNomeTextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buscarTelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buscarNomeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarTelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarTelButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarNomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarNomeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inserirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atualizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(excluirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(confirmarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        nomeLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        nomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        nomeLabel.setText("Nome:");

        telefoneLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        telefoneLabel.setForeground(new java.awt.Color(255, 255, 255));
        telefoneLabel.setText("Telefone:");

        bairroLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        bairroLabel.setForeground(new java.awt.Color(255, 255, 255));
        bairroLabel.setText("Bairro:");

        ruaLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        ruaLabel.setForeground(new java.awt.Color(255, 255, 255));
        ruaLabel.setText("Rua:");

        numeroLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        numeroLabel.setForeground(new java.awt.Color(255, 255, 255));
        numeroLabel.setText("Número");

        complementoLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        complementoLabel.setForeground(new java.awt.Color(255, 255, 255));
        complementoLabel.setText("Complemento:");

        referenciaLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        referenciaLabel.setForeground(new java.awt.Color(255, 255, 255));
        referenciaLabel.setText("Referencia");

        javax.swing.GroupLayout tabelaPanelLayout = new javax.swing.GroupLayout(tabelaPanel);
        tabelaPanel.setLayout(tabelaPanelLayout);
        tabelaPanelLayout.setHorizontalGroup(
            tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabelaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                    .addGroup(tabelaPanelLayout.createSequentialGroup()
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tabelaPanelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(telefoneLabel)
                                    .addComponent(nomeLabel)
                                    .addComponent(referenciaLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(telefoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(referenciaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tabelaPanelLayout.createSequentialGroup()
                                .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(numeroLabel)
                                    .addComponent(complementoLabel)
                                    .addComponent(ruaLabel)
                                    .addComponent(bairroLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tabelaPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(complementoTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(numeroTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ruaTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(tabelaPanelLayout.createSequentialGroup()
                                        .addComponent(bairroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabelaPanelLayout.setVerticalGroup(
            tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabelaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabelaPanelLayout.createSequentialGroup()
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telefoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefoneLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bairroLabel)
                            .addComponent(bairroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ruaLabel)
                            .addComponent(ruaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numeroLabel)
                            .addComponent(numeroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(complementoLabel)
                            .addComponent(complementoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabelaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(referenciaLabel)
                            .addComponent(referenciaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        getContentPane().add(tabelaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 730));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pastelando_imagem/backgroung_Pequeno.jpg"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clienteTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteTableMouseClicked
        if (auxOperacao != 1) {
            setLinha(clienteTable.getSelectedRow());
            nomeTextField.setText(cTableModel.getValueAt(linha, 0).toString());
            telefoneTextField.setText(cTableModel.getValueAt(linha, 1).toString());
            bairroTextField.setText(cTableModel.getValueAt(linha, 2).toString());
            ruaTextField.setText(cTableModel.getValueAt(linha, 3).toString());
            numeroTextField.setText(cTableModel.getValueAt(linha, 4).toString());
            if (cTableModel.getValueAt(linha, 5) == null) {
                complementoTextField.setText("");
            } else {
                complementoTextField.setText(cTableModel.getValueAt(linha, 5).toString());
            }

            if (cTableModel.getValueAt(linha, 6) == null) {
                referenciaTextField.setText("");
            } else {
                referenciaTextField.setText(cTableModel.getValueAt(linha, 6).toString());
            }
            confirmarButton.setEnabled(true);
        }
        if (auxOperacao == 2) {
            insertButtonText("Confirmar Atualização");
        }
        if (auxOperacao == 3) {
            insertButtonText("Confirmar Exclusão");
        }
    }//GEN-LAST:event_clienteTableMouseClicked

    private void inserirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirButtonActionPerformed
        if (auxOperacao == 2 || auxOperacao == 3) {
            cleanText();
        }
        clienteTable.clearSelection();
        confirmarButton.setEnabled(true);
        insertButtonText("Confirmar Inserção");
        setAuxOperacao(1);
    }//GEN-LAST:event_inserirButtonActionPerformed

    private void atualizarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarButtonActionPerformed
        clienteTable.clearSelection();
        confirmarButton.setEnabled(true);
        cleanText();
        insertButtonText("Selecione o Cliente");
        setAuxOperacao(2);
    }//GEN-LAST:event_atualizarButtonActionPerformed

    private void excluirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirButtonActionPerformed
        clienteTable.clearSelection();
        confirmarButton.setEnabled(true);
        cleanText();
        insertButtonText("Selecione o Cliente");
        setAuxOperacao(3);
    }//GEN-LAST:event_excluirButtonActionPerformed

    private void confirmarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarButtonActionPerformed
        try {
            if (auxOperacao == 1) {
                cm.setNome(nomeTextField.getText());
                cm.setTelefone(telefoneTextField.getText());
                cm.setBairro(bairroTextField.getText());
                cm.setRua(ruaTextField.getText());
                cm.setNumero_casa(numeroTextField.getText());
                cm.setComplemento(complementoTextField.getText());
                cm.setReferencia(referenciaTextField.getText());
                clienteDao.inserirC(cm);
            }

            if (auxOperacao == 2) {
                setLinha(clienteTable.getSelectedRow());
                cm = clienteDao.exibirCliente().get(linha);
                cm.setNome(nomeTextField.getText());
                cm.setTelefone(telefoneTextField.getText());
                cm.setBairro(bairroTextField.getText());
                cm.setRua(ruaTextField.getText());
                cm.setNumero_casa(numeroTextField.getText());
                cm.setComplemento(complementoTextField.getText());
                cm.setReferencia(referenciaTextField.getText());
                clienteDao.atualizarC(cm);
                confirmarButton.setEnabled(false);
            }

            if (auxOperacao == 3) {
                setLinha(clienteTable.getSelectedRow());
                cm = clienteDao.exibirCliente().get(linha);
                clienteDao.removerC(cm);
                confirmarButton.setEnabled(false);
            }
            lerTabelaCliente();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        cleanText();
        clienteTable.clearSelection();
    }//GEN-LAST:event_confirmarButtonActionPerformed

    private void buscarTelTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarTelTextFieldMouseClicked
        buscarTelTextField.setText("");
    }//GEN-LAST:event_buscarTelTextFieldMouseClicked

    private void buscarTelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarTelButtonActionPerformed
        try {
            lerTabelaClienteFiltradaTel();
            buscarTelTextField.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buscarTelButtonActionPerformed

    private void buscarNomeTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarNomeTextFieldMouseClicked
        buscarNomeTextField.setText("");
    }//GEN-LAST:event_buscarNomeTextFieldMouseClicked

    private void buscarNomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarNomeButtonActionPerformed
        try {
            lerTabelaClienteFiltradaNome();
            buscarNomeTextField.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buscarNomeButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarButton;
    private javax.swing.JLabel background;
    private javax.swing.JLabel bairroLabel;
    private javax.swing.JTextField bairroTextField;
    private javax.swing.JButton buscarNomeButton;
    private javax.swing.JTextField buscarNomeTextField;
    private javax.swing.JButton buscarTelButton;
    private javax.swing.JTextField buscarTelTextField;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTable clienteTable;
    private javax.swing.JLabel complementoLabel;
    private javax.swing.JTextField complementoTextField;
    private javax.swing.JButton confirmarButton;
    private javax.swing.JButton excluirButton;
    private javax.swing.JButton inserirButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JLabel numeroLabel;
    private javax.swing.JTextField numeroTextField;
    private javax.swing.JLabel referenciaLabel;
    private javax.swing.JTextField referenciaTextField;
    private javax.swing.JLabel ruaLabel;
    private javax.swing.JTextField ruaTextField;
    private javax.swing.JPanel tabelaPanel;
    private javax.swing.JLabel telefoneLabel;
    private javax.swing.JTextField telefoneTextField;
    // End of variables declaration//GEN-END:variables
}