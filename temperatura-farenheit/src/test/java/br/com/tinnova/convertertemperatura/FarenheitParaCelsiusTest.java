package br.com.tinnova.convertertemperatura;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FarenheitParaCelsiusTest {

    @Test
    public void testaFarenheitValorValido(){
        ConverterTemperatura converterTemperatura = new FarenheitParaCelsius();
        Integer celsius = converterTemperatura.converter(171);

        assertEquals(new Integer(77), celsius);
    }

    @Test
    public void testaFarenheitValorValido2(){
        ConverterTemperatura converterTemperatura = new FarenheitParaCelsius();
        Integer celsius = converterTemperatura.converter(209);

        assertEquals(new Integer(98), celsius);
    }

    @Test(expected = RuntimeException.class)
    public void testaFarenheitValorInvalido(){
        ConverterTemperatura converterTemperatura = new FarenheitParaCelsius();
        Integer celsius = converterTemperatura.converter(null);
    }

}
