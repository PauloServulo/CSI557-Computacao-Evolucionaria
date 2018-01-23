/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import metodo.AED;
import problema.Problema;
import problema.ProblemaDeJong;

/**
 *
 * @author fernando
 */
public class AEDPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        double min = -100.0;
        double max = 100.0;
        int precisao = 10;
                
        int gmax = 100; // critério de parada
        
        int tamPop = 100; 
        int numVar = 10 * precisao;
        
        int numBest = 100;
        int numEst = 100;

        Problema problema = new ProblemaDeJong(numVar);
        
        AED aed = new AED(min, max, precisao, tamPop, numVar, gmax, numBest, numEst, problema);
        
        aed.executar();
        
    }
    
}
