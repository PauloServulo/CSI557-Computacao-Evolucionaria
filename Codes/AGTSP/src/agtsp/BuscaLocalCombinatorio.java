/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agtsp;

import java.util.Collections;

/**
 *
 * @author fernando
 */
public class BuscaLocalCombinatorio {
    
    Problema problema;

    public BuscaLocalCombinatorio(Problema problema) {
        this.problema = problema;
    }
    
    // Trocar U e V
    // First improvement
    public void buscaLocalSwap(Individuo individuo) {
        
        // Custo atual
        Double foAtual = individuo.getFuncaoObjetivo();
        
        for(int u = 0; u < individuo.getVariaveis().size() - 1; u++) {
            for(int v = u + 1; v < individuo.getVariaveis().size(); v++) {
                // Operar Swap(u, v)
                Collections.swap(individuo.getVariaveis(), u, v);
                // Calcular a diferença 
                problema.calcularFuncaoObjetivo(individuo);
                
                // Se existe melhora
                if ( individuo.getFuncaoObjetivo() < foAtual ) {
                    // Encerrar - first improvement
                    return;
                } else { // Não existe melhora             
                    // Desfazer a troca
                    Collections.swap(individuo.getVariaveis(), u, v);
                    // Retornar o valor da FO
                    individuo.setFuncaoObjetivo(foAtual);
                }
            }
        }
        
    }
    
    // Remover U e inserir após V
    public void buscaLocalRemoverUV(Individuo individuo) {
        
        Double foAtual = individuo.getFuncaoObjetivo();
        
        for(int u = 0; u < individuo.getVariaveis().size()-1; u++) {
            for(int v = u + 1; v < individuo.getVariaveis().size(); v++) {
                // Recuperar o valor na posição U
                Integer valorU = individuo.getVariaveis().get(u);
                
                // Remover U
                individuo.getVariaveis().remove(u);
                // Inserir após V
                individuo.getVariaveis().add(v, valorU);
                
                // Calcular o Custo
                problema.calcularFuncaoObjetivo(individuo);
                
                // Se existe melhora
                if (individuo.getFuncaoObjetivo() < foAtual) {
                    // Encerrar - first improvement
                    return;
                } else {
                    // Desfazer a troca
                    // Remover de V
                    individuo.getVariaveis().remove(v);
                    // Inserir novamente em U
                    individuo.getVariaveis().add(u, valorU);
                    // Valor atual da FO
                    individuo.setFuncaoObjetivo(foAtual);
                    
                }
                
            }
        }
        
    }
    
}
