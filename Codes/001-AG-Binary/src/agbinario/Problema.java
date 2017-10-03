/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agbinario;

/**
 *
 * @author fernando
 */
public class Problema {
    
    public static void calcularFuncaoObjetivo(Individuo individuo) {
        
        Double soma = 0.0;
        individuo.decodificar();
        
        for(Double var : individuo.getVariaveis()) {
            soma +=  Math.pow(var, 2);
        }
        
        individuo.setFuncaoObjetivo(soma);
        
        
    }
    
}
