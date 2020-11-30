package br.com.tinnova.veiculo.rest;

import br.com.tinnova.veiculo.entidade.MarcaVeiculoEnum;
import br.com.tinnova.veiculo.entidade.Veiculo;
import br.com.tinnova.veiculo.negocio.VeiculoNegocio;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@RunWith(SpringRunner.class)
@WebMvcTest(VeiculoRest.class)
public class VeiculoRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VeiculoNegocio veiculoNegocio;


    @Test
    public void criarVeiculo() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setNome("Ranger");
        veiculo.setMarca(MarcaVeiculoEnum.FORD);
        veiculo.setAno(2018);
        veiculo.setVendido(Boolean.FALSE);
        veiculo.setDescricao("Camionete Ranger");

        ObjectMapper obj = new ObjectMapper();
        obj.registerModule(new JavaTimeModule());

        String veiculoJson = obj.writeValueAsString(veiculo);


        Veiculo veiculoRetorno = veiculo;
        veiculoRetorno.setId(1);
        veiculoRetorno.setCreated(LocalDateTime.now());
        veiculoRetorno.setUpdated(LocalDateTime.now());

        when(veiculoNegocio.save(any())).thenReturn(ResponseEntity.ok(veiculoRetorno));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/veiculos")
                .content(veiculoJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Veiculo veiculoResult = obj.readValue(result.getResponse().getContentAsString(), Veiculo.class);

        assertEquals(veiculoRetorno.getId(), veiculoResult.getId());

    }

    @Test
    public void criarVeiculoComNomeVazio() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setNome("");
        veiculo.setMarca(MarcaVeiculoEnum.FORD);
        veiculo.setAno(2018);
        veiculo.setVendido(Boolean.FALSE);
        veiculo.setDescricao("Camionete Ranger");

        ObjectMapper obj = new ObjectMapper();
        obj.registerModule(new JavaTimeModule());

        String veiculoJson = obj.writeValueAsString(veiculo);


        when(veiculoNegocio.save(any())).thenCallRealMethod();


        Veiculo veiculoRetorno = veiculo;
        veiculoRetorno.setId(1);
        veiculoRetorno.setCreated(LocalDateTime.now());
        veiculoRetorno.setUpdated(LocalDateTime.now());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/veiculos")
                .content(veiculoJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();


        assertEquals("Informe o nome do veiculo", result.getResponse().getContentAsString());

    }

    @Test
    public void criarVeiculoComMarcaVazio() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setNome("Ranger");
        veiculo.setAno(2018);
        veiculo.setVendido(Boolean.FALSE);
        veiculo.setDescricao("Camionete Ranger");

        ObjectMapper obj = new ObjectMapper();
        obj.registerModule(new JavaTimeModule());

        String veiculoJson = obj.writeValueAsString(veiculo);


        when(veiculoNegocio.save(any())).thenCallRealMethod();


        Veiculo veiculoRetorno = veiculo;
        veiculoRetorno.setId(1);
        veiculoRetorno.setCreated(LocalDateTime.now());
        veiculoRetorno.setUpdated(LocalDateTime.now());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/veiculos")
                .content(veiculoJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();


        assertEquals("Informe a marca do veiculo", result.getResponse().getContentAsString());

    }

    @Test
    public void criarVeiculoComAnoVazio() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setNome("Ranger");
        veiculo.setMarca(MarcaVeiculoEnum.FORD);
        veiculo.setVendido(Boolean.FALSE);
        veiculo.setDescricao("Camionete Ranger");

        ObjectMapper obj = new ObjectMapper();
        obj.registerModule(new JavaTimeModule());

        String veiculoJson = obj.writeValueAsString(veiculo);


        when(veiculoNegocio.save(any())).thenCallRealMethod();


        Veiculo veiculoRetorno = veiculo;
        veiculoRetorno.setId(1);
        veiculoRetorno.setCreated(LocalDateTime.now());
        veiculoRetorno.setUpdated(LocalDateTime.now());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/veiculos")
                .content(veiculoJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();


        assertEquals("Informe o ano do veiculo", result.getResponse().getContentAsString());

    }


    @Test
    public void deveListarVeiculos() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setNome("Ranger");
        veiculo.setMarca(MarcaVeiculoEnum.FORD);
        veiculo.setAno(2018);
        veiculo.setVendido(Boolean.FALSE);
        veiculo.setDescricao("Camionete Ranger");

        ObjectMapper obj = new ObjectMapper();
        obj.registerModule(new JavaTimeModule());

        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(veiculo);

        when(veiculoNegocio.findAll()).thenReturn(ResponseEntity.ok(veiculos));


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        List<Veiculo> veiculosRetornados = Arrays.asList(obj.readValue(result.getResponse().getContentAsString(), Veiculo[].class).clone());

        assertEquals(veiculosRetornados.size(), 1);
        assertEquals(veiculosRetornados.get(0).getNome(), "Ranger");

    }

}
