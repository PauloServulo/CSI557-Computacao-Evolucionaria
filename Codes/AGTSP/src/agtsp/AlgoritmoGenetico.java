/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agtsp;

import java.util.Collections;
import java.util.Random;

/**
 *
 * @author fernando
 */
public class AlgoritmoGenetico {

    // Tamanho da população
    Integer tamanho;
    // Taxa de crossover - operador principal
    Double pCrossover;
    // Taxa de mutação - operador secundário
    Double pMutacao;
    // Critério de parada - número de gerações
    Integer geracoes;

    // Dados do problema
    // Problema - TSP
    Problema problema;
    // Variáveis
    Integer nVariaveis;

    public AlgoritmoGenetico(Integer tamanho, Double pCrossover, Double pMutacao, Integer geracoes, Problema problema, Integer nVariaveis) {
        this.tamanho = tamanho;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.problema = problema;
        this.nVariaveis = nVariaveis;
    }

    Populacao populacao;
    Populacao novaPopulacao;
    Individuo melhorSolucao;

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public Double executar() {

        populacao = new Populacao(nVariaveis, tamanho, problema);
        novaPopulacao = new Populacao(nVariaveis, tamanho, problema);

        // Criar a população
        populacao.criar();

        // Avaliar
        populacao.avaliar();

        // Recuperar o melhor indivíduo
        Random rnd = new Random();
        int ind1, ind2;

        BuscaLocalCombinatorio buscaLocalCombinatorio
                = new BuscaLocalCombinatorio(problema);
        
        // Enquanto o critério de parada não for satisfeito - Gerações
        for (int g = 1; g <= geracoes; g++) {

            for (int i = 0; i < this.tamanho; i++) {
                // Crossover
                if (rnd.nextDouble() <= this.pCrossover) {
                    // Realizar a operação

                    ind1 = rnd.nextInt(this.tamanho);

                    do {
                        ind2 = rnd.nextInt(this.tamanho);
                    } while (ind1 == ind2);

                    Individuo desc1 = new Individuo(nVariaveis);
                    Individuo desc2 = new Individuo(nVariaveis);

                    // Progenitores
                    Individuo p1 = populacao.getIndividuos().get(ind1);
                    Individuo p2 = populacao.getIndividuos().get(ind2);

                    // Ponto de corte
                    int corte = rnd.nextInt(p1.getVariaveis().size());

                    // Descendente 1 -> Ind1_1 + Ind2_2;
                    crossoverUmPonto(p1, p2, desc1, corte);

                    // Descendente 2 -> Ind2_1 + Ind1_2;
                    crossoverUmPonto(p2, p1, desc2, corte);

                    // Mutação
                    // Descendente 1
                    mutacaoSWAP(desc1);
                    // Descendente 2
                    mutacaoSWAP(desc2);

                    // Avaliar as novas soluções
                    problema.calcularFuncaoObjetivo(desc1);
                    problema.calcularFuncaoObjetivo(desc2);

                    buscaLocalCombinatorio.executar(desc1);
                    buscaLocalCombinatorio.executar(desc2);
                                                        
                    // Inserir na nova população
                    novaPopulacao.getIndividuos().add(desc1);
                    novaPopulacao.getIndividuos().add(desc2);
                }
            }

            // Definir sobreviventes -> populacao + descendentes
            // Merge: combinar pop+desc
            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
            // Ordenar populacao;
            Collections.sort(populacao.getIndividuos());

            // Eliminar os demais individuos - criterio: tamanho da população
            populacao.getIndividuos()
                    .subList(this.tamanho,
                            populacao.getIndividuos().size())
                    .clear();

            // Limpa a nova população para a geração seguinte
            novaPopulacao.getIndividuos().clear();

            // Imprimir a situacao atual
            System.out.println("Gen = " + g
                    + "\tCusto = "
                    + populacao.getIndividuos().get(0).getFuncaoObjetivo());
        }

        System.out.println("FO: " + populacao.getIndividuos().get(0).getFuncaoObjetivo());
        problema.calcularFuncaoObjetivo(populacao.getIndividuos().get(0));
        
        System.out.println("Melhor resultado: ");
        System.out.println(populacao.getIndividuos().get(0).getVariaveis());
        System.out.println("FO: " + populacao.getIndividuos().get(0).getFuncaoObjetivo());
        return populacao.getIndividuos().get(0).getFuncaoObjetivo();
        
        
    }

    private void crossoverUmPonto(Individuo ind1, Individuo ind2, Individuo descendente, int corte) {

        // Crossover OX

        // Copiar Parte 1 do Ind1
        descendente.getVariaveis().addAll(ind1.getVariaveis().subList(0, corte));

        int tam = descendente.getVariaveis().size();
        int i = corte;

        // Completa a partir da Parte 2 do Individuo 2
        // Se necessário, a Parte 1 do Indivíduo 2 - até todas as cidades serem visitadas
        while (tam < this.nVariaveis) {

            if (!descendente.getVariaveis().contains(ind2.getVariaveis().get(i))) {
                descendente.getVariaveis().add(ind2.getVariaveis().get(i));
                tam++;

                if (tam == this.nVariaveis) {
                    break;
                }

            }

            i++;

            if (i == this.nVariaveis) {
                i = 0;
            }

        }

    }

    private void mutacaoSWAP(Individuo individuo) {

        Random rnd = new Random();

        // Verificar a troca para cada cidade - posição
        for (int i = 0; i < individuo.getVariaveis().size(); i++) {
            if (rnd.nextDouble() <= this.pMutacao) {

                // Mutacao SWAP - troca entre duas cidades
                int j;
                do {
                    j = rnd.nextInt(this.nVariaveis);
                } while (i == j);

                Collections.swap(individuo.getVariaveis(), i, j);

            }
        }

    }

}
