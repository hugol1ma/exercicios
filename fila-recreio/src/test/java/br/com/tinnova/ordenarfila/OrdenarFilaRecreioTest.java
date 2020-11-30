package br.com.tinnova.ordenarfila;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrdenarFilaRecreioTest {


    @Test
    public void testarOrdenacaoUmLugarMantido(){
        OrdenarFilaRecreio ordenarFilaRecreio = new OrdenarFilaRecreio();

        List<Integer> notas = new ArrayList<>();
        notas.add(100);
        notas.add(80);
        notas.add(90);

        Integer lugaresMantidos = ordenarFilaRecreio.quantidadeLugarMantido(notas);

        assertEquals( new Integer(1), lugaresMantidos);

    }

    @Test
    public void testarOrdenacaoNenhumLugarMantido(){
        OrdenarFilaRecreio ordenarFilaRecreio = new OrdenarFilaRecreio();

        List<Integer> notas = new ArrayList<>();
        notas.add(100);
        notas.add(120);
        notas.add(30);
        notas.add(50);

        Integer lugaresMantidos = ordenarFilaRecreio.quantidadeLugarMantido(notas);

        assertEquals( new Integer(0), lugaresMantidos);

    }

    @Test
    public void testarOrdenacaoComTodosLugaresMantidos(){
        OrdenarFilaRecreio ordenarFilaRecreio = new OrdenarFilaRecreio();

        List<Integer> notas = new ArrayList<>();
        notas.add(100);
        notas.add(90);
        notas.add(30);
        notas.add(25);

        Integer lugaresMantidos = ordenarFilaRecreio.quantidadeLugarMantido(notas);

        assertEquals( new Integer(4), lugaresMantidos);

    }
}
