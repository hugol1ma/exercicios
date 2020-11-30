package br.com.tinnova.ordenarfila;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenarFilaRecreio {


    /**
     * Método que verifica uma lista de notas dos alunos e realiza uma ordenação da nota maior para o menor e verifica quantos
     * alunos serão mantidos na mesma posição da fila
     *
     * @param notas
     * @return retorna o numero de alunos que continuaram na fila de acordo com a nota
     */
    public int quantidadeLugarMantido(List<Integer> notas){

        Integer lugaresMantidos = 0;

        List<Integer> notasOrdenadas = ordenar(notas);

        for(int i =0; i < notasOrdenadas.size(); i++){
            if (notasOrdenadas.get(i) == notas.get(i)) {
                lugaresMantidos ++;
            }
        }
        return lugaresMantidos;
    }


    /**
     * Método que ordena da nota maior para o menor
     *
     * @param notas
     * @return retorna as notas ordenadas em ordem decrescente
     */
    private List<Integer> ordenar(List<Integer> notas) {
        return notas
                .stream()
                // Ordenacao reversa
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

}
