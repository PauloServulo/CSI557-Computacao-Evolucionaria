/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag.tsp;

/**
 *
 * @author fernando
 */
public class AGTSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Problema tsp = new Problema("/Users/fernando/Dropbox/Dados/Desenvolvimento/Netbeans/CSI557-Computacao-Evolucionaria/Codes/instances/tsplib/att48.tsp");
        System.out.println(tsp);
        
        //Individuo individuo = new Individuo(tsp.dimensao); 
        //individuo.criar();
        
        //tsp.calcularFuncaoObjetivo(individuo);
        //System.out.println(individuo);
        AlgoritmoGenetico ag = new AlgoritmoGenetico(100, 0.8, 0.05, 100, tsp);
        ag.executar();
        
    }
    
}
