/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agtsp;

import java.util.ArrayList;
import java.util.Arrays;

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

//        testeBerlin(problema);
//        return;
        
        Integer tamanho = 50;
        Double pCrossover = 0.8;
        Double pMutacao = 0.015;
        Integer geracoes = 1000;
        Integer nvar = problema.dimensao;
        
        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, nvar);

        ag.executar();
        
    }
    
    public static void testeBerlin(Problema problema) {
        ArrayList<Integer> bestBerlin = new ArrayList<>( Arrays.asList(1,49,32,45,19,41,8,9,10,43,33,51,11,52,14,13,47,26,27,28,12,25,4,6,15,5,24,48,38,37,40,39,36,35,34,44,46,16,29,50,20,23,30,2,7,42,21,17,3,18,31,22) );
 
        Individuo best = new Individuo(problema.dimensao);
        best.setVariaveis(bestBerlin);
        problema.calcularFuncaoObjetivo(best);
        
        System.out.println(best);
    }
    
}