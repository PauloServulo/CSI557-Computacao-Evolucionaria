/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag.tsp;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class AlgoritmoGenetico {

    int tamPop;
    double pCrossover;
    double pMutacao;
    int geracoes;

    Individuo melhorSolucao;

    Populacao populacao;
    Populacao novaPopulacao;

    Problema problema;

    public AlgoritmoGenetico(int tamPop, double pCrossover, double pMutacao, int geracoes, Problema problema) {
        this.tamPop = tamPop;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.problema = problema;
    }

    public int getTamPop() {
        return tamPop;
    }

    public void setTamPop(int tamPop) {
        this.tamPop = tamPop;
    }

    public double getpCrossover() {
        return pCrossover;
    }

    public void setpCrossover(double pCrossover) {
        this.pCrossover = pCrossover;
    }

    public double getpMutacao() {
        return pMutacao;
    }

    public void setpMutacao(double pMutacao) {
        this.pMutacao = pMutacao;
    }

    public int getGeracoes() {
        return geracoes;
    }

    public void setGeracoes(int geracoes) {
        this.geracoes = geracoes;
    }

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }

    public Populacao getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Populacao populacao) {
        this.populacao = populacao;
    }

    public Populacao getNovaPopulacao() {
        return novaPopulacao;
    }

    public void setNovaPopulacao(Populacao novaPopulacao) {
        this.novaPopulacao = novaPopulacao;
    }

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    public void executar() {

        populacao = new Populacao(tamPop, problema);
        novaPopulacao = new Populacao(tamPop, problema);

        // Geração da população inicial
        populacao.criar();

        // Avaliar a população inicial
        populacao.avaliar();

        try {
            // Recuperar o melhor indivíduo
            melhorSolucao = (Individuo) populacao.getMelhorIndividuo().clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AlgoritmoGenetico.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Informação sobre a solução inicial 
        System.out.println("Solução inicial: " + melhorSolucao.getFuncaoObjetivo());

        Random rnd = new Random();
        int pai1 = 0, pai2 = 0;

        // Número de gerações - critério de parada
        for (int gen = 1; gen < geracoes; gen++) {

            // Reprodução
            novaPopulacao.getIndividuos().clear();

            // Percorrer a populacao corrente
            // E selecionar os pais
            for (int j = 0; j < tamPop; j++) {

                // Se o crossover deve ser aplicado
                if (rnd.nextDouble() <= pCrossover) {
                    // -- Selecionar os pais
                    pai1 = rnd.nextInt(tamPop);
                    do {
                        pai2 = rnd.nextInt(tamPop);
                    } while (pai1 == pai2);

                    Individuo filho1 = new Individuo(problema.dimensao);
                    Individuo filho2 = new Individuo(problema.dimensao);

                    crossoverOX(this.populacao.getIndividuos().get(pai1), this.populacao.getIndividuos().get(pai2), filho1, filho2);

                    System.out.println(this.populacao.getIndividuos().get(pai1));
                    System.out.println(this.populacao.getIndividuos().get(pai2));
                    System.out.println(filho1);
                    System.out.println(filho2);
                    
                    System.exit(0);

                }

            }

        }

    }

    private void crossoverOX(Individuo pai1, Individuo pai2, Individuo filho1, Individuo filho2) {

        Random rnd = new Random();

        int i, j;

        i = rnd.nextInt(problema.dimensao / 2);
        do {
            j = rnd.nextInt(problema.dimensao);
        } while (i == j || i > j);

//        if (i > j) {
//            int aux = i;
//            i = j;
//            j = aux;
//        }
        // Parte central entre I e J
        filho1.getCromossomos().addAll(i, pai1.getCromossomos().subList(i, j));
        filho2.getCromossomos().addAll(i, pai2.getCromossomos().subList(i, j));

        // Copiar de P2 para F1
        int idx = j;
        int k = j;

        while (k < tamPop) {
            if (!filho1.getCromossomos().contains(pai2.getCromossomos().get(k))) {
                filho1.getCromossomos().add(idx, pai2.getCromossomos().get(k));
                idx++;

                if (idx == tamPop) {
                    break;
                }

            }

            k++;
            if (k == tamPop) {
                k = 0;
            }

        }

        idx = 0;
        k = 0;

        while (k < i) {
            if (!filho1.getCromossomos().contains(pai2.getCromossomos().get(k))) {
                filho1.getCromossomos().add(idx, pai2.getCromossomos().get(k));
                idx++;

                if (idx == i) {
                    break;
                }

            }

            k++;

        }

        // Copiar de P1 para F2         
        idx = j;
        k = j;

        while (k < tamPop) {
            if (!filho2.getCromossomos().contains(pai1.getCromossomos().get(k))) {
                filho2.getCromossomos().add(idx, pai1.getCromossomos().get(k));
                idx++;

                if (idx == tamPop) {
                    break;
                }

            }

            k++;
            if (k == tamPop) {
                k = 0;
            }

        }

        idx = 0;
        k = 0;

        while (k < i) {
            if (!filho2.getCromossomos().contains(pai1.getCromossomos().get(k))) {
                filho2.getCromossomos().add(idx, pai1.getCromossomos().get(k));
                idx++;

                if (idx == i) {
                    break;
                }

            }

            k++;

        }

    }

}
