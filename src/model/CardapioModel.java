/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gsa38
 */
public class CardapioModel {
    private String sabor_r;
    private String sabor_c;
    private String sabor_pd;
    private String sabor_m;
    private String tipo_b;
    private double tamanho;
    private double valor_refri;
    private double valor_pd;
    private double valor_tp;
    private double valor_r;
    private int id_complemento;
    private int id_bebida;
    private int id_pd;
    private int id_recheio;
    private int id_tipo;
    private int categoria;

    
    public CardapioModel(){
        
    }

    public double getValor_r() {
        return valor_r;
    }

    public void setValor_r(double valor_r) {
        this.valor_r = valor_r;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getId_complemento() {
        return id_complemento;
    }

    public void setId_complemento(int id_complemento) {
        this.id_complemento = id_complemento;
    }

    public int getId_bebida() {
        return id_bebida;
    }

    public void setId_bebida(int id_bebida) {
        this.id_bebida = id_bebida;
    }

    public int getId_pd() {
        return id_pd;
    }

    public void setId_pd(int id_pd) {
        this.id_pd = id_pd;
    }

    public int getId_recheio() {
        return id_recheio;
    }

    public void setId_recheio(int id_recheio) {
        this.id_recheio = id_recheio;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
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

    public String getSabor_pd() {
        return sabor_pd;
    }

    public void setSabor_pd(String sabor_pd) {
        this.sabor_pd = sabor_pd;
    }

    public String getSabor_m() {
        return sabor_m;
    }

    public void setSabor_m(String sabor_m) {
        this.sabor_m = sabor_m;
    }

    public String getTipo_b() {
        return tipo_b;
    }

    public void setTipo_b(String tipo_b) {
        this.tipo_b = tipo_b;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public double getValor_refri() {
        return valor_refri;
    }

    public void setValor_refri(double valor_refri) {
        this.valor_refri = valor_refri;
    }

    public double getValor_pd() {
        return valor_pd;
    }

    public void setValor_pd(double valor_pd) {
        this.valor_pd = valor_pd;
    }

    public double getValor_tp() {
        return valor_tp;
    }

    public void setValor_tp(double valor_tp) {
        this.valor_tp = valor_tp;
    }

    @Override
    public String toString() {
        return getSabor_m(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
