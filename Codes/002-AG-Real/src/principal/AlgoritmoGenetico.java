/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.Collections;
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

    double min;
    double max;
    int nvar;

    Individuo melhorSolucao;
    Populacao populacao;
    Populacao novaPopulacao;

    public AlgoritmoGenetico(int tamPop, double pCrossover, double pMutacao, int geracoes, double min, double max, int nvar) {
        this.tamPop = tamPop;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.min = min;
        this.max = max;
        this.nvar = nvar;
    }

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }

    public void executar() {

        populacao = new Populacao(min, max, nvar, tamPop);
        novaPopulacao = new Populacao(min, max, nvar, tamPop);

        // Criar a populacao
        populacao.criarPopulacao();
        // Avaliacao da populacao inicial
        populacao.avaliar();

        try {
            // Recuperar o melhor individuo
            melhorSolucao = (Individuo) populacao.getMelhorIndividuo().clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AlgoritmoGenetico.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Solucao inicial: "
                + melhorSolucao.getFuncaoObjetivo());

        Random rnd = new Random();
        int pai1, pai2;

        // Geracoes - criterio de parada
        for (int gen = 1; gen <= geracoes; gen++) {

            // Percorrer a quantidade de individuos 
            // para a reproducao
            novaPopulacao.getIndividuos().clear();

            for (int j = 0; j < this.tamPop; ++j) {
                // Reproducao
                // - Avaliar a aplicação do operador
                if (rnd.nextDouble() <= this.pCrossover) {
                    // - Selecionar pais
                    pai1 = rnd.nextInt(this.tamPop);
                    do {
                        pai2 = rnd.nextInt(this.tamPop);
                    } while (pai1 == pai2);

                    Individuo ind1 = null;
                    Individuo ind2 = null;
                    try {
                        // Copiar os pais nos descendentes:
                        ind1 = (Individuo) populacao.getIndividuos().get(pai1).clone();
                        ind2 = (Individuo) populacao.getIndividuos().get(pai2).clone();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(AlgoritmoGenetico.class.getName()).log(Level.SEVERE, null, ex);
                    }

//                    System.out.println(populacao.getIndividuos().get(pai1).getCromossomos().size());
//                    System.out.println(populacao.getIndividuos().get(pai2).getCromossomos().size());
                    // - Operar - crossover
                    this.crossoverUmPonto(populacao.getIndividuos().get(pai1),
                            populacao.getIndividuos().get(pai2), ind1);
                    this.crossoverUmPonto(populacao.getIndividuos().get(pai2),
                            populacao.getIndividuos().get(pai1), ind2);

                    // - Operar - mutacao
                    mutacaoPorBit(ind1);
                    mutacaoPorBit(ind2);

                    // Avaliar descendentes
                    Problema.calcularFuncaoObjetivo(ind1);
                    Problema.calcularFuncaoObjetivo(ind2);

                    novaPopulacao.getIndividuos().add(ind1);
                    novaPopulacao.getIndividuos().add(ind2);

                }
            }

            // Definir sobrevivencia - pop + descendentes
            // Combinar Pop + NovaPop
            populacao.getIndividuos()
                    .addAll(novaPopulacao.getIndividuos());
            Collections.sort(populacao.getIndividuos());
            //populacao.getIndividuos().ensureCapacity(tamPop);
            //populacao.getIndividuos().trimToSize();
            populacao.getIndividuos()
                    .subList(tamPop, 
                            populacao.getIndividuos().size()).clear();

            if (populacao
                    .getMelhorIndividuo()
                    .getFuncaoObjetivo()
                    < this.getMelhorSolucao().getFuncaoObjetivo()) {
                try {
                    this.setMelhorSolucao((Individuo) populacao.getMelhorIndividuo().clone());
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(AlgoritmoGenetico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.println("Gen = " + gen
                    + "\tFO = " + this.getMelhorSolucao()
                            .getFuncaoObjetivo()
                    + "\tPop = " + populacao.getIndividuos().size());

        }

    }

    private void crossoverUmPonto(Individuo pai1,
            Individuo pai2, Individuo filho) {

        Random rnd = new Random();
        int corte = rnd.nextInt(pai1.getCromossomos().size());

//        System.out.println(filho.getCromossomos().size());
//        System.out.println(pai1.getCromossomos().size());        
        filho.getCromossomos().clear();

//        System.out.println(filho.getCromossomos().size());
//        System.out.println(pai1.getCromossomos().size());        
        filho.getCromossomos()
                .addAll(pai1.getCromossomos()
                        .subList(0, corte));
        filho.getCromossomos()
                .addAll(pai2.getCromossomos()
                        .subList(corte,
                                pai2.getCromossomos().size()));

    }

    private void mutacaoPorBit(Individuo filho) {

        Random rnd = new Random();

        double valor;
        for (int i = 0; i < filho.getCromossomos().size(); ++i) {
            if (rnd.nextDouble() <= this.pMutacao) {
                valor = filho.getCromossomos().get(i) 
                        * rnd.nextDouble();
                
                if (rnd.nextBoolean() == false) {
                    valor = -valor;
                }
                valor = filho.getCromossomos().get(i) 
                        + valor;
                if ( valor >= this.min && valor <= this.max ) {
                    filho.getCromossomos().set(i, valor);
                }
            }
        }

    }

}
