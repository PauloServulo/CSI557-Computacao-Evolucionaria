/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import operacoes.BuscaLocalCombinatorio;
import problema.Problema;
import solucao.Individuo;
import solucao.IndividuoDouble;
import solucao.IndividuoInteger;
import solucao.PopulacaoDouble;
import solucao.PopulacaoInteger;

/**
 *
 * @author fernando
 */
public class EvolucaoDiferencialTSP implements Metodo {

    private double min;
    private double max;
    private int gmax; // crit�rio de parada
    private int D; // numero de variaveis
    private int Np; // tamanho da popula��o
    private double F; // coeficiente de muta��o
    private double Cr; // coeficiente de crossover
    private Problema problema; // TSP

    public EvolucaoDiferencialTSP(double min, double max, int gmax, int D, int Np, double F, double Cr, Problema problema) {
        this.min = min;
        this.max = max;
        this.gmax = gmax;
        this.D = D;
        this.Np = Np;
        this.F = F;
        this.Cr = Cr;
        this.problema = problema;
    }

    @Override
    public void executar() {

        // Cria��o da popula��o inicial - X     
        PopulacaoDouble populacao = new PopulacaoDouble(problema, min, max, D, Np);
        populacao.criar();

        // Populacao para representar o contexto combinatorio
        PopulacaoInteger popTSP = new PopulacaoInteger(Np, problema);

        converteRealParaInteiro(populacao, popTSP);

        // Avalia��o da popula��o inicial
        popTSP.avaliar();

        // Nova popula��o - Y
        PopulacaoDouble novaPopulacao = new PopulacaoDouble();

        // Enquanto o crit�rio de parada n�o for atingido...
        for (int g = 1; g <= gmax; g++) {
            // Para cada vetor da popula��o
            for (int i = 0; i < Np; i++) {

                // Selecionar aleatoriamente r0, r1, r2
                Random rnd = new Random();
                int r0 = rnd.nextInt(Np);
                int r1, r2;
                do {
                    r1 = rnd.nextInt(Np);
                } while (r1 == r0);

                do {
                    r2 = rnd.nextInt(Np);
                } while (r2 == r1 || r2 == r0);

                IndividuoDouble trial = new IndividuoDouble(min, max, D);

                IndividuoDouble xr0 = (IndividuoDouble) populacao.getIndividuos().get(r0);
                IndividuoDouble xr1 = (IndividuoDouble) populacao.getIndividuos().get(r1);
                IndividuoDouble xr2 = (IndividuoDouble) populacao.getIndividuos().get(r2);

                perturbacao(trial, xr1, xr2);
                mutacao(trial, xr0);

                IndividuoDouble target = (IndividuoDouble) populacao.getIndividuos().get(i);

                crossover(trial, target);

                IndividuoInteger trialTSP = converteRealParaInteiro(trial);
                IndividuoInteger targetTSP = converteRealParaInteiro(target);
                
                // Sele��o
                problema.calcularFuncaoObjetivo(trialTSP);
                problema.calcularFuncaoObjetivo(targetTSP);
                
                // BUSCA LOCAL
                BuscaLocalCombinatorio buscaLocal = new BuscaLocalCombinatorio(problema);
                buscaLocal.buscaLocal(trialTSP);
                
                // converte de Integer para Double
                trial = converteInteiroParaReal(trialTSP);
                
                trial.setFuncaoObjetivo(trialTSP.getFuncaoObjetivo());
                target.setFuncaoObjetivo(targetTSP.getFuncaoObjetivo());
                
                if (trialTSP.getFuncaoObjetivo() <= targetTSP.getFuncaoObjetivo()) {
                    novaPopulacao.getIndividuos().add(trial);
                } else {
                    novaPopulacao.getIndividuos().add(target.clone());
                }

            }

            populacao.getIndividuos().clear();
            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
            Collections.sort(populacao.getIndividuos());

            System.out.println("Gen = " + g + "\tFO: " + populacao.getMelhorIndividuo().getFuncaoObjetivo());
        }
        
        System.out.println("Melhor solucao = " + populacao.getMelhorIndividuo());
        IndividuoInteger indTSP = converteRealParaInteiro(populacao.getMelhorIndividuo());
        System.out.println("Melhor solucao Int = " + indTSP);
    }

    private void perturbacao(IndividuoDouble trial, IndividuoDouble xr1, IndividuoDouble xr2) {

        // Diferen�a entre r1 e r2
        for (int i = 0; i < D; i++) {
            double diferenca = xr1.getCromossomos().get(i)
                    - xr2.getCromossomos().get(i);
            trial.getCromossomos().add(diferenca);
        }

    }

    private void mutacao(IndividuoDouble trial, IndividuoDouble xr0) {

        // Multiplicar por F a diferen�a e somar com Xr0
        for (int i = 0; i < D; i++) {
            double valor = xr0.getCromossomos().get(i)
                    + this.F * (trial.getCromossomos().get(i));
            trial.getCromossomos().set(i, valor);
        }

    }

    private void crossover(IndividuoDouble trial, IndividuoDouble target) {

        Random rnd = new Random();
        int jRand = rnd.nextInt(this.D);

        for (int i = 0; i < this.D; i++) {
            if (!(rnd.nextDouble() <= this.Cr || i == jRand)) {
                trial.getCromossomos().set(i, target.getCromossomos().get(i));
            }
        }

    }

    private void converteRealParaInteiro(PopulacaoDouble populacao, PopulacaoInteger popTSP) {

        popTSP.getIndividuos().clear();

        for (Individuo ind : populacao.getIndividuos()) {

            IndividuoInteger indTSP = converteRealParaInteiro(ind);

            popTSP.getIndividuos().add(indTSP);
            ind.setFuncaoObjetivo(indTSP.getFuncaoObjetivo());

        }

    }

    private IndividuoInteger converteRealParaInteiro(Individuo ind) {

        IndividuoInteger indTSP = new IndividuoInteger(this.D);
        indTSP.setCromossomos(new ArrayList<>(Arrays.asList(new Integer[this.D])));
        // Copia dos cromossomos
        ArrayList<Double> crom = (ArrayList<Double>) ind.getCromossomos().clone();
        ArrayList<Double> valores = (ArrayList<Double>) ind.getCromossomos().clone();
        // Ordena
        Collections.sort(crom);

        int cliente = 1;

        for (int i = 0; i < crom.size(); i++) {
            int idx = valores.indexOf(crom.get(i));
            indTSP.getCromossomos().set(idx, cliente);
            valores.set(idx, Double.NaN);
            cliente++;
        }
        
        indTSP.setFuncaoObjetivo(ind.getFuncaoObjetivo());
        
        return indTSP;
    }

    private IndividuoDouble converteInteiroParaReal(Individuo ind) {

        IndividuoDouble indDouble = new IndividuoDouble(this.min, this.max, this.D);
        for(int i = 0; i < ind.getCromossomos().size(); i++) {
            
            Double dbl = Double.valueOf(ind.getCromossomos().get(i).toString());
            dbl /= this.D;
            
            indDouble.getCromossomos().add(dbl);            
        }
        
        indDouble.setFuncaoObjetivo(ind.getFuncaoObjetivo());
        
        return indDouble;
    }

}
