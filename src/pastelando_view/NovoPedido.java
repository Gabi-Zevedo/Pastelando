/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pastelando_view;

import controller.DataController;
import dao.CardapioDao;
import dao.ClienteDao;
import dao.PedidoDao;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import tableModel.BebidaTableModel;
import model.CardapioModel;
import model.ClienteModel;
import model.PastelModel;
import model.PedidoModel;
import tableModel.ClienteTableModel;
import tableModel.ComplementoTableModel;
import tableModel.PastelDoceTableModel;
import tableModel.RecheioTableModel;

/**
 *
 * @author gsa38
 */
public class NovoPedido extends javax.swing.JFrame {

    private static final NovoPedido novopedido = new NovoPedido();
    private DataController controller;
    private ClienteTableModel cTableModel;
    private ClienteModel cm = new ClienteModel();
    private ClienteModel cma = new ClienteModel();
    private PedidoModel p = new PedidoModel();
    private ClienteDao clienteDao = new ClienteDao();
    private CardapioDao cardapioDao = new CardapioDao();
    private PedidoDao pedidoDao = new PedidoDao();
    private RecheioTableModel rTableModel;
    private ComplementoTableModel compTableModel;
    private PastelDoceTableModel pdTableModel;
    private BebidaTableModel bTableModel;
    private CardapioModel r = new CardapioModel();
    private CardapioModel comp = new CardapioModel();
    private CardapioModel pd = new CardapioModel();
    private CardapioModel tp = new CardapioModel();
    private CardapioModel b = new CardapioModel();
    private PastelModel pModel = new PastelModel();
    List<ClienteModel> listaC;
    List<CardapioModel> listaB;
    List<CardapioModel> listaComp;
    List<CardapioModel> listaR;
    List<CardapioModel> listaPd;
    List<PedidoModel> listaPedido;
    List<PastelModel> listaPm = new ArrayList<>();
    List<PastelModel> listaPv = new ArrayList<>();
    List<PastelModel> listaPastel = new ArrayList<>();
    List<PastelModel> listaPastelValor = new ArrayList<>();
    int contr = 1, contc = 1, contb = 1, contp = 1, contpd = 1;
    int auxEditar = 0;
    boolean editavel = false;
    int id_tp_fk;
    int pagamento;
    int tipoPastel;
    int categoria;
    int idEditado;
    double valorTotal;
    double valorTp;
    double valorB;
    boolean pastelFeito = true;
    boolean pedidoAberto = false;
    double valorpastel;
    double valorbebida;
    boolean filtradaCli = false;
    boolean filtradaTel = false;

    public static NovoPedido getNovoPedido() {
        return novopedido;
    }

    public double getValorpastel() {
        return valorpastel;
    }

    public void setValorpastel(double valorpastel) {
        this.valorpastel = valorpastel;
    }

    public double getValorbebida() {
        return valorbebida;
    }

    public void setValorbebida(double valorbebida) {
        this.valorbebida = valorbebida;
    }

    public double getValorB() {
        return valorB;
    }

    public void setValorB(double valorB) {
        this.valorB = valorB;
    }

    public double getValorTp() {
        return valorTp;
    }

    public void setValorTp(double valorTp) {
        this.valorTp = valorTp;
    }

    public boolean isPedidoAberto() {
        return pedidoAberto;
    }

    public void setPedidoAberto(boolean pedidoAberto) {
        this.pedidoAberto = pedidoAberto;
    }

    public int getContp() {
        return contp;
    }

