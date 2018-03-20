/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agbinario;

/**
 *
 * @author fernando
 */
public class AlgoritmoGenetico {
    
    // Tamanho da população
    Integer tamanho;
    // Taxa de crossover - operador principal
    Double pCrossover;
    // Taxa de mutação - operador secundário
    Double pMutacao;
    // Critério de parada - número de gerações
    Integer geracoes;
    
    // Dados do problema
    // Problema - DeJong
    Problema problema;
    // Precisão
    Integer precisao;
    // Mínimo
    Double minimo;
    // Máximo
    Double maximo;    
    // Variáveis
    Integer nVariaveis;

    public AlgoritmoGenetico(Integer tamanho, Double pCrossover, Double pMutacao, Integer geracoes, Problema problema, Integer precisao, Double minimo, Double maximo, Integer nVariaveis) {
        this.tamanho = tamanho;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.problema = problema;
        this.precisao = precisao;
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVariaveis = nVariaveis;
    }
    
    Populacao populacao;
    Populacao novaPopulacao;
    Individuo melhorSolucao;

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }
    
    public void executar() {
        
        populacao = new Populacao(precisao, minimo, maximo, nVariaveis, tamanho, problema);
        novaPopulacao = new Populacao(precisao, minimo, maximo, nVariaveis, tamanho, problema);
        
        // Criar a população
        populacao.criar();
        
        // Avaliar
        populacao.avaliar();
        
        // Recuperar o melhor indivíduo
        
        // Enquanto o critério de parada não for satisfeito - Gerações
        for(int g = 1; g <= geracoes; g++) {
            
        }
        
        
        
    }
    
}
