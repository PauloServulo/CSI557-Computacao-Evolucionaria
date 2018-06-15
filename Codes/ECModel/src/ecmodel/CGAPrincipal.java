/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import metodo.CompactGA;
import problema.Problema;
import problema.ProblemaDeJong;
import problema.ProblemaRastrigin;
import solucao.Individuo;

/**
 *
 * @author fernando
 */
public class CGAPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
         Double minimo = -5.12;
         Double maximo = 5.12;
         Integer precisao = 10;
     
         Integer n = 100;
         Integer l = 100;
         
         Integer gmax = 10000;
         
         Problema problema = new ProblemaRastrigin(l);
         
         CompactGA cGA = new CompactGA(problema, minimo, maximo, precisao, n, l, gmax);
         Individuo resultado = cGA.executar();    
         System.out.println(resultado);
                  
    }
    
}