    public void setContp(int contp) {
        this.contp = contp;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public boolean isPastelFeito() {
        return pastelFeito;
    }

    public void setPastelFeito(boolean pastelFeito) {
        this.pastelFeito = pastelFeito;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getTipoPastel() {
        return tipoPastel;
    }

    public int getIdEditado() {
        return idEditado;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public void setIdEditado(int idEditado) {
        this.idEditado = idEditado;
    }

    public void setTipoPastel(int tipoPastel) {
        this.tipoPastel = tipoPastel;
    }

    public int getContb() {
        return contb;
    }

    public int getId_tp_fk() {
        return id_tp_fk;
    }

    public void setId_tp_fk(int id_tp_fk) {
        this.id_tp_fk = id_tp_fk;
    }

    public boolean isEditavel() {
        return editavel;
    }

    public void setEditavel(boolean editavel) {
        this.editavel = editavel;
    }

    public void setContb(int contb) {
        this.contb = contb;
    }

    public int getPagamento() {
        return pagamento;
    }

    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
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

    public void cleanOpcText() {
        opcaoTextField1.setText("");
        opcaoTextField2.setText("");
        opcaoTextField3.setText("");
        opcaoTextField4.setText("");
        opcaoTextField5.setText("");
        opcaoTextField6.setText("");
    }

    public void lerTabelaPastelDoce() throws SQLException {
        listaPd = cardapioDao.exibirPastelDoce();
        pdTableModel = new PastelDoceTableModel(listaPd);
        opcaoTable.setModel(pdTableModel);
    }

    public void lerTabelaComplemento() throws SQLException {
        listaComp = cardapioDao.exibirComplementos();
        compTableModel = new ComplementoTableModel(listaComp);
        opcaoTable.setModel(compTableModel);
    }

    public void lerTabelaBebida() throws SQLException {
        listaB = cardapioDao.exibirBebida();
        bTableModel = new BebidaTableModel(listaB);
        bebidasTable.setModel(bTableModel);
    }

    public void lerTabelaRecheio() throws SQLException {
        listaR = cardapioDao.exibirRecheios();
        rTableModel = new RecheioTableModel(listaR);
        opcaoTable.setModel(rTableModel);
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

    public void opcaoLabelName(String nome1, String nome2, String nome3) {
        opcaoLabel1.setText(nome1);
        opcaoLabel2.setText(nome2);
        opcaoLabel3.setText(nome3);
    }

    public void radioState(boolean r1, boolean r2) {
        recheioRadio.setVisible(r1);
        complementoRadio.setVisible(r2);
    }

    public void labelETesteState(boolean r1, boolean r2, boolean r3, boolean r4, boolean r5, boolean r6) {
        opcaoLabel1.setVisible(r1);
        opcaoLabel2.setVisible(r2);
        opcaoLabel3.setVisible(r3);
        opcaoLabel4.setVisible(r4);
        opcaoLabel5.setVisible(r5);
        opcaoLabel6.setVisible(r6);
        opcaoTextField1.setVisible(r1);
        opcaoTextField2.setVisible(r2);
        opcaoTextField3.setVisible(r3);
        opcaoTextField4.setVisible(r4);
        opcaoTextField5.setVisible(r5);
        opcaoTextField6.setVisible(r6);
    }

    public void textoEditavel(boolean x) {
        nomeTextField.setEditable(x);
        telefoneTextField.setEditable(x);
        bairroTextField.setEditable(x);
        ruaTextField.setEditable(x);
        numeroTextField.setEditable(x);
        complementoTextField.setEditable(x);
    }

    public void menuMontagem(int c) {
        try {
            if (c == 1) {
                opcaoTable.setVisible(true);
                opcaoLabelName("Recheio 1:", "Recheio 2:", "Recheio 3:");
                if (recheioRadio.isSelected()) {
                    lerTabelaRecheio();
                } else {
                    lerTabelaComplemento();
                }
                radioState(true, true);
                labelETesteState(true, true, true, true, true, true);
            }
            if (c == 2) {
                opcaoTable.setVisible(true);
                cleanOpcText();
                lerTabelaPastelDoce();
                radioState(false, false);
                opcaoLabelName("Sabor:", "", "");
                labelETesteState(true, false, false, false, false, false);

            }
            if (c == 3) {
                opcaoTable.setVisible(false);
                radioState(false, false);
                opcaoLabelName("", "", "");
                labelETesteState(false, false, false, false, false, false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean dadosPreenchidos() {
        int flag = 0;
        if (!opcaoTextField1.getText().isEmpty() && flag == 0) {
            flag = 1;
        }

        if (!opcaoTextField2.getText().isEmpty() && flag == 0) {
            flag = 1;
        }

        if (!opcaoTextField3.getText().isEmpty() && flag == 0) {
            flag = 1;
        }

        if (!opcaoTextField4.getText().isEmpty() && flag == 0) {
            flag = 1;
        }

        if (!opcaoTextField5.getText().isEmpty() && flag == 0) {
            flag = 1;
        }

        if (!opcaoTextField6.getText().isEmpty() && flag == 0) {
            flag = 1;
        }
        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void resetarPedido() {
        cleanOpcText();
        cleanText();
        setContb(1);
        setContp(1);
        contc = 1;
        contr = 1;
        salvarClienteButton.setEnabled(true);
        editarRecheioComplementoButton.setEnabled(false);
        salvarBebidaPedido.setEnabled(false);
        salvarPastelButton.setEnabled(false);
        addBebidaExtraButton.setEnabled(false);
        concluirPedidoButton.setEnabled(false);
        verImpressaoButton.setEnabled(false);
        cancelarPedidoButton.setEnabled(false);
        addPastelExtraButton.setVisible(false);
        valorRecebidoTextField.setText("0");
        valorTotalTextField.setText("0");
        valorAdicionalTextField.setText("0");
        taxaTextField.setText("0");
        saborTextField.setText("");
        tamanhoTextField.setText("");
        bebidaCountTextField.setText("Bebida " + getContb());
        pastelCountLabel.setText("Pastel " + getContb());
        setValorTotal(0);
        setPastelFeito(true);
        setPedidoAberto(false);
        buscarButton.setEnabled(true);
        tipoPastelComboBox.setEnabled(false);
        obsTextAreaLabel.setText("");
    }

    public void valorPastelNota() {
        try {
            listaPedido = pedidoDao.exibirUltimoPedido();
            p = listaPedido.get(0);
            listaC = clienteDao.dadosClienteNota(p.getCliente_fk());
            cm = listaC.get(0);
            listaPastel = pedidoDao.notaPastel(p.getId_pedido());
            listaB = pedidoDao.notaBebida(p.getId_pedido());

            int i = 0;
            int qtd = listaPastel.size();
            for (i = 0; i < qtd; i++) {

                setValorpastel(getValorpastel() + pedidoDao.notaPastelValor(listaPastel.get(i).getId_pastel()));

            }
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void valorBebidaNota() {
        try {
            listaPedido = pedidoDao.exibirUltimoPedido();
            p = listaPedido.get(0);
            listaC = clienteDao.dadosClienteNota(p.getCliente_fk());
            cm = listaC.get(0);
            listaPastel = pedidoDao.notaPastel(p.getId_pedido());
            listaB = pedidoDao.notaBebida(p.getId_pedido());

            int i = 0;
            int qtd = listaB.size();
            for (i = 0; i < qtd; i++) {

                setValorbebida(getValorbebida() + pedidoDao.notaBebidaValor(listaB.get(i).getId_bebida()));

            }
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public NovoPedido() {
        try {
            initComponents();
            //Toolkit toolkit = getToolkit();
            //Dimension size = toolkit.getScreenSize();
            //setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
            bebidaCountTextField.setText("Bebida " + getContb());
            pastelCountLabel.setText("Pastel " + getContb());
            dataTextField.setHorizontalAlignment(JTextField.LEFT);
            relogioTimer.start();
            for (CardapioModel cm : cardapioDao.exibirTipoPastel()) {
                tipoPastelComboBox.addItem(cm);
            }
            lerTabelaRecheio();
            lerTabelaBebida();
            tabelaCientePanel.setVisible(false);
            buscarButton.setVisible(false);
            salvarClienteButton.setEnabled(false);
            tipoPastelComboBox.setEnabled(false);

        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PageFormat getPageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight = 10.0;
        double headerHeight = 5.0;                  //fixed----->but can be mod
        double footerHeight = 5.0;                  //fixed----->but can be mod

        double width = convert_CM_To_PPI(5.8);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight + middleHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                convert_CM_To_PPI(0.25),
                convert_CM_To_PPI(0.5),
                width - convert_CM_To_PPI(0.35),
                height - convert_CM_To_PPI(1));   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);

        return pf;
    }

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    public class Nota implements Printable {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {
                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 24));

                try {
                    /*Draw Header*/
                    int y = 15;
                    int yShift = 8;
                    int headerRectHeight = 10;
                    int headerRectHeighta = 40;
                    //variaveis
                    listaPedido = pedidoDao.exibirUltimoPedido();
                    p = listaPedido.get(0);
                    listaC = clienteDao.dadosClienteNota(p.getCliente_fk());
                    cm = listaC.get(0);
                    listaPastel = pedidoDao.notaPastel(p.getId_pedido());
                    listaB = pedidoDao.notaBebida(p.getId_pedido());
                   

                    setValorpastel(0);
                    setValorbebida(0);
                    valorPastelNota();
                    valorBebidaNota();

                    g2d.setFont(new Font("Monospaced", Font.BOLD, 9));
                    g2d.drawString("-----------------------", 12, y);
                    y += yShift;
                    g2d.drawString("       PASTELANDO", 12, y);
                    y += yShift;
                    g2d.drawString("-----------------------", 12, y);
                    y += yShift;
                    g2d.drawString("  "+controller.dataNota(), 12, y);
                    y += yShift;
                    g2d.drawString("-----------------------", 12, y);
                    y += yShift;

                    g2d.drawString("" + cm.getNome(), 12, y);
                    y += yShift;
                    g2d.drawString("Tel: " + cm.getTelefone(), 12, y);
                    y += yShift;
                    g2d.drawString("Rua: " + cm.getRua(), 12, y);
                    y += yShift;
                    g2d.drawString("N°: " + cm.getNumero_casa(), 12, y);
                    y += yShift;
                    g2d.drawString("Bairro: " + cm.getBairro(), 12, y);
                    y += yShift;
                    if (!complementoTextField.getText().isEmpty()) {
                        g2d.drawString("Comp: " + cm.getComplemento(), 12, y);
                        y += yShift;
                    }
                    if (!referenciaTextField.getText().isEmpty()) {
                        g2d.drawString("Ref: " + cm.getReferencia(), 12, y);
                        y += yShift;
                    }
                    g2d.drawString("-----------------------", 12, y);
                    y += headerRectHeight;
                    if (listaPastel.size() > 0) {
                        g2d.drawString("" + listaPastel.size() + " x Pastel     R$:" + getValorpastel(), 12, y);
                        y += yShift;
                    }
                    if (listaB.size() > 0) {
                        g2d.drawString("" + listaB.size() + " x Bebida     R$:" + getValorbebida(), 12, y);
                        y += yShift;
                    }
                    if (Double.parseDouble(valorAdicionalTextField.getText()) > 0) {
                        g2d.drawString("Adicionais     R$:" + Double.parseDouble(valorAdicionalTextField.getText()), 12, y);
                        y += yShift;
                    }
                    g2d.drawString("Tx. de Entrega R$:" +(Double.parseDouble(taxaTextField.getText())) , 12, y);
                    y += yShift;
                    g2d.drawString("                       ", 12, y);
                    y += headerRectHeight;
                    g2d.drawString("   Valor Total R$:" +(Double.parseDouble(valorTotalTextField.getText())), 12, y);
                    y += yShift;
                    g2d.drawString("                       ", 12, y);
                    y += yShift;
                    if (debitoRadio.isSelected()) {
                        g2d.drawString("Pagamento: DEBITO", 12, y);
                        y += headerRectHeight;
                    }
                    if (creditoRadio.isSelected()) {
                        g2d.drawString("Pagamento: CRÉDITO", 12, y);
                        y += headerRectHeight;
                    }
                    if (dinheiroRadio.isSelected()) {
                        g2d.drawString("Pagamento: DINHEIRO", 12, y);
                        y += headerRectHeight;
                        g2d.drawString("Valor Recebido R$:" + Double.parseDouble(valorRecebidoTextField.getText()), 12, y);
                        y += headerRectHeight;
                        g2d.drawString("         TROCO R$:" + (Double.parseDouble(valorRecebidoTextField.getText()) - (Double.parseDouble(valorTotalTextField.getText()))), 12, y);
                        y += headerRectHeight;
                    }
                    if (!obsTextAreaLabel.getText().isEmpty()) {
                        g2d.drawString("----------OBS----------", 12, y);
                        y += yShift;
                        g2d.drawString("" + obsTextAreaLabel.getText(), 12, y);
                        y += yShift;
                    }
                    g2d.drawString("-----------------------", 12, y);
                    y += headerRectHeight;
                    g2d.drawString("Amor é o Principal", 12, y);
                    y += yShift;
                    g2d.drawString("Ingrediente Nessa", 12, y);
                    y += yShift;
                    g2d.drawString("Cozinha", 12, y);
                    y += yShift;
                    g2d.drawString("-----------------------", 12, y);
                    y += yShift;
                    return (PAGE_EXISTS);
                } catch (Exception ex) {
                    System.out.println("ERROR : " + ex);
                }
                return (PAGE_EXISTS);
            } else {
                return (NO_SUCH_PAGE);
            }

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

        clienteBGroup = new javax.swing.ButtonGroup();
        pedidoBGroup = new javax.swing.ButtonGroup();
        metodoPagamentoLabel = new javax.swing.ButtonGroup();
        relogioTimer = new org.netbeans.examples.lib.timerbean.Timer();
        designPanel = new javax.swing.JPanel();
        cPedidoPanel = new javax.swing.JPanel();
        tipoPastelComboBox = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        opcaoTable = new javax.swing.JTable();
        recheioRadio = new javax.swing.JRadioButton();
        complementoRadio = new javax.swing.JRadioButton();
        editarRecheioComplementoButton = new javax.swing.JButton();
        salvarPastelButton = new javax.swing.JButton();
        opcaoTextField1 = new javax.swing.JTextField();
        opcaoLabel1 = new javax.swing.JLabel();
        opcaoLabel2 = new javax.swing.JLabel();
        opcaoTextField2 = new javax.swing.JTextField();
        opcaoLabel3 = new javax.swing.JLabel();
        opcaoTextField3 = new javax.swing.JTextField();
        opcaoTextField4 = new javax.swing.JTextField();
        opcaoTextField5 = new javax.swing.JTextField();
        opcaoTextField6 = new javax.swing.JTextField();
        opcaoLabel4 = new javax.swing.JLabel();
        opcaoLabel5 = new javax.swing.JLabel();
        opcaoLabel6 = new javax.swing.JLabel();
        pastelCountLabel = new javax.swing.JLabel();
        addPastelExtraButton = new javax.swing.JButton();
        cCadastroPanel = new javax.swing.JPanel();
        novoClienteButton = new javax.swing.JRadioButton();
        clienteAntigoRadioButton = new javax.swing.JRadioButton();
        nomeLabel = new javax.swing.JLabel();
        nomeTextField = new javax.swing.JTextField();
        telefoneTextField = new javax.swing.JTextField();
        bairroTextField = new javax.swing.JTextField();
        ruaTextField = new javax.swing.JTextField();
        numeroTextField = new javax.swing.JTextField();
        telefoneLabel = new javax.swing.JLabel();
        bairroLabel = new javax.swing.JLabel();
        ruaLabel = new javax.swing.JLabel();
        numeroLabel = new javax.swing.JLabel();
        complementoTextField = new javax.swing.JTextField();
        complementoLabel = new javax.swing.JLabel();
        salvarClienteButton = new javax.swing.JButton();
        buscarButton = new javax.swing.JButton();
        referenciaLabel = new javax.swing.JLabel();
        referenciaTextField = new javax.swing.JTextField();
        tabelaCientePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clienteTable = new javax.swing.JTable();
        buscarTelTextField = new javax.swing.JTextField();
        buscarTelButton = new javax.swing.JButton();
        buscarNomeTextField = new javax.swing.JTextField();
        buscarNomeButton = new javax.swing.JButton();
        bebidaPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bebidasTable = new javax.swing.JTable();
        saborLabel = new javax.swing.JLabel();
        tamanhoLabel = new javax.swing.JLabel();
        saborTextField = new javax.swing.JTextField();
        tamanhoTextField = new javax.swing.JTextField();
        salvarBebidaPedido = new javax.swing.JButton();
        addBebidaExtraButton = new javax.swing.JButton();
        bebidaCountTextField = new javax.swing.JTextField();
        finalizarPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        obsTextAreaLabel = new javax.swing.JTextArea();
        obsLabel = new javax.swing.JLabel();
        taxaLabel = new javax.swing.JLabel();
        taxaTextField = new javax.swing.JTextField();
        verImpressaoButton = new javax.swing.JButton();
        cancelarPedidoButton = new javax.swing.JButton();
        concluirPedidoButton = new javax.swing.JButton();
        dinheiroRadio = new javax.swing.JRadioButton();
        debitoRadio = new javax.swing.JRadioButton();
        creditoRadio = new javax.swing.JRadioButton();
        valorTotalLabel = new javax.swing.JLabel();
        valorTotalTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        valorRecebidoTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        valorAdicionalTextField = new javax.swing.JTextField();
        confirmarValorAdicionalButton = new javax.swing.JButton();
        confirmarTaxaButton = new javax.swing.JButton();
        dataTextField = new javax.swing.JTextField();
        Background = new javax.swing.JLabel();

        relogioTimer.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                relogioTimerOnTime(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Pedido");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/pastelando_imagem/Logo_loginJP.jpg")
        );
        setLocation(new java.awt.Point(287, 20));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        designPanel.setBackground(new java.awt.Color(0, 0, 0));
        designPanel.setOpaque(false);

        tipoPastelComboBox.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        tipoPastelComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipoPastelComboBoxMouseClicked(evt);
            }
        });
        tipoPastelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoPastelComboBoxtipoPastelComboBoxActionPerformed(evt);
            }
        });
        tipoPastelComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tipoPastelComboBoxKeyPressed(evt);
            }
        });

        opcaoTable.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoTable.setModel(new javax.swing.table.DefaultTableModel(
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
        opcaoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcaoTableopcaoTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(opcaoTable);

        pedidoBGroup.add(recheioRadio);
        recheioRadio.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        recheioRadio.setSelected(true);
        recheioRadio.setText("Recheio");
        recheioRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recheioRadiorecheioRadioActionPerformed(evt);
            }
        });

