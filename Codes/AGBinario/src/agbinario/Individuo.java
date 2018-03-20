/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agbinario;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author fernando
 */
public class Individuo {
    
    // Genotipo
    private ArrayList<Integer> cromossomos;
    // Fenotipo
    private ArrayList<Double> variaveis;
    // Decodificado - binário para inteiro
    private ArrayList<Integer> decodificacao;
    // Custo da função objetivo
    Double funcaoObjetivo;
    
    // Precisão
    Integer precisao;
    // Valor mínimo
    Double minimo;
    // Valor máximo
    Double maximo;
    
    // Número de variáveis
    Integer nVar;

    public Individuo(Integer precisao, Double minimo, Double maximo, Integer nVar) {
        this.precisao = precisao;
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVar = nVar;
    }

    public ArrayList<Integer> getCromossomos() {
        return cromossomos;
    }

    public void setCromossomos(ArrayList<Integer> cromossomos) {
        this.cromossomos = cromossomos;
    }

    public ArrayList<Double> getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(ArrayList<Double> variaveis) {
        this.variaveis = variaveis;
    }

    public ArrayList<Integer> getDecodificacao() {
        return decodificacao;
    }

    public void setDecodificacao(ArrayList<Integer> decodificacao) {
        this.decodificacao = decodificacao;
    }

    public Double getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    public void setFuncaoObjetivo(Double funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }

    public Integer getPrecisao() {
        return precisao;
    }

    public void setPrecisao(Integer precisao) {
        this.precisao = precisao;
    }

    public Double getMinimo() {
        return minimo;
    }

    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }

    public Double getMaximo() {
        return maximo;
    }

    public void setMaximo(Double maximo) {
        this.maximo = maximo;
    }

    public Integer getnVar() {
        return nVar;
    }

    public void setnVar(Integer nVar) {
        this.nVar = nVar;
    }
    
    // Gerar o genótipo
    public void criar() {
        
        this.cromossomos = new ArrayList<>();
        
        Random rnd = new Random();
        
        for(int i = 0; i < this.getnVar(); i++) {
            for(int j = 0; j < this.getPrecisao(); j++) {
                this.getCromossomos().add( rnd.nextInt(2) );
            }
        }
        
    }
    
    // Genótipo -> Fenótipo : Binário -> Real
    public void decodificar() {
        
        this.decodificacao = new ArrayList<>();
        this.variaveis = new ArrayList<>();


       int valor;
       Double real;
       
       for(int i = 0; i < this.getnVar(); i++) {
       
           valor = 0;
           
           for(int j = 0; j < this.getPrecisao(); j++) {
               
               valor += Math.pow(2, this.getPrecisao() - j - 1)
                       * this.getCromossomos().get( i * this.getPrecisao() + j );
               
           }
           
           this.getDecodificacao().add(valor);
           
           real = (valor * ( this.getMaximo() - this.getMinimo() )
                    / ( Math.pow(2, this.getPrecisao()) - 1.0 )) 
                   + this.getMinimo();
           
           this.getVariaveis().add(real);
           
       }

        
    }
    
    
}
