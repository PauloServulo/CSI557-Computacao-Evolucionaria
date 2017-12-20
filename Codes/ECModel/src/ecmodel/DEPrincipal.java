/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import metodo.EvolucaoDiferencial;
import problema.Problema;
import problema.ProblemaDeJong;

/**
 *
 * @author fernando
 */
public class DEPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        double min = -100.0;
        double max = 100.0;
        int gmax = 1000; // critério de parada
        int D = 100; // numero de variaveis
        int Np = 100; // tamanho da população
        double F = 0.1; // coeficiente de mutação
        double Cr = 0.8; // coeficiente de crossover
        Problema problema = new ProblemaDeJong(D);
        
        EvolucaoDiferencial de = new EvolucaoDiferencial(min, max, gmax, D, Np, F, Cr, problema);
        de.executar();

    }

}
