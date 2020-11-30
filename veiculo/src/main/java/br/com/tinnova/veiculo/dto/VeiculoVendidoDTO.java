package br.com.tinnova.veiculo.dto;

import br.com.tinnova.veiculo.entidade.MarcaVeiculoEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * DTO para alterar os dados de venda de um determinado veículo
 */

public class VeiculoVendidoDTO {


    private Integer id;
    private Boolean vendido;
    private LocalDateTime updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
