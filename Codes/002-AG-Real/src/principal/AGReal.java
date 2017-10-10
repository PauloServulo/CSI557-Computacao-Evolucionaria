/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

/**
 *
 * @author fernando
 */
public class AGReal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int tamPop = 100;
        double pCrossover = 0.8;
        double pMutacao = 0.05;
        int geracoes = 1000;
        
        double min = -100.0;  
        double max = 100.0;
        int nvar = 100;
        
        AlgoritmoGenetico ag = 
                new AlgoritmoGenetico(tamPop, 
                        pCrossover, 
                        pMutacao, 
                        geracoes, 
                        min, max, nvar);
        // Processa o AG - min funcao
        ag.executar();
        Individuo melhor = ag.getMelhorSolucao();
        
        System.out.println("Melhor solucao = " + melhor);        
    }
    
}
