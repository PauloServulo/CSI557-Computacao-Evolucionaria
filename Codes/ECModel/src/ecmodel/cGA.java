/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import metodo.CompactGA;
import problema.Problema;
import problema.ProblemaDeJong;

/**
 *
 * @author fernando
 */
public class cGA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        double min = -100.0;
        double max = 100.0;
        int precisao = 10;
        
        int n = 10;
        int l = 100;
        int gmax = 100;
                
        Problema problema = new ProblemaDeJong(l);
        
        CompactGA cga = new CompactGA(problema, min, max, precisao, n, l, gmax);
        cga.executar();
        
    }
    
}
