/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agtsp;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author fernando
 */
public class Individuo implements Comparable<Individuo>{
    
    // Genótipo+Fenotipo
    // Representação - ordem de visitação das cidades
    private ArrayList<Integer> variaveis;
    // Custo da função objetivo
    Double funcaoObjetivo;
        
    // Número de variáveis
    Integer nVar;

    public Individuo(Integer nVar) {
        this.nVar = nVar;
        this.variaveis = new ArrayList<>();
    }

    public ArrayList<Integer> getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(ArrayList<Integer> variaveis) {
        this.variaveis = variaveis;
    }

    public Double getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    public void setFuncaoObjetivo(Double funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }

    public Integer getnVar() {
        return nVar;
    }

    public void setnVar(Integer nVar) {
        this.nVar = nVar;
    }
    
    // Gerar o genótipo
    public void criar() {
        
        this.variaveis = new ArrayList<>();
                      
        for(int i = 1; i <= this.getnVar(); i++) {
            this.variaveis.add(i);
        }       
        Collections.shuffle(this.variaveis);
    }
     
    @Override
    public int compareTo(Individuo o) {
        return this.getFuncaoObjetivo()
                .compareTo(o.getFuncaoObjetivo());
    }

    @Override
    public String toString() {
        return "Individuo{" + "variaveis=" + variaveis + ", funcaoObjetivo=" + funcaoObjetivo + ", nVar=" + nVar + '}';
    }
    
    
    
}
