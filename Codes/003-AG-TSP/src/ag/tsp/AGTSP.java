/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag.tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author fernando
 */
public class AGTSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Problema tsp = new Problema("/Users/fernando/Dropbox/Dados/Desenvolvimento/Netbeans/CSI557-Computacao-Evolucionaria/Codes/instances/tsplib/berlin52.tsp");
        //Problema tsp = new Problema("/home/fernando/Dropbox/Dados/Desenvolvimento/Netbeans/CSI557-Computacao-Evolucionaria/Codes/instances/tsplib/berlin52.tsp");

        //System.out.println(tsp);

        //Individuo individuo = new Individuo(tsp.dimensao); 
        //individuo.criar();
        // Caso 1 -> pop - 100, ger - 1000
        AlgoritmoGenetico ag1 = new AlgoritmoGenetico(100, 0.8, 0.05, 0.8, 1000, tsp);
        // Caso 2 -> pop - 1000, ger - 100
        AlgoritmoGenetico ag2 = new AlgoritmoGenetico(1000, 0.8, 0.05, 0.8, 100, tsp);

        Double result = 0.0;
        for (int c = 1; c <= 30; c++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);

            for (Integer i : casos) {
                long startTime = System.currentTimeMillis();
                switch (i) {
                    case 1:
                        ag1.executar();
                        result = ag1.getMelhorSolucao().getFuncaoObjetivo();
                        break;
                    case 2:
                        ag2.executar();
                        result = ag2.getMelhorSolucao().getFuncaoObjetivo();
                        break;
                }

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println(c + ";" + i + ";" + result + ";" + totalTime);
                System.out.flush();
                
            }

        }
    }

}
