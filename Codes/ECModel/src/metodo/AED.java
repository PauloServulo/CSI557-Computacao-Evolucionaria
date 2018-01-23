/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import problema.Problema;
import solucao.IndividuoBinario;
import solucao.IndividuoDouble;
import solucao.PopulacaoBinario;

/**
 *
 * @author fernando
 */
public class AED implements Metodo {

    private double min;
    private double max;
    private int precisao;

    private int tamPop;
    private int numVar;
    private int gmax;
    private int numBest;
    private int numEst;

    private Problema problema;

    public AED(double min, double max, int precisao, int tamPop, int numVar, int gmax, int numBest, int numEst, Problema problema) {
        this.min = min;
        this.max = max;
        this.precisao = precisao;
        this.tamPop = tamPop;
        this.numVar = numVar;
        this.gmax = gmax;
        this.numBest = numBest;
        this.numEst = numEst;
        this.problema = problema;
    }



    @Override
    public void executar() {

        PopulacaoBinario populacao = new PopulacaoBinario(tamPop, problema);
        populacao.criar();
        populacao.avaliar(this.min, this.max, this.precisao);
        
        PopulacaoBinario novaPopulacao = new PopulacaoBinario(tamPop, problema);

        for (int g = 1; g < this.gmax; g++) {

            double proporcao[] = new double[this.numVar];
            // Selecionar melhores - numBest
            for (int p = 0; p < this.numVar; p++) {
                int soma = 0;
                for (int b = 0; b < this.numBest; b++) {
                    if (populacao.getIndividuos().get(b).getCromossomos().get(p) == 1) {
                        soma++;
                    }
                }
                // Definir as proporcoes
                proporcao[p] = (double) soma / this.numBest;
            }

            // Estimar - gerar solucoes considerando a proporcao
            Random rnd = new Random();
            novaPopulacao.getIndividuos().clear(); 
            for (int i = 0; i < this.numEst; i++) {
                IndividuoBinario ind = new IndividuoBinario(this.numVar);
                for (int p = 0; p < this.numVar; p++) {
                    if (rnd.nextDouble() <= proporcao[p]) {
                        ind.getCromossomos().add(1);
                    } else {
                        ind.getCromossomos().add(0);
                    }

                }

                IndividuoDouble indDbl = ind.decodificar(min, max, precisao);
                
                problema.calcularFuncaoObjetivo(indDbl);
                ind.setFuncaoObjetivo(indDbl.getFuncaoObjetivo());
                novaPopulacao.getIndividuos().add(ind);
                //System.out.println(indDbl.getFuncaoObjetivo());

            }
                        
            // Populacao + NovaPopulacao
            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
            // Ordena individuos
            Collections.sort(populacao.getIndividuos());
            // Corta em TamPop
            populacao.getIndividuos().subList(this.tamPop, populacao.getIndividuos().size()).clear();

            System.out.println(populacao.getMelhorIndividuo().getFuncaoObjetivo());

            //System.exit(0);
            
        }

    }

}
