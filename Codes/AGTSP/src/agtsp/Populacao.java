/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agtsp;

import java.util.ArrayList;

/**
 *
 * @author fernando
 */
public class Populacao {
        
    // Número de variáveis
    Integer nVar;

    // Tamanho da população
    Integer tamanho;
    
    // Conjunto de indivíduos
    ArrayList<Individuo> individuos;

    // Problema
    Problema problema;

    public Populacao(Integer nVar, Integer tamanho, Problema problema) {
        this.nVar = nVar;
        this.tamanho = tamanho;
        this.problema = problema;
        this.individuos = new ArrayList<>();
    }
    
    public Integer getnVar() {
        return nVar;
    }

    public void setnVar(Integer nVar) {
        this.nVar = nVar;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    public ArrayList<Individuo> getIndividuos() {
        return individuos;
    }

    public void setIndividuos(ArrayList<Individuo> individuos) {
        this.individuos = individuos;
    }
    
    // Criar a população
    public void criar() {
        
        this.individuos = new ArrayList<>();
        
        for(int i = 0; i < this.getTamanho(); i++) {
            
            Individuo individuo = new Individuo(this.nVar);
            individuo.criar();
            
            this.getIndividuos().add(individuo);
            
        }
        
    }
    
    // Avaliar a população
    public void avaliar() {
        
        for(Individuo individuo : this.getIndividuos()) {
            problema.calcularFuncaoObjetivo(individuo);
        }
        
    }
    
    
}