        pedidoBGroup.add(complementoRadio);
        complementoRadio.setText("Complemento");
        complementoRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                complementoRadiocomplementoRadioActionPerformed(evt);
            }
        });

        editarRecheioComplementoButton.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        editarRecheioComplementoButton.setText("Editar");
        editarRecheioComplementoButton.setEnabled(false);
        editarRecheioComplementoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarRecheioComplementoButtonActionPerformed(evt);
            }
        });

        salvarPastelButton.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        salvarPastelButton.setText("SALVAR");
        salvarPastelButton.setEnabled(false);
        salvarPastelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarPastelButtonActionPerformed(evt);
            }
        });

        opcaoTextField1.setEditable(false);
        opcaoTextField1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoTextField1.setMinimumSize(new java.awt.Dimension(180, 22));
        opcaoTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcaoTextField1MouseClicked(evt);
            }
        });
        opcaoTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcaoTextField1opcaoTextField1ActionPerformed(evt);
            }
        });

        opcaoLabel1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoLabel1.setText("Recheio 1");

        opcaoLabel2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoLabel2.setText("Recheio 2");

        opcaoTextField2.setEditable(false);
        opcaoTextField2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcaoTextField2MouseClicked(evt);
            }
        });
        opcaoTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcaoTextField2opcaoTextField2ActionPerformed(evt);
            }
        });

        opcaoLabel3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoLabel3.setText("Recheio 3");

        opcaoTextField3.setEditable(false);
        opcaoTextField3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcaoTextField3MouseClicked(evt);
            }
        });
        opcaoTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcaoTextField3opcaoTextField3ActionPerformed(evt);
            }
        });

        opcaoTextField4.setEditable(false);
        opcaoTextField4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcaoTextField4MouseClicked(evt);
            }
        });
        opcaoTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcaoTextField4ActionPerformed(evt);
            }
        });

        opcaoTextField5.setEditable(false);
        opcaoTextField5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcaoTextField5MouseClicked(evt);
            }
        });

        opcaoTextField6.setEditable(false);
        opcaoTextField6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcaoTextField6MouseClicked(evt);
            }
        });

        opcaoLabel4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoLabel4.setText("Complemento 1");

        opcaoLabel5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoLabel5.setText("Complemento 2");

        opcaoLabel6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        opcaoLabel6.setText("Complemento 3");

        pastelCountLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pastelCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pastelCountLabel.setText("PASTEL");

        addPastelExtraButton.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        addPastelExtraButton.setText("Adicionar Outro ");
        addPastelExtraButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPastelExtraButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cPedidoPanelLayout = new javax.swing.GroupLayout(cPedidoPanel);
        cPedidoPanel.setLayout(cPedidoPanelLayout);
        cPedidoPanelLayout.setHorizontalGroup(
            cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPedidoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pastelCountLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tipoPastelComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 256, Short.MAX_VALUE)))
                .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cPedidoPanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addPastelExtraButton, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(opcaoTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(opcaoTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(opcaoTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(opcaoLabel1)
                                .addComponent(opcaoLabel2)
                                .addComponent(opcaoLabel3))))
                    .addGroup(cPedidoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(recheioRadio)))
                .addGap(18, 18, 18)
                .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(opcaoLabel6)
                            .addComponent(opcaoLabel5)
                            .addComponent(opcaoLabel4)
                            .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(opcaoTextField4)
                                .addComponent(opcaoTextField5)
                                .addComponent(opcaoTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPedidoPanelLayout.createSequentialGroup()
                            .addComponent(editarRecheioComplementoButton, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(salvarPastelButton)))
                    .addComponent(complementoRadio))
                .addContainerGap())
        );
        cPedidoPanelLayout.setVerticalGroup(
            cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPedidoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pastelCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recheioRadio)
                    .addComponent(complementoRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcaoLabel1)
                    .addComponent(opcaoLabel4)
                    .addComponent(tipoPastelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cPedidoPanelLayout.createSequentialGroup()
                        .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(opcaoTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opcaoTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(opcaoLabel2)
                            .addComponent(opcaoLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(opcaoTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opcaoTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(opcaoLabel3)
                            .addComponent(opcaoLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(opcaoTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opcaoTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(cPedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editarRecheioComplementoButton)
                            .addComponent(salvarPastelButton)
                            .addComponent(addPastelExtraButton)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        addPastelExtraButton.setVisible(false);

        clienteBGroup.add(novoClienteButton);
        novoClienteButton.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        novoClienteButton.setText("Novo Cliente");
        novoClienteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoClienteButtonActionPerformed(evt);
            }
        });

        clienteBGroup.add(clienteAntigoRadioButton);
        clienteAntigoRadioButton.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        clienteAntigoRadioButton.setText("Cliente Antigo");
        clienteAntigoRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteAntigoRadioButtonActionPerformed(evt);
            }
        });

        nomeLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        nomeLabel.setText("Nome:");

        nomeTextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        telefoneTextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        bairroTextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        ruaTextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        numeroTextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        telefoneLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        telefoneLabel.setText("Telefone:");

        bairroLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        bairroLabel.setText("Bairro:");

        ruaLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ruaLabel.setText("Rua:");

        numeroLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        numeroLabel.setText("Número:");

        complementoTextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        complementoLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        complementoLabel.setText("Comple.:");

        salvarClienteButton.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        salvarClienteButton.setText("Salvar");
        salvarClienteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarClienteButtonActionPerformed(evt);
            }
        });

        buscarButton.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        buscarButton.setText("Buscar");
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
            }
        });

        referenciaLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        referenciaLabel.setText("Ref.:");

        javax.swing.GroupLayout cCadastroPanelLayout = new javax.swing.GroupLayout(cCadastroPanel);
        cCadastroPanel.setLayout(cCadastroPanelLayout);
        cCadastroPanelLayout.setHorizontalGroup(
            cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cCadastroPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cCadastroPanelLayout.createSequentialGroup()
                        .addComponent(numeroLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numeroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cCadastroPanelLayout.createSequentialGroup()
                        .addComponent(ruaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ruaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cCadastroPanelLayout.createSequentialGroup()
                        .addComponent(bairroLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bairroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cCadastroPanelLayout.createSequentialGroup()
                        .addComponent(telefoneLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telefoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cCadastroPanelLayout.createSequentialGroup()
                        .addComponent(nomeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cCadastroPanelLayout.createSequentialGroup()
                        .addComponent(novoClienteButton)
                        .addGap(18, 18, 18)
                        .addComponent(clienteAntigoRadioButton))
                    .addGroup(cCadastroPanelLayout.createSequentialGroup()
                        .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(referenciaLabel)
                            .addComponent(complementoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(complementoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addGroup(cCadastroPanelLayout.createSequentialGroup()
                                .addComponent(buscarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salvarClienteButton))
                            .addComponent(referenciaTextField))))
                .addGap(27, 27, 27))
        );
        cCadastroPanelLayout.setVerticalGroup(
            cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cCadastroPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(novoClienteButton)
                    .addComponent(clienteAntigoRadioButton))
                .addGap(18, 18, 18)
                .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeLabel)
                    .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefoneLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bairroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bairroLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ruaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ruaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(complementoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(complementoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(referenciaLabel)
                    .addComponent(referenciaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cCadastroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarButton)
                    .addComponent(salvarClienteButton))
                .addContainerGap())
        );

        clienteTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        buscarTelTextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        buscarTelTextField.setText("Digite o Telefone");
        buscarTelTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarTelTextFieldMouseClicked(evt);
            }
        });
        buscarTelTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarTelTextFieldActionPerformed(evt);
            }
        });

        buscarTelButton.setText("Filtrar Telefone");
        buscarTelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarTelButtonActionPerformed(evt);
            }
        });

        buscarNomeTextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
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

        javax.swing.GroupLayout tabelaCientePanelLayout = new javax.swing.GroupLayout(tabelaCientePanel);
        tabelaCientePanel.setLayout(tabelaCientePanelLayout);
        tabelaCientePanelLayout.setHorizontalGroup(
            tabelaCientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabelaCientePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buscarNomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscarNomeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buscarTelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscarTelButton)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        tabelaCientePanelLayout.setVerticalGroup(
            tabelaCientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabelaCientePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabelaCientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarTelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarTelButton)
                    .addComponent(buscarNomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarNomeButton)))
        );

        bebidasTable.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        bebidasTable.setModel(new javax.swing.table.DefaultTableModel(
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
        bebidasTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bebidasTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(bebidasTable);

        saborLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        saborLabel.setText("Sabor:");

        tamanhoLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        tamanhoLabel.setText("Tamanho:");

        saborTextField.setEditable(false);
        saborTextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        saborTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saborTextFieldActionPerformed(evt);
            }
        });

        tamanhoTextField.setEditable(false);
        tamanhoTextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        salvarBebidaPedido.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        salvarBebidaPedido.setText("SALVAR");
        salvarBebidaPedido.setEnabled(false);
        salvarBebidaPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarBebidaPedidoActionPerformed(evt);
            }
        });

        addBebidaExtraButton.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        addBebidaExtraButton.setText("Adicionar Outra");
        addBebidaExtraButton.setEnabled(false);
        addBebidaExtraButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBebidaExtraButtonActionPerformed(evt);
            }
        });

        bebidaCountTextField.setEditable(false);
        bebidaCountTextField.setBackground(new java.awt.Color(0,0,0,1)
        );
        bebidaCountTextField.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        bebidaCountTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        bebidaCountTextField.setText("BEBIDAS");
        bebidaCountTextField.setBorder(null);

        javax.swing.GroupLayout bebidaPanelLayout = new javax.swing.GroupLayout(bebidaPanel);
        bebidaPanel.setLayout(bebidaPanelLayout);
        bebidaPanelLayout.setHorizontalGroup(
            bebidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bebidaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bebidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bebidaCountTextField)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(bebidaPanelLayout.createSequentialGroup()
                        .addGroup(bebidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(addBebidaExtraButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bebidaPanelLayout.createSequentialGroup()
                                .addComponent(tamanhoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tamanhoTextField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bebidaPanelLayout.createSequentialGroup()
                                .addComponent(saborLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saborTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addComponent(salvarBebidaPedido)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bebidaPanelLayout.setVerticalGroup(
            bebidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bebidaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bebidaCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bebidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saborLabel)
                    .addComponent(saborTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bebidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tamanhoLabel)
                    .addComponent(tamanhoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(bebidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salvarBebidaPedido)
                    .addComponent(addBebidaExtraButton))
                .addContainerGap())
        );

        addBebidaExtraButton.setVisible(false);

        obsTextAreaLabel.setColumns(20);
        obsTextAreaLabel.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        obsTextAreaLabel.setRows(5);
        jScrollPane3.setViewportView(obsTextAreaLabel);

        obsLabel.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        obsLabel.setText("Obsevação:");

        taxaLabel.setText("Taxa de Entrega:");

        taxaTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        taxaTextField.setText("0");

        verImpressaoButton.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        verImpressaoButton.setText("VER IMPRESSÃO");
        verImpressaoButton.setEnabled(false);
        verImpressaoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verImpressaoButtonActionPerformed(evt);
            }
        });

        cancelarPedidoButton.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        cancelarPedidoButton.setText("CANCELAR PEDIDO");
        cancelarPedidoButton.setEnabled(false);
        cancelarPedidoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarPedidoButtonActionPerformed(evt);
            }
        });

        concluirPedidoButton.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        concluirPedidoButton.setText("CONCLUIR PEDIDO");
        concluirPedidoButton.setEnabled(false);
        concluirPedidoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                concluirPedidoButtonActionPerformed(evt);
            }
        });

        metodoPagamentoLabel.add(dinheiroRadio);
        dinheiroRadio.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        dinheiroRadio.setText("Dinheiro");
        dinheiroRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dinheiroRadioActionPerformed(evt);
            }
        });

        metodoPagamentoLabel.add(debitoRadio);
        debitoRadio.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        debitoRadio.setText("Débito");
        debitoRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debitoRadioActionPerformed(evt);
            }
        });

        metodoPagamentoLabel.add(creditoRadio);
        creditoRadio.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        creditoRadio.setText("Crédito");
        creditoRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditoRadioActionPerformed(evt);
            }
        });

        valorTotalLabel.setText("Valor Total:");

        valorTotalTextField.setEditable(false);
        valorTotalTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valorTotalTextField.setText("0");
        valorTotalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorTotalTextFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Pagamento:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel3.setText("Valor Recebido:");

        valorRecebidoTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valorRecebidoTextField.setText("0");

        jLabel4.setText("Valores Adicionais:");

        valorAdicionalTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valorAdicionalTextField.setText("0");

        confirmarValorAdicionalButton.setText("Ok");
        confirmarValorAdicionalButton.setEnabled(false);
        confirmarValorAdicionalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarValorAdicionalButtonActionPerformed(evt);
            }
        });

        confirmarTaxaButton.setText("Ok");
        confirmarTaxaButton.setEnabled(false);
        confirmarTaxaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarTaxaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout finalizarPanelLayout = new javax.swing.GroupLayout(finalizarPanel);
        finalizarPanel.setLayout(finalizarPanelLayout);
        finalizarPanelLayout.setHorizontalGroup(
            finalizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finalizarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(finalizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(obsLabel)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(finalizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(finalizarPanelLayout.createSequentialGroup()
                        .addComponent(taxaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(taxaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirmarTaxaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valorTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valorTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(creditoRadio)
                    .addComponent(debitoRadio)
                    .addGroup(finalizarPanelLayout.createSequentialGroup()
                        .addComponent(dinheiroRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valorRecebidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(finalizarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valorAdicionalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirmarValorAdicionalButton))
                    .addGroup(finalizarPanelLayout.createSequentialGroup()
                        .addComponent(cancelarPedidoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verImpressaoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(concluirPedidoButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        finalizarPanelLayout.setVerticalGroup(
            finalizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finalizarPanelLayout.createSequentialGroup()
                .addGroup(finalizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(finalizarPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(obsLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, finalizarPanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)))
                .addGroup(finalizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(finalizarPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane3))
                    .addGroup(finalizarPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(finalizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dinheiroRadio)
                            .addComponent(jLabel3)
                            .addComponent(valorRecebidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(debitoRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(creditoRadio)
                        .addGap(17, 17, 17)
                        .addGroup(finalizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(valorAdicionalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmarValorAdicionalButton))
                        .addGap(18, 18, 18)
                        .addGroup(finalizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valorTotalTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(finalizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(taxaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(taxaLabel)
                                .addComponent(valorTotalLabel)
                                .addComponent(confirmarTaxaButton)))
                        .addGap(18, 18, 18)
                        .addGroup(finalizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(verImpressaoButton)
                            .addComponent(cancelarPedidoButton)
                            .addComponent(concluirPedidoButton))))
                .addContainerGap())
        );

        dataTextField.setEditable(false);
        dataTextField.setBackground(new java.awt.Color(0,0,0,1));
        dataTextField.setFont(new java.awt.Font("Tahoma", 2, 22)); // NOI18N
        dataTextField.setForeground(new java.awt.Color(255, 255, 255));
        dataTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        dataTextField.setBorder(null);
        dataTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout designPanelLayout = new javax.swing.GroupLayout(designPanel);
        designPanel.setLayout(designPanelLayout);
        designPanelLayout.setHorizontalGroup(
            designPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(designPanelLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(designPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(designPanelLayout.createSequentialGroup()
                        .addComponent(cCadastroPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cPedidoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bebidaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(designPanelLayout.createSequentialGroup()
                        .addComponent(finalizarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(designPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabelaCientePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataTextField))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        designPanelLayout.setVerticalGroup(
            designPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, designPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(designPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cPedidoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cCadastroPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bebidaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(designPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(finalizarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, designPanelLayout.createSequentialGroup()
                        .addComponent(tabelaCientePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(dataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        getContentPane().add(designPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1300, 680));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pastelando_imagem/Background_Medio.jpg"))); // NOI18N
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tipoPastelComboBoxtipoPastelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoPastelComboBoxtipoPastelComboBoxActionPerformed
        tp = (CardapioModel) tipoPastelComboBox.getSelectedItem();
        menuMontagem(tp.getCategoria());
        setCategoria(tp.getCategoria());
        setTipoPastel(tp.getId_tipo());
        setValorTp(tp.getValor_tp());
        contc = 1;
        contr = 1;
        contpd = 1;
        cleanOpcText();
        listaPm.clear();
    }//GEN-LAST:event_tipoPastelComboBoxtipoPastelComboBoxActionPerformed

    private void opcaoTableopcaoTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcaoTableopcaoTableMouseClicked
        if (!pastelFeito) {
            try {
                if (getCategoria() == 1) {
                    int linha = opcaoTable.getSelectedRow();
                    if (complementoRadio.isSelected()) {
                        PastelModel complemento = new PastelModel();
                        if (contc > 3 && editavel == false) {
                            JOptionPane.showMessageDialog(null, "Número Máximo de Complementos Padrão Atingido!!", "ERRO 0199", JOptionPane.ERROR_MESSAGE);
                        }
                        if ((contc == 3 && editavel == false) || auxEditar == 6) {
                            if (editavel == false) {
                                opcaoTextField6.setText(compTableModel.getValueAt(linha, 0).toString());
                                auxEditar = -1;
                                contc++;
                                comp = cardapioDao.exibirComplementos().get(linha);
                                complemento.setSabor_c(opcaoTextField6.getText());
                                complemento.setTipo_pastel_fk(getTipoPastel());
                                complemento.setComplemento_fk(comp.getId_complemento());
                                listaPm.add(complemento);
                            }
                            if (editavel == true) {
                                opcaoTextField6.setText(compTableModel.getValueAt(linha, 0).toString());
                                auxEditar = -1;
                                comp = cardapioDao.exibirComplementos().get(linha);
                                complemento.setSabor_c(opcaoTextField6.getText());
                                complemento.setTipo_pastel_fk(getTipoPastel());
                                complemento.setComplemento_fk(comp.getId_complemento());
                                listaPm.set(idEditado, complemento);

                            }
                        }

                        if ((contc == 2 && editavel == false) || auxEditar == 5) {
                            if (editavel == false) {
                                opcaoTextField5.setText(compTableModel.getValueAt(linha, 0).toString());
                                auxEditar = -1;
                                contc++;
                                comp = cardapioDao.exibirComplementos().get(linha);
                                complemento.setSabor_c(opcaoTextField5.getText());
                                complemento.setTipo_pastel_fk(getTipoPastel());
                                complemento.setComplemento_fk(comp.getId_complemento());
                                listaPm.add(complemento);
                            }
                            if (editavel == true) {
                                opcaoTextField5.setText(compTableModel.getValueAt(linha, 0).toString());
                                auxEditar = -1;
                                comp = cardapioDao.exibirComplementos().get(linha);
                                complemento.setSabor_c(opcaoTextField5.getText());
                                complemento.setTipo_pastel_fk(getTipoPastel());
                                complemento.setComplemento_fk(comp.getId_complemento());
                                listaPm.set(idEditado, complemento);
                            }
                        }

                        if ((contc == 1 && editavel == false) || auxEditar == 4) {
                            opcaoTextField4.setText(compTableModel.getValueAt(linha, 0).toString());
                            auxEditar = -1;
                            contc++;
                            comp = cardapioDao.exibirComplementos().get(linha);
                            complemento.setSabor_c(opcaoTextField4.getText());
                            complemento.setTipo_pastel_fk(getTipoPastel());
                            complemento.setComplemento_fk(comp.getId_complemento());
                            listaPm.add(complemento);

                            if (editavel == true) {
                                opcaoTextField4.setText(compTableModel.getValueAt(linha, 0).toString());
                                auxEditar = -1;
                                comp = cardapioDao.exibirComplementos().get(linha);
                                complemento.setSabor_c(opcaoTextField4.getText());
                                complemento.setTipo_pastel_fk(getTipoPastel());
                                complemento.setComplemento_fk(comp.getId_complemento());
                                listaPm.set(idEditado, complemento);

                            }
                        }
                    }

                    if (recheioRadio.isSelected()) {
                        PastelModel recheio = new PastelModel();
                        if (contr > 3 && editavel == false) {
                            JOptionPane.showMessageDialog(null, "Número Máximo de Recheios Padrão Atingido!!", "ERRO 0200", JOptionPane.ERROR_MESSAGE);
                        }
                        if ((contr == 3 && editavel == false) || auxEditar == 3) {
                            if (editavel == false) {
                                opcaoTextField3.setText(rTableModel.getValueAt(linha, 0).toString());
                                auxEditar = -1;
                                contr++;
                                r = cardapioDao.exibirRecheios().get(linha);
                                recheio.setSabor_r(opcaoTextField3.getText());
                                recheio.setTipo_pastel_fk(getTipoPastel());
                                recheio.setRecheio_fk(r.getId_recheio());
                                recheio.setValor_r(r.getValor_r());
                                setValorTotal(valorTotal + recheio.getValor_r());
                                listaPm.add(recheio);
                            }
                            if (editavel == true) {
                                opcaoTextField3.setText(rTableModel.getValueAt(linha, 0).toString());
                                auxEditar = -1;
                                r = cardapioDao.exibirRecheios().get(linha);
                                recheio.setSabor_r(opcaoTextField3.getText());
                                recheio.setTipo_pastel_fk(getTipoPastel());
                                recheio.setRecheio_fk(r.getId_recheio());
                                recheio.setValor_r(r.getValor_r());
                                setValorTotal(valorTotal + recheio.getValor_r());
                                listaPm.set(idEditado, recheio);

                            }
                        }

                        if ((contr == 2 && editavel == false) || auxEditar == 2) {
                            if (editavel == false) {
                                opcaoTextField2.setText(rTableModel.getValueAt(linha, 0).toString());
                                auxEditar = -1;
                                contr++;
                                r = cardapioDao.exibirRecheios().get(linha);
                                recheio.setSabor_r(opcaoTextField2.getText());
                                recheio.setTipo_pastel_fk(getTipoPastel());
                                recheio.setRecheio_fk(r.getId_recheio());
                                recheio.setValor_r(r.getValor_r());
                                setValorTotal(valorTotal + recheio.getValor_r());
                                listaPm.add(recheio);
                            }
                            if (editavel == true) {
                                opcaoTextField2.setText(rTableModel.getValueAt(linha, 0).toString());
                                auxEditar = -1;
                                r = cardapioDao.exibirRecheios().get(linha);
                                recheio.setSabor_r(opcaoTextField2.getText());
                                recheio.setTipo_pastel_fk(getTipoPastel());
                                recheio.setRecheio_fk(r.getId_recheio());
                                recheio.setValor_r(r.getValor_r());
                                setValorTotal(valorTotal + recheio.getValor_r());
                                listaPm.set(idEditado, recheio);

                            }
                        }

                        if ((contr == 1 && editavel == false) || auxEditar == 1) {
                            if (editavel == false) {
                                opcaoTextField1.setText(rTableModel.getValueAt(linha, 0).toString());
                                auxEditar = -1;
                                contr++;
                                r = cardapioDao.exibirRecheios().get(linha);
                                recheio.setSabor_r(opcaoTextField1.getText());
                                recheio.setTipo_pastel_fk(getTipoPastel());
                                recheio.setRecheio_fk(r.getId_recheio());
                                recheio.setValor_r(r.getValor_r());
                                setValorTotal(valorTotal + recheio.getValor_r());
                                listaPm.add(recheio);
                            }
                            if (editavel == true) {
                                opcaoTextField1.setText(rTableModel.getValueAt(linha, 0).toString());
                                auxEditar = -1;
                                r = cardapioDao.exibirRecheios().get(linha);
                                recheio.setSabor_r(opcaoTextField1.getText());
                                recheio.setTipo_pastel_fk(getTipoPastel());
                                recheio.setRecheio_fk(r.getId_recheio());
                                recheio.setValor_r(r.getValor_r());
                                setValorTotal(valorTotal + recheio.getValor_r());
                                listaPm.set(idEditado, recheio);

                            }

                        }
                    }
                }
                if (getCategoria() == 2) {
                    int linha = opcaoTable.getSelectedRow();
                    PastelModel pastelDoce = new PastelModel();
                    if (!editavel) {
                        if (contpd > 1) {
                            JOptionPane.showMessageDialog(null, "Um sabor já foi selecionado, Clique em editar para selecionar novamente!!", "ERRO 0201", JOptionPane.ERROR_MESSAGE);
                        } else {
                            opcaoTextField1.setText(pdTableModel.getValueAt(linha, 0).toString());
                            auxEditar = -1;
                            pd = cardapioDao.exibirPastelDoce().get(linha);
                            pastelDoce.setSabor_pd(opcaoTextField1.getText());
                            pastelDoce.setTipo_pastel_fk(getTipoPastel());
                            pastelDoce.setPastel_doce_fk(pd.getId_pd());
                            pastelDoce.setValor_pd(pd.getValor_pd());
                            setValorTotal(valorTotal + pastelDoce.getValor_pd());
                            listaPm.add(pastelDoce);
                            contpd++;
                        }
                    }
                    if (editavel && auxEditar == 7) {
                        if (contpd > 1) {
                            JOptionPane.showMessageDialog(null, "Um sabor já foi selecionado, Clique em editar para selecionar novamente!!", "ERRO 0201", JOptionPane.ERROR_MESSAGE);
                        } else {
                            opcaoTextField1.setText(pdTableModel.getValueAt(linha, 0).toString());
                            auxEditar = -1;
                            pd = cardapioDao.exibirPastelDoce().get(linha);
                            pastelDoce.setSabor_pd(opcaoTextField1.getText());
                            pastelDoce.setTipo_pastel_fk(getTipoPastel());
                            pastelDoce.setPastel_doce_fk(pd.getId_pd());
                            pastelDoce.setValor_pd(pd.getValor_pd());
                            setValorTotal(valorTotal + pastelDoce.getValor_pd());
                            listaPm.set(idEditado, pastelDoce);
                            contpd++;
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (pastelFeito || !pedidoAberto) {
            if (!pedidoAberto) {
                JOptionPane.showMessageDialog(null, "Selecione o cliente primeiro para montar o pastel", "ERRO 0204", JOptionPane.ERROR_MESSAGE);
            } else {
                if (pastelFeito) {
                    JOptionPane.showMessageDialog(null, "Adicione outro pastel para continuar", "ERRO 0205", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_opcaoTableopcaoTableMouseClicked

    private void recheioRadiorecheioRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recheioRadiorecheioRadioActionPerformed
        try {
            lerTabelaRecheio();
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_recheioRadiorecheioRadioActionPerformed

    private void complementoRadiocomplementoRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_complementoRadiocomplementoRadioActionPerformed
        try {
            lerTabelaComplemento();
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_complementoRadiocomplementoRadioActionPerformed

    private void opcaoTextField1opcaoTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcaoTextField1opcaoTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcaoTextField1opcaoTextField1ActionPerformed

    private void opcaoTextField2opcaoTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcaoTextField2opcaoTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcaoTextField2opcaoTextField2ActionPerformed

    private void opcaoTextField3opcaoTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcaoTextField3opcaoTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcaoTextField3opcaoTextField3ActionPerformed

    private void novoClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoClienteButtonActionPerformed
        if (!pedidoAberto) {
            textoEditavel(true);
            buscarButton.setVisible(false);
            salvarClienteButton.setEnabled(true);
            cleanText();
        } else {
            textoEditavel(false);
            JOptionPane.showMessageDialog(null, "Cancele o pedido para poder selecionar outro cliente", "ERRO 0206", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_novoClienteButtonActionPerformed

    private void clienteAntigoRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteAntigoRadioButtonActionPerformed
        if (!pedidoAberto) {
            textoEditavel(false);
            buscarButton.setVisible(true);
            salvarClienteButton.setEnabled(true);
            cleanText();

        } else {
            textoEditavel(false);
            JOptionPane.showMessageDialog(null, "Cancele o pedido para poder selecionar outro cliente", "ERRO 0206", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_clienteAntigoRadioButtonActionPerformed

    private void salvarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarClienteButtonActionPerformed
        try {
            if (clienteAntigoRadioButton.isSelected()) {
                pedidoDao.abrirPedido(cma);
            } else if (novoClienteButton.isSelected()) {
                cm.setNome(nomeTextField.getText());
                cm.setTelefone(telefoneTextField.getText());
                cm.setBairro(bairroTextField.getText());
                cm.setRua(ruaTextField.getText());
                cm.setNumero_casa(numeroTextField.getText());
                cm.setComplemento(complementoTextField.getText());
                cm.setReferencia(referenciaTextField.getText());
                clienteDao.inserirC(cm);//id aparece aqui
                listaC = clienteDao.exibirCliente();
                cm = listaC.get((listaC.size() - 1));
                pedidoDao.abrirPedido(cm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        salvarClienteButton.setEnabled(false);
        editarRecheioComplementoButton.setEnabled(true);
        salvarBebidaPedido.setEnabled(true);
        salvarPastelButton.setEnabled(true);
        addBebidaExtraButton.setEnabled(true);
        concluirPedidoButton.setEnabled(true);
        verImpressaoButton.setEnabled(true);
        cancelarPedidoButton.setEnabled(true);
        confirmarValorAdicionalButton.setEnabled(true);
        confirmarTaxaButton.setEnabled(true);
        setPastelFeito(false);
        setPedidoAberto(true);
        buscarButton.setEnabled(false);
        tipoPastelComboBox.setEnabled(true);
        tabelaCientePanel.setVisible(false);
        buscarTelTextField.setText("");
        buscarNomeTextField.setText("");
        boolean filtradaCli = false;
        boolean filtradaTel = false;
    }//GEN-LAST:event_salvarClienteButtonActionPerformed

    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        try {
            tabelaCientePanel.setVisible(true);
            cleanText();
            lerTabelaCliente();
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void clienteTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteTableMouseClicked
        int linha = clienteTable.getSelectedRow();
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
        try {
            if (!filtradaCli && !filtradaTel) {
                listaC = clienteDao.exibirCliente();
                cma = listaC.get(linha);
            }
            if (filtradaCli) {
                listaC = clienteDao.filtrarClienteNome(buscarNomeTextField.getText());
                cma = listaC.get(linha);
            }
            if (filtradaTel) {
                listaC = clienteDao.filtrarClienteTel(buscarTelTextField.getText());
                cma = listaC.get(linha);
            }
            //ta aqui;

        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_clienteTableMouseClicked

    private void tipoPastelComboBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipoPastelComboBoxKeyPressed

    }//GEN-LAST:event_tipoPastelComboBoxKeyPressed

    private void bebidasTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bebidasTableMouseClicked
        try {
            int linha = bebidasTable.getSelectedRow();
            saborTextField.setText(bTableModel.getValueAt(linha, 0).toString());
            tamanhoTextField.setText(bTableModel.getValueAt(linha, 2).toString());
            listaB = cardapioDao.exibirBebida();
            setValorB(listaB.get(linha).getValor_refri());
            b = listaB.get(linha);
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bebidasTableMouseClicked

    private void addBebidaExtraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBebidaExtraButtonActionPerformed
        if ("Adicionar Outra".equals(addBebidaExtraButton.getText())) {
            setContb(contb + 1);
            bebidaCountTextField.setText("Bebida " + getContb());
            addBebidaExtraButton.setText("Cancelar");
            salvarBebidaPedido.setEnabled(true);
        } else {
            if ("Cancelar".equals(addBebidaExtraButton.getText())) {
                setContb(contb - 1);
                bebidaCountTextField.setText("Bebida " + getContb());
                addBebidaExtraButton.setText("Adicionar Outra");
                salvarBebidaPedido.setEnabled(false);
            }
        }
    }//GEN-LAST:event_addBebidaExtraButtonActionPerformed

    private void concluirPedidoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_concluirPedidoButtonActionPerformed
        try {
            controller = new DataController();
            listaPedido = pedidoDao.exibirPedidos();
            p = listaPedido.get(listaPedido.size() - 1);
            p.setObs(obsTextAreaLabel.getText());
            pedidoDao.fecharPedido(obsTextAreaLabel.getText(), this.pagamento, (Double.parseDouble(taxaTextField.getText())), (Double.parseDouble(valorTotalTextField.getText())), p, controller.getData());

            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.setPrintable(new Nota(), getPageFormat(pj));
            try {
                pj.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        resetarPedido();
        JOptionPane.showMessageDialog(null, "Pedido Concluido");
    }//GEN-LAST:event_concluirPedidoButtonActionPerformed

    private void dataTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataTextFieldActionPerformed

    }//GEN-LAST:event_dataTextFieldActionPerformed

    private void relogioTimerOnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relogioTimerOnTime
        controller = new DataController();
        controller.dataTxt(dataTextField);
    }//GEN-LAST:event_relogioTimerOnTime

    private void salvarBebidaPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarBebidaPedidoActionPerformed
        try {
            listaPedido = pedidoDao.exibirPedidos();
            p = listaPedido.get((listaPedido.size() - 1));
            p.setId_bp(b.getId_bebida());
            pedidoDao.inserirBebidaPedido(p);
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        setValorTotal(valorTotal + getValorB());
        valorTotalTextField.setText("" + getValorTotal());
        salvarBebidaPedido.setEnabled(false);
        addBebidaExtraButton.setVisible(true);
        addBebidaExtraButton.setText("Adicionar Outra");
        bebidasTable.clearSelection();
        saborTextField.setText("");
        tamanhoTextField.setText("");
    }//GEN-LAST:event_salvarBebidaPedidoActionPerformed

    private void dinheiroRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dinheiroRadioActionPerformed
        setPagamento(1);
    }//GEN-LAST:event_dinheiroRadioActionPerformed

    private void debitoRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debitoRadioActionPerformed
        setPagamento(2);
    }//GEN-LAST:event_debitoRadioActionPerformed

    private void creditoRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditoRadioActionPerformed
        setPagamento(3);
    }//GEN-LAST:event_creditoRadioActionPerformed

    private void opcaoTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcaoTextField1MouseClicked
        if (editavel == true) {
            if (getCategoria() == 1) {
                for (int i = 0; i < listaPm.size(); i++) {
                    if (listaPm.get(i).getSabor_r() != null && listaPm.get(i).getSabor_r().equals(opcaoTextField1.getText())) {
                        setValorTotal(getValorTotal() - listaPm.get(i).getValor_r());
                        setIdEditado(i);
                        opcaoTextField1.setText("");
                        auxEditar = 1;
                    }
                }
            }
            if (getCategoria() == 2) {
                for (int i = 0; i < listaPm.size(); i++) {
                    if (listaPm.get(i).getSabor_pd() != null && listaPm.get(i).getSabor_pd().equals(opcaoTextField1.getText())) {
                        setValorTotal(getValorTotal() - listaPm.get(i).getValor_pd());
                        setIdEditado(i);
                        opcaoTextField1.setText("");
                        auxEditar = 7;
                        contpd = 1;
                    }
                }
            }
        }
    }//GEN-LAST:event_opcaoTextField1MouseClicked

    private void editarRecheioComplementoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarRecheioComplementoButtonActionPerformed
        if (dadosPreenchidos() || getCategoria() == 3 || getCategoria() == 4) {
            int input = -1;
            if ("Editar".equals(editarRecheioComplementoButton.getText())) {
                input = JOptionPane.showConfirmDialog(null, "Deseja Editar?", "confirmação", JOptionPane.YES_NO_OPTION);
            }
            if ("Concluir".equals(editarRecheioComplementoButton.getText())) {
                input = JOptionPane.showConfirmDialog(null, "Finalizar edição?", "confirmação", JOptionPane.YES_NO_OPTION);
            }
            if (input == 0) {
                if ("Editar".equals(editarRecheioComplementoButton.getText())) {
                    setEditavel(true);
                    editarRecheioComplementoButton.setText("Concluir");
                    salvarPastelButton.setEnabled(false);
                } else {
                    if ("Concluir".equals(editarRecheioComplementoButton.getText())) {
                        setEditavel(false);
                        editarRecheioComplementoButton.setText("Editar");
                        salvarPastelButton.setEnabled(true);
                    }
                }
            }
        } else {
            if (!dadosPreenchidos() && getCategoria() != 3) {
                JOptionPane.showMessageDialog(null, "Selecione algo antes de editar", "ERRO 0207", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_editarRecheioComplementoButtonActionPerformed

    private void opcaoTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcaoTextField2MouseClicked
        if (editavel == true) {
            for (int i = 0; i < listaPm.size(); i++) {
                if (listaPm.get(i).getSabor_r() != null && listaPm.get(i).getSabor_r().equals(opcaoTextField2.getText())) {
                    setValorTotal(getValorTotal() - listaPm.get(i).getValor_r());
                    setIdEditado(i);
                }
            }
            opcaoTextField2.setText("");
            auxEditar = 2;
        }
    }//GEN-LAST:event_opcaoTextField2MouseClicked

    private void opcaoTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcaoTextField3MouseClicked
        if (editavel == true) {
            for (int i = 0; i < listaPm.size(); i++) {
                if (listaPm.get(i).getSabor_r() != null && listaPm.get(i).getSabor_r().equals(opcaoTextField3.getText())) {
                    setValorTotal(getValorTotal() - listaPm.get(i).getValor_r());
                    setIdEditado(i);
                }
            }
            opcaoTextField3.setText("");
            auxEditar = 3;
        }
    }//GEN-LAST:event_opcaoTextField3MouseClicked

    private void salvarPastelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarPastelButtonActionPerformed
        setEditavel(false);
        if (dadosPreenchidos() || getCategoria() == 3 || getCategoria() == 4) {
            try {
                PastelModel base = new PastelModel();
                if (getCategoria() != 3) {
                    base = listaPm.get(listaPm.size() - 1);
                    pedidoDao.inserirTipoPastel(base);
                    listaPastel = pedidoDao.selecionarPastel();
                    listaPedido = pedidoDao.exibirPedidos();
                    base = listaPastel.get(listaPastel.size() - 1);
                    base.setPedido_fk(listaPedido.get(listaPedido.size() - 1).getId_pedido());
                    pedidoDao.inserirPastelPedido(base);
                    for (PastelModel pastel : listaPm) {
                        pastel.setId_pastel(base.getId_pastel());
                        if (pastel.getRecheio_fk() != 0) {
                            pedidoDao.inserirRecheioPastel(pastel);
                        }
                        if (pastel.getComplemento_fk() != 0) {
                            pedidoDao.inserirComplementoPastel(pastel);
                        }
                    }
                } else {
                    PastelModel pastelVento = new PastelModel();
                    pastelVento.setTipo_pastel_fk(tipoPastel);
                    setCategoria(-1);
                    pedidoDao.inserirTipoPastel(pastelVento);
                    listaPastel = pedidoDao.selecionarPastel();
                    listaPedido = pedidoDao.exibirPedidos();
                    base = listaPastel.get(listaPastel.size() - 1);
                    base.setPedido_fk(listaPedido.get(listaPedido.size() - 1).getId_pedido());
                    pedidoDao.inserirPastelPedido(base);
                }
            } catch (SQLException e) {
                JOptionPane.showInternalMessageDialog(null, "Erro ao inserir no banco." + e, "ERRO!", JOptionPane.ERROR_MESSAGE);
            }

            listaPm.clear();
            cleanOpcText();
            salvarPastelButton.setEnabled(false);
            addPastelExtraButton.setVisible(true);
            addPastelExtraButton.setText("Adicionar Outro");
            contr = contc = contpd = 1;
            setPastelFeito(true);
            tipoPastelComboBox.setEnabled(false);
            editarRecheioComplementoButton.setEnabled(false);
            setValorTotal(valorTotal + getValorTp());
            valorTotalTextField.setText("" + getValorTotal());
            opcaoTable.clearSelection();
        } else {
            if (!dadosPreenchidos() && getCategoria() != 3) {
                JOptionPane.showMessageDialog(null, "Monte um pastel para poder salvar", "ERRO 0203", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_salvarPastelButtonActionPerformed

    private void opcaoTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcaoTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcaoTextField4ActionPerformed

    private void saborTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saborTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saborTextFieldActionPerformed

    private void opcaoTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcaoTextField4MouseClicked
        if (editavel == true) {
            for (int i = 0; i < listaPm.size(); i++) {
                if (listaPm.get(i).getSabor_c() != null && listaPm.get(i).getSabor_c().equals(opcaoTextField4.getText())) {
                    setIdEditado(i);
                }
            }
            opcaoTextField4.setText("");
            auxEditar = 4;
        }
    }//GEN-LAST:event_opcaoTextField4MouseClicked

    private void opcaoTextField5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcaoTextField5MouseClicked
        if (editavel == true) {
            JOptionPane.showMessageDialog(null, "teste: " + listaPm.size());
            for (int i = 0; i < listaPm.size(); i++) {
                if (listaPm.get(i).getSabor_c() != null && listaPm.get(i).getSabor_c().equals(opcaoTextField5.getText())) {
                    setIdEditado(i);
                }
            }
            opcaoTextField5.setText("");
            auxEditar = 5;
        }
    }//GEN-LAST:event_opcaoTextField5MouseClicked

    private void opcaoTextField6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcaoTextField6MouseClicked
        if (editavel == true) {
            for (int i = 0; i < listaPm.size(); i++) {
                if (listaPm.get(i).getSabor_c() != null && listaPm.get(i).getSabor_c().equals(opcaoTextField6.getText())) {
                    setIdEditado(i);
                }
            }
            opcaoTextField6.setText("");
            auxEditar = 6;
        }
    }//GEN-LAST:event_opcaoTextField6MouseClicked

    private void cancelarPedidoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarPedidoButtonActionPerformed
        try {
            listaPedido = pedidoDao.exibirUltimoPedido();
            p = listaPedido.get(0);
            pedidoDao.cancelarPedido(p);
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        resetarPedido();
    }//GEN-LAST:event_cancelarPedidoButtonActionPerformed

    private void valorTotalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorTotalTextFieldActionPerformed

    }//GEN-LAST:event_valorTotalTextFieldActionPerformed

    private void addPastelExtraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPastelExtraButtonActionPerformed
        if ("Adicionar Outro".equals(addPastelExtraButton.getText())) {
            setContp(contp + 1);
            pastelCountLabel.setText("Pastel " + getContp());
            addPastelExtraButton.setText("Cancelar");
            salvarPastelButton.setEnabled(true);
            setPastelFeito(false);
            tipoPastelComboBox.setEnabled(true);
            editarRecheioComplementoButton.setEnabled(true);
            contr = contc = contpd = 1;
        } else {
            if ("Cancelar".equals(addPastelExtraButton.getText())) {
                setContp(contp - 1);
                pastelCountLabel.setText("Pastel " + getContp());
                addPastelExtraButton.setText("Adicionar Outro");
                salvarPastelButton.setEnabled(false);
                listaPm.clear();
                setPastelFeito(true);
                cleanOpcText();
                tipoPastelComboBox.setEnabled(false);
                editarRecheioComplementoButton.setText("Editar");
                editarRecheioComplementoButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_addPastelExtraButtonActionPerformed

    private void confirmarValorAdicionalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarValorAdicionalButtonActionPerformed
        setValorTotal(getValorTotal() + Double.parseDouble(valorAdicionalTextField.getText()));
        valorTotalTextField.setText("" + getValorTotal());
        confirmarValorAdicionalButton.setEnabled(false);
    }//GEN-LAST:event_confirmarValorAdicionalButtonActionPerformed

    private void confirmarTaxaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarTaxaButtonActionPerformed
        setValorTotal(getValorTotal() + Double.parseDouble(taxaTextField.getText()));
        valorTotalTextField.setText("" + getValorTotal());
        confirmarTaxaButton.setEnabled(false);
    }//GEN-LAST:event_confirmarTaxaButtonActionPerformed

    private void tipoPastelComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipoPastelComboBoxMouseClicked

    }//GEN-LAST:event_tipoPastelComboBoxMouseClicked

    private void buscarTelTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarTelTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarTelTextFieldActionPerformed

    private void buscarTelTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarTelTextFieldMouseClicked
        buscarTelTextField.setText("");
    }//GEN-LAST:event_buscarTelTextFieldMouseClicked

    private void buscarTelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarTelButtonActionPerformed
        filtradaTel = true;
        filtradaCli = false;
        try {
            lerTabelaClienteFiltradaTel();
            //buscarTelTextField.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buscarTelButtonActionPerformed

    private void buscarNomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarNomeButtonActionPerformed
        filtradaCli = true;
        filtradaTel = false;
        try {
            lerTabelaClienteFiltradaNome();
            //buscarNomeTextField.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buscarNomeButtonActionPerformed

    private void buscarNomeTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarNomeTextFieldMouseClicked
        buscarNomeTextField.setText("");
    }//GEN-LAST:event_buscarNomeTextFieldMouseClicked

    private void verImpressaoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verImpressaoButtonActionPerformed

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new Nota(), getPageFormat(pj));
        try {
            pj.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
        
        JOptionPane.showMessageDialog(null, "Impressão Concluida");
    }//GEN-LAST:event_verImpressaoButtonActionPerformed

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
            java.util.logging.Logger.getLogger(NovoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NovoPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton addBebidaExtraButton;
    private javax.swing.JButton addPastelExtraButton;
    private javax.swing.JLabel bairroLabel;
    private javax.swing.JTextField bairroTextField;
    private javax.swing.JTextField bebidaCountTextField;
    private javax.swing.JPanel bebidaPanel;
    private javax.swing.JTable bebidasTable;
    private javax.swing.JButton buscarButton;
    private javax.swing.JButton buscarNomeButton;
    private javax.swing.JTextField buscarNomeTextField;
    private javax.swing.JButton buscarTelButton;
    private javax.swing.JTextField buscarTelTextField;
    private javax.swing.JPanel cCadastroPanel;
    private javax.swing.JPanel cPedidoPanel;
    private javax.swing.JButton cancelarPedidoButton;
    private javax.swing.JRadioButton clienteAntigoRadioButton;
    private javax.swing.ButtonGroup clienteBGroup;
    private javax.swing.JTable clienteTable;
    private javax.swing.JLabel complementoLabel;
    private javax.swing.JRadioButton complementoRadio;
    private javax.swing.JTextField complementoTextField;
    private javax.swing.JButton concluirPedidoButton;
    private javax.swing.JButton confirmarTaxaButton;
    private javax.swing.JButton confirmarValorAdicionalButton;
    private javax.swing.JRadioButton creditoRadio;
    private javax.swing.JTextField dataTextField;
    private javax.swing.JRadioButton debitoRadio;
    private javax.swing.JPanel designPanel;
    private javax.swing.JRadioButton dinheiroRadio;
    private javax.swing.JButton editarRecheioComplementoButton;
    private javax.swing.JPanel finalizarPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.ButtonGroup metodoPagamentoLabel;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JRadioButton novoClienteButton;
    private javax.swing.JLabel numeroLabel;
    private javax.swing.JTextField numeroTextField;
    private javax.swing.JLabel obsLabel;
    private javax.swing.JTextArea obsTextAreaLabel;
    private javax.swing.JLabel opcaoLabel1;
    private javax.swing.JLabel opcaoLabel2;
    private javax.swing.JLabel opcaoLabel3;
    private javax.swing.JLabel opcaoLabel4;
    private javax.swing.JLabel opcaoLabel5;
    private javax.swing.JLabel opcaoLabel6;
    private javax.swing.JTable opcaoTable;
    private javax.swing.JTextField opcaoTextField1;
    private javax.swing.JTextField opcaoTextField2;
    private javax.swing.JTextField opcaoTextField3;
    private javax.swing.JTextField opcaoTextField4;
    private javax.swing.JTextField opcaoTextField5;
    private javax.swing.JTextField opcaoTextField6;
    private javax.swing.JLabel pastelCountLabel;
    private javax.swing.ButtonGroup pedidoBGroup;
    private javax.swing.JRadioButton recheioRadio;
    private javax.swing.JLabel referenciaLabel;
    private javax.swing.JTextField referenciaTextField;
    private org.netbeans.examples.lib.timerbean.Timer relogioTimer;
    private javax.swing.JLabel ruaLabel;
    private javax.swing.JTextField ruaTextField;
    private javax.swing.JLabel saborLabel;
    private javax.swing.JTextField saborTextField;
    private javax.swing.JButton salvarBebidaPedido;
    private javax.swing.JButton salvarClienteButton;
    private javax.swing.JButton salvarPastelButton;
    private javax.swing.JPanel tabelaCientePanel;
    private javax.swing.JLabel tamanhoLabel;
    private javax.swing.JTextField tamanhoTextField;
    private javax.swing.JLabel taxaLabel;
    private javax.swing.JTextField taxaTextField;
    private javax.swing.JLabel telefoneLabel;
    private javax.swing.JTextField telefoneTextField;
    private javax.swing.JComboBox<Object> tipoPastelComboBox;
    private javax.swing.JTextField valorAdicionalTextField;
    private javax.swing.JTextField valorRecebidoTextField;
    private javax.swing.JLabel valorTotalLabel;
    private javax.swing.JTextField valorTotalTextField;
    private javax.swing.JButton verImpressaoButton;
    // End of variables declaration//GEN-END:variables
}
