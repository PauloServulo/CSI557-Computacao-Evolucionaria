/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import problema.Problema;
import solucao.Individuo;
import solucao.IndividuoDouble;
import solucao.PopulacaoDouble;

/**
 *
 * @author fernando
 */
public class DEReal implements Metodo {

    private Double minimo;
    private Double maximo;
    private Problema problema;
    
    // Criterio de parada
    private Integer gmax;
    // Numero de variaveis
    private Integer D;
    // Tamanho da populacao
    private Integer Np;
    // Coeficiente de mutacao
    private Double F;
    // Coeficiente de Crossover
    private Double Cr;

    public DEReal(Double minimo, Double maximo, Problema problema, Integer gmax, Integer D, Integer Np, Double F, Double Cr) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.problema = problema;
        this.gmax = gmax;
        this.D = D;
        this.Np = Np;
        this.F = F;
        this.Cr = Cr;
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

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    public Integer getGmax() {
        return gmax;
    }

    public void setGmax(Integer gmax) {
        this.gmax = gmax;
    }

    public Integer getD() {
        return D;
    }

    public void setD(Integer D) {
        this.D = D;
    }

    public Integer getNp() {
        return Np;
    }

    public void setNp(Integer Np) {
        this.Np = Np;
    }

    public Double getF() {
        return F;
    }

    public void setF(Double F) {
        this.F = F;
    }

    public Double getCr() {
        return Cr;
    }

    public void setCr(Double Cr) {
        this.Cr = Cr;
    }
    
    @Override
    public Individuo executar() {

        // Criacao da populacao inicial - X
        PopulacaoDouble populacao = new PopulacaoDouble(this.problema, this.minimo, this.maximo, this.D, this.Np);
        populacao.criar();
        
        // Avaliar a populacao inicial
        populacao.avaliar();
        
        // Nova populacao
        PopulacaoDouble novaPopulacao = new PopulacaoDouble();

        IndividuoDouble melhorSolucao = (IndividuoDouble) ((IndividuoDouble) populacao.getMelhorIndividuo()).clone();
        
        // Enquanto o criterio de parada nao for atingido
        for(int g = 1; g <= this.gmax; g++) {
            
            // Para cada vetor da populacao
            for(int i = 0; i < this.Np; i++) {
                
                // Selecionar r0, r1, r2
                
                // Gerar perturbacao - diferenca
                // Mutacao - r0 + F * perturbacao
                
                // Trial
                // Crossover
                // Selecao
                
            }
            
        }
        
    }
    
}
