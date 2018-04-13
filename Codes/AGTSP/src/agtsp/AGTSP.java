/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agtsp;

/**
 *
 * @author fernando
 */
public class AGTSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Problema problema = new Problema("/Volumes/MacData/Dropbox/Dados/Desenvolvimento/Netbeans/CSI557-Computacao-Evolucionaria/Codes/instances/tsplib/berlin52.tsp");
        
        System.out.println(problema);

        Integer tamanho = 50;
        Double pCrossover = 0.8;
        Double pMutacao = 0.05;
        Integer geracoes = 1000;
        Integer nvar = problema.dimensao;
        
        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, nvar);

        ag.executar();
        
    }
    
}
