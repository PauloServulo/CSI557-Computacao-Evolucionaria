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
public class AGBinario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Individuo ind = new Individuo(5, -100.0, 100.0, 5);
        ind.criar();
        System.out.println(ind.getCromossomos());
        
        
    }
    
}
