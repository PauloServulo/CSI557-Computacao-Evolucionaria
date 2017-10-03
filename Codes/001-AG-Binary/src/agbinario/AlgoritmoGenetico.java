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
    
    int tamPop;
    double pCrossover;
    double pMutacao;
    int geracoes;
    
    int precisao;
    double min;
    double max;
    int nvar;

    public AlgoritmoGenetico(int tamPop, double pCrossover, double pMutacao, int geracoes, int precisao, double min, double max, int nvar) {
        this.tamPop = tamPop;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.precisao = precisao;
        this.min = min;
        this.max = max;
        this.nvar = nvar;
    }
    
    Individuo melhorSolucao;
    Populacao populacao;

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }
    
    public void executar() {
        
        populacao = new Populacao(precisao, min, max, nvar, tamPop);
        
        // Criar a populacao
        populacao.criarPopulacao();
        // Avaliacao da populacao inicial
        populacao.avaliar();
        
       // Recuperar o melhor individuo
       melhorSolucao = populacao.getMelhorIndividuo();
       
       System.out.println("Solucao inicial: " 
                + melhorSolucao.getFuncaoObjetivo());
        
      // Geracoes - criterio de parada
      for(int gen = 1; gen <= geracoes; gen++) {
          
          // Reproducao
          // - Selecionar pais
          // - Operar - crossover
          // - Operar - mutacao
          
          // Avaliar descendentes
          
          // Definir sobrevivencia - pop + descendentes
      }
        
    }
    
}
