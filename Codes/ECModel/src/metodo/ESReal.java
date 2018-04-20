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
public class ESReal implements Metodo {

    // Parametros - problema - DeJong
    private Double minimo;
    private Double maximo;
    private Integer nVariaveis;
    private Problema problema;
    
    // Parametros - ES
    private Integer mu; // Tamanho da populacao
    private Integer lambda; // numero de descendentes
    private Integer geracoes; // criterio de parada
    private Double pMutacao; // mutacao - aplicacao ao descendente - variacao/perturbacao

    public ESReal(Double minimo, Double maximo, Integer nVariaveis, Problema problema, Integer mu, Integer lambda, Integer geracoes, Double pMutacao) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVariaveis = nVariaveis;
        this.problema = problema;
        this.mu = mu;
        this.lambda = lambda;
        this.geracoes = geracoes;
        this.pMutacao = pMutacao;
    }
    
    @Override
    public Individuo executar() {
        
        // Geracao da populacao inicial - aleatoria - tamanho mu
        PopulacaoDouble populacao = new PopulacaoDouble(problema, minimo, maximo, nVariaveis, mu);     
        populacao.criar();
        
        // Avaliar
        populacao.avaliar();
        
        // Populacao - descendentes
        PopulacaoDouble descendentes = new PopulacaoDouble();
        
        // Criterio de parada - numero de geracoes
        for(int g = 1; g < this.geracoes; g++) {
            
            // Para cada individuo, gerar lambda/mu descendentes
            for(int i = 0; i < populacao.getIndividuos().size(); i++) {
                
                // Gerar lambda/mu descendentes
                for(int d = 0; d < lambda / mu; d++) {
                    
                    // Clonar individuo
                    IndividuoDouble filho = (IndividuoDouble)populacao.getIndividuos().get(i).clone();
                    
                    // Aplicar mutacao
                    
                    // Avaliar
                    
                    // Inserir na nova populacao
                    
                }
                
            }
            
        }
        
        
    }
    
}
