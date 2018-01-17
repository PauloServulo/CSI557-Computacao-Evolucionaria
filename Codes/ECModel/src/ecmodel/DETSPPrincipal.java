/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import metodo.EvolucaoDiferencialTSP;
import problema.Problema;
import problema.ProblemaTSP;

/**
 *
 * @author fernando
 */
public class DETSPPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Problema problema = new ProblemaTSP("/Users/fernando/Dropbox/Dados/Desenvolvimento/Netbeans/CSI557-Computacao-Evolucionaria/Codes/instances/tsplib/berlin52.tsp");
        Problema problema = new ProblemaTSP("/home/fernando/Dropbox/Dados/Desenvolvimento/Netbeans/CSI557-Computacao-Evolucionaria/Codes/instances/tsplib/berlin52.tsp");
        
        double min = 0.0;
        double max = 1.0;
        int gmax = 100; // critério de parada
        int D = problema.getDimensao(); // numero de variaveis
        int Np = 100; // tamanho da população
        double F = 0.1; // coeficiente de mutação
        double Cr = 0.8; // coeficiente de crossover
        
        EvolucaoDiferencialTSP deTSP = new EvolucaoDiferencialTSP(min, max, gmax, D, Np, F, Cr, problema);
        deTSP.executar();
                
    }
    
}
