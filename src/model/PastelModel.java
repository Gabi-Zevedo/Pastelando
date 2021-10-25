/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gsa38
 */
public class PastelModel {
    int id_pastel;
    int pastel_doce_fk;
    int tipo_pastel_fk;
    int recheio_fk;
    int complemento_fk;
    int pedido_fk;
    String sabor_r;
    String sabor_c;
    String sabor_pd;
    double valor_r;
    double valor_tp;
    double valor_pd;

    public int getPedido_fk() {
        return pedido_fk;
    }

    public double getValor_r() {
        return valor_r;
    }

    public void setValor_r(double valor_r) {
        this.valor_r = valor_r;
    }

    public double getValor_tp() {
        return valor_tp;
    }

    public void setValor_tp(double valor_tp) {
        this.valor_tp = valor_tp;
    }

    public double getValor_pd() {
        return valor_pd;
    }

    public void setValor_pd(double valor_pd) {
        this.valor_pd = valor_pd;
    }

    public void setPedido_fk(int pedido_fk) {
        this.pedido_fk = pedido_fk;
    }

    public String getSabor_pd() {
        return sabor_pd;
    }

    public void setSabor_pd(String sabor_pd) {
        this.sabor_pd = sabor_pd;
    }
    
    
    
    public int getId_pastel() {
        return id_pastel;
    }

    public void setId_pastel(int id_pastel) {
        this.id_pastel = id_pastel;
    }

    public int getPastel_doce_fk() {
        return pastel_doce_fk;
    }

    public void setPastel_doce_fk(int pastel_doce_fk) {
        this.pastel_doce_fk = pastel_doce_fk;
    }

    public int getTipo_pastel_fk() {
        return tipo_pastel_fk;
    }

    public void setTipo_pastel_fk(int tipo_pastel_fk) {
        this.tipo_pastel_fk = tipo_pastel_fk;
    }

    public int getRecheio_fk() {
        return recheio_fk;
    }

    public void setRecheio_fk(int recheio_fk) {
        this.recheio_fk = recheio_fk;
    }

    public int getComplemento_fk() {
        return complemento_fk;
    }

    public void setComplemento_fk(int complemento_fk) {
        this.complemento_fk = complemento_fk;
    }

    public String getSabor_r() {
        return sabor_r;
    }

    public void setSabor_r(String sabor_r) {
        this.sabor_r = sabor_r;
    }

    public String getSabor_c() {
        return sabor_c;
    }

    public void setSabor_c(String sabor_c) {
        this.sabor_c = sabor_c;
    }

    
}
