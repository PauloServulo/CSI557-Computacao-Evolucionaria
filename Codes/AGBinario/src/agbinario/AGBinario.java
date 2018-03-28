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
       
//        Individuo ind = new Individuo(3, -100.0, 100.0, 3);
//        ind.criar();
//        System.out.println(ind.getCromossomos());
//        ind.decodificar();
//        System.out.println(ind.getDecodificacao());
//        System.out.println(ind.getVariaveis());
        
        Problema problema = new Problema();
//        problema.calcularFuncaoObjetivo(ind);
//        System.out.println(ind.getFuncaoObjetivo());

        Integer tamanho = 50;
        Double pCrossover = 0.8;
        Double pMutacao = 0.05;
        Integer geracoes = 1000;
        Integer precisao = 10;
        
        Double minimo = -100.0;
        Double maximo = 100.0;
        Integer nVariaveis = 100;

        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, precisao, minimo, maximo, nVariaveis);
        
        ag.executar();
        
    }
    
}
