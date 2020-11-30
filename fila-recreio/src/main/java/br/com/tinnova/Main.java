package br.com.tinnova;

import br.com.tinnova.ordenarfila.OrdenarFilaRecreio;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Informe a quantidade de alunos ");
        int quantidadeAluno = keyboard.nextInt();

        keyboard = new Scanner(System.in);
        System.out.println("Informe as notas de cada um separado por espaço: ");
        String notaGeral = keyboard.nextLine();

        List<Integer> notas =  Arrays.stream(notaGeral.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        if (notas.size() != quantidadeAluno) {
            throw new RuntimeException("Quantidade de notas invalida para a quantidade de alunos");
        }
        OrdenarFilaRecreio ordenarFilaRecreio = new OrdenarFilaRecreio();
        Integer quantidadeLugarMantido = ordenarFilaRecreio.quantidadeLugarMantido(notas);
        System.out.println(quantidadeLugarMantido);
    }
}
