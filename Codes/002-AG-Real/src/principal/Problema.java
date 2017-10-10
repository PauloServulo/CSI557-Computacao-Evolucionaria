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
public class Problema {
    
    public static void calcularFuncaoObjetivo(Individuo individuo) {
        
        Double soma = 0.0;
        
        for(Double var : individuo.getCromossomos()) {
            soma +=  Math.pow(var, 2);
        }
        
        individuo.setFuncaoObjetivo(soma);        
        
    }
    
}
