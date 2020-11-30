package br.com.tinnova;

import br.com.tinnova.convertertemperatura.ConverterTemperatura;
import br.com.tinnova.convertertemperatura.FarenheitParaCelsius;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Informe um numero inteiro: ");
        Integer temperatura = keyboard.nextInt();

        ConverterTemperatura converterTemperatura = new FarenheitParaCelsius();
        Integer temperaturaConvertida = converterTemperatura.converter(temperatura);
        System.out.println(temperaturaConvertida);

    }
}
