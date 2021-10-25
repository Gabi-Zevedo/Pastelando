/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import model.CardapioModel;
import model.PastelModel;
import model.PedidoModel;
import dao.PedidoDao;

/**
 *
 * @author gsa38
 */
public class PrintController {
    
    private PedidoModel pedido = new PedidoModel();
    List<CardapioModel> listaB;
    List<PastelModel> listaPastel = new ArrayList<>();
    List<PedidoModel> listaPedido;
    private PedidoDao pedidoDao = new PedidoDao();
    
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
    
    public class Nota implements Printable{

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            int result = NO_SUCH_PAGE;
            if(pageIndex == 0){
                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
                
                FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 5));
                
                
                
                try{
            /*Draw Header*/
            int y=15;
            int yShift = 8;
            int headerRectHeight=10;
            int headerRectHeighta=40;
            //variaveis
            listaPedido = pedidoDao.exibirUltimoPedido();
            pedido = listaPedido.get(0);
            
            
            g2d.setFont(new Font("Monospaced",Font.PLAIN,7));
            g2d.drawString("----------------------------------", 12, y); y+=yShift;
            g2d.drawString("PASTELANDO CONTA", 12, y); y+=yShift;
            g2d.drawString("---------------------------------------------------------------------", 12, y); y+=headerRectHeight;
            
            
            g2d.drawString("----------------------------------", 12, y); y+=yShift;
            
            g2d.drawString("TESTE1:                 VALOR1:", 12, y); y+=yShift;
            g2d.drawString("----------------------------------", 12, y); y+=yShift;
            
            g2d.drawString(" "+pedido.getData_pedido()+"                "+pedido.getValor_total()+" ", 12, y); y+=yShift;
            g2d.drawString("----------------------------------", 12, y); y+=yShift;
            g2d.drawString("              ARIGATO             ", 12, y); y+=yShift;
            return (PAGE_EXISTS);
          } catch (Exception ex)
    {
        System.out.println("ERROR : "+ex);
    }
    return(PAGE_EXISTS);
}
    else
    return(NO_SUCH_PAGE);
    

}
    }
}
