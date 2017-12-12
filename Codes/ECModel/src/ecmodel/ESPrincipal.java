/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import metodo.EvolutionEstrategy;
import problema.Problema;
import problema.ProblemaDeJong;

/**
 *
 * @author fernando
 */
public class ESPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        double min = -100.0;
        double max = 100.0;
        int nvar = 100;
        int mu = 100;
        int lambda = 1000;
        int geracoes = 100;
        double pMutacao = 1.0;
        
        Problema problema = new ProblemaDeJong(100);
        EvolutionEstrategy es = new EvolutionEstrategy(min, max, nvar, mu, lambda, problema, geracoes, pMutacao);
        
        es.executar();
        
        
    }
    
}
