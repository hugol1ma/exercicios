package br.com.tinnova.convertertemperatura;

import java.math.BigDecimal;

public interface ConverterTemperatura {


    /**
     * Método converter uma determinada temperatura para outra
     * @param temperatura
     * @return temperatura convertida
     */
    Integer converter(Integer temperatura);
}
