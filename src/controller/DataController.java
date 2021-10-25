/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * @author gsa38
 */
public class DataController {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
    Date d = new Date();
    String diasemana = "";
 

    public String diaSemana() {
        switch (d.getDay()) {
            case 0:
                diasemana = "Domingo";
                break;
            case 1:
                diasemana = "Segunda-Feira";
                break;
            case 2:
                diasemana = "Terça-Feira";
                break;
            case 3:
                diasemana = "Quarta-Feira";
                break;
            case 4:
                diasemana = "Quinta-Feira";
                break;
            case 5:
                diasemana = "Sexta-Feira";
                break;
            case 6:
                diasemana = "Sabado";
                break;
        }
        return diasemana;
    }
    
    public String getData(){
        return data.format(d);
    }
    
    
    public void dataTxt(JTextField dataText){
            dataText.setText(diaSemana()+ ", "+ sdf.format(d));
    }
    public String dataNota(){
        String dataNota;
        dataNota = (""+sdf.format(d));
        return dataNota;
    }
}
