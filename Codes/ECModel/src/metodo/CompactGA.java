/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import java.util.ArrayList;
import java.util.Random;
import problema.Problema;
import solucao.IndividuoBinario;
import solucao.IndividuoDouble;

/**
 *
 * @author fernando
 */
public class CompactGA implements Metodo {

    Problema problema;
    double min;
    double max;
    int precisao;
    
    int n;
    int l;
    int gmax;

    public CompactGA(Problema problema, double min, double max, int precisao, int n, int l, int gmax) {
        this.problema = problema;
        this.min = min;
        this.max = max;
        this.precisao = precisao;
        this.n = n;
        this.l = l;
        this.gmax = gmax;
    }

    

    @Override
    public void executar() {

        ArrayList<Double> p = new ArrayList<>();
        IndividuoDouble best =  new IndividuoDouble(min, max, n);
        best.setFuncaoObjetivo(Double.MAX_VALUE);

        // Inicializar o vetor
        Random rnd = new Random();
        for (int i = 0; i < this.l; i++) {
            p.add(0.5);
        }

        for (int g = 1; g <= this.gmax; g++) {

            // Gerando a, b
            IndividuoBinario a = gerarIndividuo(p);
            IndividuoBinario b = gerarIndividuo(p);
            
            IndividuoDouble aDbl = a.decodificar(this.min, this.max, this.precisao);

            IndividuoDouble bDbl = b.decodificar(this.min, this.max, this.precisao);
            
            problema.calcularFuncaoObjetivo(aDbl);
            problema.calcularFuncaoObjetivo(bDbl);

            a.setFuncaoObjetivo(aDbl.getFuncaoObjetivo());
            b.setFuncaoObjetivo(bDbl.getFuncaoObjetivo());
            
            IndividuoBinario winner, loser;

            // Compete(a, b)
            if (a.getFuncaoObjetivo() < b.getFuncaoObjetivo()) {
                winner = a;
                loser = b;
            } else {
                winner = b;
                loser = a;
            }

            // Verificação do melhor indivíduo
            if (winner.getFuncaoObjetivo() < best.getFuncaoObjetivo()) {
                best.setFuncaoObjetivo(winner.getFuncaoObjetivo());
            }
            
            for (int i = 0; i < this.l; i++) {

                if (winner.getCromossomos().get(i) != loser.getCromossomos().get(i)) {

                    if (winner.getCromossomos().get(i) == 1) {
                        p.set(i, p.get(i) + 1.0 / this.n);
                    } else {
                        p.set(i, p.get(i) - 1.0 / this.n);
                    }

                }

            }

        IndividuoDouble resultado = new IndividuoDouble(this.min, this.max, this.l);
        resultado.setCromossomos(p);
        problema.calcularFuncaoObjetivo(resultado);
                        
            System.out.println("G = " + g + "\t"+ winner.getFuncaoObjetivo() + "\t" + best.getFuncaoObjetivo() + "\t" + resultado.getFuncaoObjetivo());
            
        }
        
        //System.out.println(p);
        IndividuoDouble resultado = new IndividuoDouble(this.min, this.max, this.l);
        resultado.setCromossomos(p);
        problema.calcularFuncaoObjetivo(resultado);
        System.out.println(resultado.getFuncaoObjetivo());

    }

    private IndividuoBinario gerarIndividuo(ArrayList<Double> p) {

        IndividuoBinario ind = new IndividuoBinario(this.l);

        Random rnd = new Random();

        for (int i = 0; i < ind.getDimensao(); i++) {
            if (rnd.nextDouble() <= p.get(i)) {
                ind.getCromossomos().add(1);
            } else {
                ind.getCromossomos().add(0);
            }
        }

        return ind;

    }

}
