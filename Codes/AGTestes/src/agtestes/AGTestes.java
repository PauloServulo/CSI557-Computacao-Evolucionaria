/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agtestes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author fernando
 */
public class AGTestes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        agreal.Problema problemaReal = new agreal.Problema();
        agbinario.Problema problemaBin = new agbinario.Problema();

        Double minimo = -100.0;
        Double maximo = 100.0;
        Integer nVariaveis = 100;

        int repeticoes = 10;

        // Parametros nao modificados
        Double pCrossover = 0.8;
        Double pMutacao = 0.05;

        // Casos de teste
        // 1 - Bin1; 2 - Bin2; 3 - Real1; 4 - Real2;
        ArrayList<String> nomes = new ArrayList<>(Arrays.asList("BIN1", "BIN2", "REAL1", "REAL2"));
        for (int i = 1; i <= repeticoes; i++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            Collections.shuffle(casos);

            for (int c = 1; c <= casos.size(); c++) {

                Integer tamanho = 50;
                Integer geracoes = 100;
                Integer precisao = 10;

                agbinario.AlgoritmoGenetico agBinario;
                agreal.AlgoritmoGenetico agReal;

                Double result = 0.0;
                long startTime = System.currentTimeMillis();

                int teste = casos.get(c - 1);

                switch (teste) {

                    case 1:
                        tamanho = 50;
                        geracoes = 100;
                        precisao = 10;
                        break;

                    case 2:
                        tamanho = 100;
                        geracoes = 1000;
                        precisao = 20;
                        break;

                    case 3:
                        tamanho = 50;
                        geracoes = 100;
                        break;

                    case 4:
                        tamanho = 100;
                        geracoes = 1000;
                        break;

                }

                if (teste <= 2) {
                    // Binario
                    agBinario = new agbinario.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problemaBin, precisao, minimo, maximo, nVariaveis);
                    result = agBinario.executar();
                } else {
                    agReal = new agreal.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problemaReal, minimo, maximo, nVariaveis);
                    result = agReal.executar();
                }

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println(nomes.get(teste - 1) + ";" + i + ";" + result + ";" + totalTime);
                System.out.flush();

            }

        }

    }

}
