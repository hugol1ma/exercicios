package br.com.tinnova.convertertemperatura;

public class FarenheitParaCelsius implements ConverterTemperatura{


    /**
     * Método para transformar a temperatura de Farenheit para Celsius utilizando a fórmula: C = (5 * (F-32) / 9);
     * @param farenheit
     * @return temperatura em celsius
     */
    public Integer converter(Integer farenheit){

        if (farenheit == null) {
            throw new RuntimeException("Informe uma temperatura em farenheit válida");
        }

        // Formula :   C = (5 * (F-32) / 9);
        Integer celsius = (5 * (farenheit - 32)) / 9;

        return celsius;
    }
}
