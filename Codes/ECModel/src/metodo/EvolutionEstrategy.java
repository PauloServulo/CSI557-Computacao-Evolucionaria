/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import java.util.Collections;
import java.util.Random;
import problema.Problema;
import solucao.Individuo;
import solucao.IndividuoDouble;
import solucao.PopulacaoDouble;

/**
 *
 * @author fernando
 */
public class EvolutionEstrategy implements Metodo {

    private double min;
    private double max;
    private int nvar;
    private int mu;
    private int lambda;
    private Problema problema;
    private int geracoes;
    private double pMutacao;

    public EvolutionEstrategy(double min, double max, int nvar, int mu, int lambda, Problema problema, int geracoes, double pMutacao) {
        this.min = min;
        this.max = max;
        this.nvar = nvar;
        this.mu = mu;
        this.lambda = lambda;
        this.problema = problema;
        this.geracoes = geracoes;
        this.pMutacao = pMutacao;
    }

    @Override
    public void executar() {

        // Gerar uma populacao inicial aleatoriamente - mu
        PopulacaoDouble populacao = new PopulacaoDouble(problema, min, max, nvar, mu);
        populacao.criar();
        // Avaliar
        populacao.avaliar();

        PopulacaoDouble novaPopulacao = new PopulacaoDouble();

        // Criterio de parada -- gen
        for (int g = 1; g <= geracoes; g++) {
            // Para cada pai, gerar lambda/mu filhos
            for (int p = 0; p < populacao.getIndividuos().size(); p++) {
                for (int i = 0; i < lambda / mu; i++) {
                    // Mutacao
                    Random rnd = new Random();
                    double valor = rnd.nextDouble();
                    if (valor <= this.pMutacao) {
                        IndividuoDouble filho = (IndividuoDouble) populacao.getIndividuos().get(p).clone();
                        mutacaoPorBit(filho);
                        // avaliar filhos
                        problema.calcularFuncaoObjetivo(filho);
                        // Adicionar
                        novaPopulacao.getIndividuos().add(filho);
                        // Busca local

                    }
                }
            }

            // ES(mu, lambda)
            populacao.getIndividuos().clear();
            // ES(mu+lambda)
            populacao.getIndividuos()
                    .addAll(novaPopulacao.getIndividuos());
            Collections.sort(populacao.getIndividuos());
            populacao.getIndividuos()
                    .subList(this.mu,
                            populacao.getIndividuos().size()).clear();

            System.out.println(populacao.getMelhorIndividuo().getFuncaoObjetivo());

        }

    }

    private void mutacaoPorBit(IndividuoDouble filho) {

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
                if (valor >= this.min && valor <= this.max) {
                    filho.getCromossomos().set(i, valor);
                }
            }
        }

    }
}
