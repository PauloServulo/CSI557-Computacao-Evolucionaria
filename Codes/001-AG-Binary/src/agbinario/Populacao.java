/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agbinario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author fernando
 */
public class Populacao {
    int precisao;
    double min;
    double max;
    int nvar;

    public Populacao() {
    }
    
    public Populacao(int precisao, double min, double max, int nvar, int tamanho) {
        this.precisao = precisao;
        this.min = min;
        this.max = max;
        this.nvar = nvar;
        this.tamanho = tamanho;
    }
    
    int tamanho;

    public int getPrecisao() {
        return precisao;
    }

    public void setPrecisao(int precisao) {
        this.precisao = precisao;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public int getNvar() {
        return nvar;
    }

    public void setNvar(int nvar) {
        this.nvar = nvar;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public ArrayList<Individuo> getIndividuos() {
        return individuos;
    }

    public void setIndividuos(ArrayList<Individuo> individuos) {
        this.individuos = individuos;
    }
    
    ArrayList<Individuo> individuos;
    
    // Ranking
    // Sobrevivencia
    // Ordenacao
    
    // Criar a populacao inicial
    public void criarPopulacao() {
        
        individuos = new ArrayList<>();
        
        for (int i = 0; i < this.getTamanho(); i++) {
            Individuo individuo = 
                    new Individuo(precisao, min, max, nvar);
            individuos.add(individuo);
            
        }
        
    }
    
    // Avaliar a populacao
    public void avaliar() {
        for(Individuo individuo : this.getIndividuos()) {
            Problema.calcularFuncaoObjetivo(individuo);
        }
    }
    
    public Individuo getMelhorIndividuo() {
        return Collections.min(individuos);
    }
}
